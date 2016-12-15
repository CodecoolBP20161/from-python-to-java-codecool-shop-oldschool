package com.codecool.shop.controller;


import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.database.CustomerDaoJDBC;
import com.codecool.shop.dao.implementation.database.LineItemDaoJDBC;
import com.codecool.shop.dao.implementation.database.OrderDaoJDBC;
import com.codecool.shop.model.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartController extends ShopController {

    public static ModelAndView renderOrder(Request req, Response res) {

        // check if session contains order, instantiate if it doesn't
        //TODO: testOrderInterface
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
        }

        return new ModelAndView(params, "product/shopping_cart");
    }

    public static ModelAndView renderCheckOut(Request req, Response res) {
        Map params = new HashMap<>();

        return new ModelAndView(params, "/checkout");
    }

    public static ModelAndView saveCustomerDetails(Request req, Response res) {
        Map params = new HashMap<>();
        OrderDao orderDao = new OrderDaoJDBC();
        CustomerDao customerDao = new CustomerDaoJDBC();
        LineItemDao lineItemDao = new LineItemDaoJDBC();

        Order order = req.session().attribute("order");
        order.getLineItems().stream().forEach(l -> l.setOrder(order.getId()));

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
        order.setCustomer(customer);
        order.setOrderStatus(OrderStatus.IN_CART);
        orderDao.add(order);
        req.session().attribute("order_id", order.getId());
        System.out.println("orderID = " + order.getId());
        order.getLineItems().stream().forEach(l -> lineItemDao.add(l));
        res.redirect("/payment");
        return new ModelAndView(params, "/payment");
    }



    public static ModelAndView renderPayment(Request req, Response res) {
        Map params = new HashMap<>();
        OrderDao orderDataStore = new OrderDaoJDBC();
        if (req.session().attribute("order_id")!=null) {
            orderDataStore.setOrderStatus((Integer)req.session().attribute("order_id"), OrderStatus.PAID);
        }

        return new ModelAndView(params, "/payment");
    }


}
