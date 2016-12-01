package com.codecool.shop.controller;


import com.codecool.shop.model.Customer;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.OrderInterface;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class OrderController extends ShopController {

    public static ModelAndView renderOrder(Request req, Response res) {

        // check if session contains order, instantiate if it doesn't
        OrderInterface order = req.session().attribute("order");
        if (order == null) {
            order = new Order();
            req.session().attribute("order", order);
        }

        // find product by product_id from request
        Product orderedProduct = productDataStore.find(Integer.parseInt(req.params(":product-id")));
        // add lineitem to the order
        order.addProduct(orderedProduct);
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
        Order order = req.session().attribute("order");
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
        order.setCustomer(customer);
        res.redirect("/payment");
        // fixme: what to return here???
        return new ModelAndView(params, "/payment");
    }

    public static ModelAndView renderPayment(Request req, Response res) {
        Map params = new HashMap<>();

        return new ModelAndView(params, "/payment");
    }


}
