package com.codecool.shop.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ProductCategoryController extends ShopController {

    public static ModelAndView renderProductsByCategory(Request req, Response res) {
        //call the ShopController abstract class method for filter items
        return render(req, res);
    }

}
