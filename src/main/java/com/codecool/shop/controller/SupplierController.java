package com.codecool.shop.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class SupplierController extends ShopController {

    public static ModelAndView renderProductsBySupplier(Request req, Response res) {
        return render(req, res);
    }

}
