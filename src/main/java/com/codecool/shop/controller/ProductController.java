package com.codecool.shop.controller;


import spark.Request;
import spark.Response;
import spark.ModelAndView;

public class ProductController extends ShopController {

    public static ModelAndView renderProducts(Request req, Response res) {
        return render(req, res);
    }

}
