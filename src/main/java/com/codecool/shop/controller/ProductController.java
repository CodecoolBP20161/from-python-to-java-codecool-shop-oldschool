package com.codecool.shop.controller;


import spark.Request;
import spark.Response;
import spark.ModelAndView;

public class ProductController extends ShopController {

    public static ModelAndView renderProducts(Request req, Response res) {
        req.session().attribute("path",req.pathInfo());
        return render(req, res);
    }

}
