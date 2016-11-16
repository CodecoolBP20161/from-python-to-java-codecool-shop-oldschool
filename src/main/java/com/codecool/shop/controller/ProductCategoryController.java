package com.codecool.shop.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ProductCategoryController extends ShopController {

    public static ModelAndView renderProductsByCategory(Request req, Response res) {
        req.session().attribute("path",req.pathInfo());
        return render(req, res);
    }

}
