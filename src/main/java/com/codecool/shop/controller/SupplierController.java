package com.codecool.shop.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class SupplierController extends ShopController {

    public static ModelAndView renderProductsBySupplier(Request req, Response res) {
        //call the ShopController abstract class method for filter items
        return render(req, res);
    }

}
