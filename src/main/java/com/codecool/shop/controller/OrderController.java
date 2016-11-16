package com.codecool.shop.controller;


import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Orderable;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class OrderController extends ShopController {

    public static ModelAndView renderOrder(Request req, Response res) {
        Orderable order = new Order();
        req.params(":product-id");

        Product orderedProduct = productDataStore.find(Integer.parseInt(req.params(":product-id")));
        order.addProduct(orderedProduct);

        if (req.session().attribute("order") == null){
            req.session().attribute("order", order);
        }

        return render(req, res);
    }
}
