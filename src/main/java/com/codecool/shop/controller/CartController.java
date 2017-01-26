package com.codecool.shop.controller;

import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.database.CustomerDaoJDBC;
import com.codecool.shop.dao.implementation.database.LineItemDaoJDBC;
import com.codecool.shop.dao.implementation.database.OrderDaoJDBC;
import com.codecool.shop.model.*;
import com.codecool.shop.services.ShippingCostCalculatorService;

import org.apache.http.client.utils.URIBuilder;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class CartController extends ShopController {
    private static ShippingServiceController shippingServiceController = new ShippingServiceController(ShippingCostCalculatorService.getInstance());
    private static String PAYMENT_SERVICE_URI = "http://localhost:9000/make-payment";
    private static String EMAIL_SERVICE_URI = "http://localhost:8888/send-email/";

    public static ModelAndView renderOrder(Request req, Response res) {
        CartInterface cart = req.session().attribute("order");
        if (cart == null) {
            cart = new Order();
            req.session().attribute("order", cart);
        }

        Product orderedProduct = productDataStore.find(Integer.parseInt(req.params(":product-id")));
        cart.addProduct(orderedProduct);
        res.redirect(req.session().attribute("path"));
        return render(req, res);
    }

    public static ModelAndView renderShoppingCart(Request req, Response res) {
        Map params = new HashMap<>();

        if (req.session().attribute("order") != null) {
            Order order = req.session().attribute("order");
            params.put("total", order.getTotalPrice());
            params.put("lineitems", order.getLineItems());
            req.session().attribute("order_id", order.getId());
        }

        return new ModelAndView(params, "product/shopping_cart");
    }

    public static ModelAndView renderCheckOut(Request req, Response res) {
        Map params = new HashMap<>();

        return new ModelAndView(params, "/checkout");
    }

    public static ModelAndView saveCustomerDetails(Request req, Response res) throws IOException, URISyntaxException {
        OrderDao orderDao = new OrderDaoJDBC();

        Customer customer = setCustomer(req);
        setOrder(req, orderDao, customer);

        String destination = customer.getShippingAddress() + ", " +
                            customer.getShippingCity() +  ", " +
                            customer.getShippingZipcode() + " " +
                            customer.getShippingCountry();

        Map params = new HashMap<>();
        params.put("shippingOptions", shippingServiceController.getShippingCost(destination));
        params.put("destination", destination);
        params.put("origin", shippingServiceController.ORIGIN);
        return new ModelAndView(params, "shipping/shipping");
    }

    public static ModelAndView renderReviewPage(Request req, Response res) throws  URISyntaxException {
        Map params = new HashMap<>();
        ShippingCostCalculator chosenShippingOption = setShippingOption(req);
        Order order = req.session().attribute("order");
        int totalPrice = order.getTotalPrice() + chosenShippingOption.getCost();

        params.put("paymentServiceUrl", paymentServiceURI(totalPrice, order));
        params.put("option", chosenShippingOption);
        params.put("total", totalPrice);
        params.put("lineitems", order.getLineItems());

        return new ModelAndView(params, "review");
    }

    private static String paymentServiceURI(int totalPrice, Order order) throws URISyntaxException {
        URIBuilder serviceURIBuilder = new URIBuilder(PAYMENT_SERVICE_URI);
        serviceURIBuilder.addParameter("total", String.valueOf(totalPrice));
        serviceURIBuilder.addParameter("return-link", EMAIL_SERVICE_URI + order.getId());
        return serviceURIBuilder.build().toString();
    }

    public static ModelAndView renderPayment(Request req, Response res) {
        Map params = new HashMap<>();
        OrderDao orderDataStore = new OrderDaoJDBC();
        if (req.session().attribute("order_id") != null) {
            orderDataStore.setOrderStatus(req.session().attribute("order_id"), OrderStatus.PAID);
        }

        return new ModelAndView(params, "/payment");
    }

    private static ShippingCostCalculator setShippingOption (Request req) {
        ShippingCostCalculator chosenOption = null;
        for (ShippingCostCalculator option: ShippingCostCalculatorService.getInstance().getShippingOptions()) {
            if(option.getName().equals(req.queryParams("shipping-option"))) {
                chosenOption = option;
            }
        }
        return chosenOption;
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




}
