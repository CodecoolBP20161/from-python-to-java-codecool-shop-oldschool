package com.codecool.shop.controller;

import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.database.CustomerDaoJDBC;
import com.codecool.shop.dao.implementation.database.LineItemDaoJDBC;
import com.codecool.shop.dao.implementation.database.OrderDaoJDBC;
import com.codecool.shop.model.*;
import org.apache.http.client.utils.URIBuilder;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class CartController extends ShopController {

    public static ModelAndView renderOrder(Request req, Response res) {

        // check if session contains order, instantiate if it doesn't
        CartInterface cart = req.session().attribute("order");
        if (cart == null) {
            cart = new Order();
            req.session().attribute("order", cart);
        }

        // find product by product_id from request
        Product orderedProduct = productDataStore.find(Integer.parseInt(req.params(":product-id")));
        // add lineitem to the order
        cart.addProduct(orderedProduct);
        // redirect to the last path
        res.redirect(req.session().attribute("path"));
        return render(req, res);
    }

    public static ModelAndView renderShoppingCart(Request req, Response res) {
        Map params = new HashMap<>();

        // get items from cart
        if (req.session().attribute("order") != null) {
            Order order = req.session().attribute("order");
            params.put("total", order.getTotalPrice());
            params.put("lineitems", order.getLineItems());
            req.session().attribute("order_id", order.getId());
            System.out.println("orderID = " + order.getId());
        }

        return new ModelAndView(params, "product/shopping_cart");
    }

    public static ModelAndView renderCheckOut(Request req, Response res) {
        Map params = new HashMap<>();

        return new ModelAndView(params, "/checkout");
    }

    public static ModelAndView saveCustomerDetails(Request req, Response res) throws URISyntaxException {
        String PAYMENT_SERVICE_URI = "http://localhost:9000/make-payment";
        String ORDER_SERVICE_URI = "http://localhost:8888/order/";

        Map params = new HashMap<>();
        OrderDao orderDao = new OrderDaoJDBC();

        Customer customer = setCustomer(req);

        Order order = setOrder(req, orderDao, customer);

        URIBuilder serviceURIBuilder = new URIBuilder(PAYMENT_SERVICE_URI);
        serviceURIBuilder.addParameter("total", String.valueOf(order.getTotalPrice()));
        serviceURIBuilder.addParameter("return-link", ORDER_SERVICE_URI + order.getId());

        res.redirect(serviceURIBuilder.build().toASCIIString());
        return new ModelAndView(params, "/payment");
    }

    private static Order setOrder(Request req, OrderDao orderDao, Customer customer) {
        LineItemDao lineItemDao = new LineItemDaoJDBC();
        Order order = req.session().attribute("order");
        order.getLineItems().stream().forEach(l -> l.setOrder(order.getId()));
        order.setCustomer(customer);
        order.setOrderStatus(OrderStatus.IN_CART);
        orderDao.add(order);
        order.getLineItems().stream().forEach(l -> lineItemDao.add(l));
        return order;
    }

    private static Customer setCustomer(Request req) {
        CustomerDao customerDao = new CustomerDaoJDBC();
        Customer customer = new Customer(
                req.queryParams("name"),
                req.queryParams("email"),
                req.queryParams("tel"),
                req.queryParams("billingCountry"),
                req.queryParams("billingCity"),
                req.queryParams("billingZip"),
                req.queryParams("billingAddr"),
                req.queryParams("shippingCountry"),
                req.queryParams("shippingCity"),
                req.queryParams("shippingZip"),
                req.queryParams("shippingAddr")
        );

        customerDao.add(customer);
        return customer;
    }

    public static ModelAndView renderPayment(Request req, Response res) {
        Map params = new HashMap<>();
        OrderDao orderDataStore = new OrderDaoJDBC();
        if (req.session().attribute("order_id") != null) {
            orderDataStore.setOrderStatus((Integer) req.session().attribute("order_id"), OrderStatus.PAID);
        }

        return new ModelAndView(params, "/payment");
    }


}