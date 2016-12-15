package com.codecool.payment_service.controller;

import org.apache.http.client.utils.URIBuilder;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class PaymentController {
    public static ModelAndView renderPaymentPage(Request req, Response res) throws URISyntaxException {
        URI return_link = new URIBuilder(req.queryParams("return-link")).build();

        Map params = new HashMap<>();
        params.put("total", req.queryParams("total"));
        return new ModelAndView(params, "payment");
    }
}
