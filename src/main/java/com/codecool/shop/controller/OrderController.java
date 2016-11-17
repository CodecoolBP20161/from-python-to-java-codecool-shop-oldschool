package com.codecool.shop.controller;


import com.codecool.shop.model.Order;
import com.codecool.shop.model.Orderable;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class OrderController extends ShopController {

    public static ModelAndView renderOrder(Request req, Response res) {
        //check if session contains order, intstntiate if no
        Orderable order = req.session().attribute("order");
        if (order == null) {
            order = new Order();
            req.session().attribute("order", order);
        }
        //find product by product id
        Product orderedProduct = productDataStore.find(Integer.parseInt(req.params(":product-id")));
        order.addProduct(orderedProduct);
        //redirect to the last path
        res.redirect(req.session().attribute("path"));
        return render(req, res);
    }
}
