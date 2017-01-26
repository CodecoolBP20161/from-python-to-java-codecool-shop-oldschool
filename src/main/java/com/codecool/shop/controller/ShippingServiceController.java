package com.codecool.shop.controller;


import com.codecool.shop.model.ShippingCostCalculator;
import com.codecool.shop.services.ShippingCostCalculatorService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class ShippingServiceController {
    private ShippingCostCalculatorService shippingCostCalculatorService;
    public static final String ORIGIN = "Nagymező u. 44, Budapest, 1065 Hungary";

    public ShippingServiceController(ShippingCostCalculatorService shippingCostCalculatorService) {
        this.shippingCostCalculatorService = shippingCostCalculatorService;
    }

    public List<ShippingCostCalculator> getShippingCost(String destination) throws IOException, URISyntaxException{
        return shippingCostCalculatorService.getShippingCost(destination, ORIGIN);
    }
}
