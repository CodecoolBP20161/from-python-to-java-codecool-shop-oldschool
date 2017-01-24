package com.codecool.shop.controller;

import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static com.codecool.shop.controller.ShopController.productDataStore;

public class VideoController {
    public static ModelAndView getModal(Request req, Response res) {
        Map params = new HashMap();
        //TODO: null if not found, or exception if parseInt fails
        Product product = productDataStore.find(Integer.parseInt(req.params(":product-id")));

        return new ModelAndView(params, "product/video_modal");
    }
}
