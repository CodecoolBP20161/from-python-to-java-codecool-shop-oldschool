package com.codecool.shop.controller;


import spark.Request;
import spark.Response;
import spark.ModelAndView;

public class ProductController extends ShopController {

    public static ModelAndView renderProducts(Request req, Response res) {
        //call the ShopController abstract class method for filter items
        return render(req, res);
    }

}
