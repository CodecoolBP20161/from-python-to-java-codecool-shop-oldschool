package com.codecool.shop.controller;


import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Orderable;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderController extends ShopController {

    public static ModelAndView renderOrder(Request req, Response res) {

        // check if session contains order, instantiate if it doesn't
        Orderable order = req.session().attribute("order");
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
}
