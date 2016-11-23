package com.codecool.shop.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
//TODO:practice jdbc test and thymeleaf
public class ProductCategoryController extends ShopController {

    public static ModelAndView renderProductsByCategory(Request req, Response res) {
        //call the ShopController abstract class method for filter items
        return render(req, res);
    }

}
