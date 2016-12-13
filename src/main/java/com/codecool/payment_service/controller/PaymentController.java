package com.codecool.payment_service.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class PaymentController {
    public static ModelAndView renderPaymentPage(Request req, Response res) {
        Map params = new HashMap<>();
        return new ModelAndView(params, "payment");
    }
}
