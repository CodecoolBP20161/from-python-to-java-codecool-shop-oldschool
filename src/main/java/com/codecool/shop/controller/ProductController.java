package com.codecool.shop.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ProductController extends ShopController {

    public static ModelAndView renderProducts(Request req, Response res) {

        return render(req, res);
    }

}
