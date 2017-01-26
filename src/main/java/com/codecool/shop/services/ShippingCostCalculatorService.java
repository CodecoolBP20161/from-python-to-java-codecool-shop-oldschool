package com.codecool.shop.services;


import com.codecool.shop.model.ShippingCostCalculator;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ShippingCostCalculatorService {
    private static final Logger logger = LoggerFactory.getLogger(ShippingCostCalculatorService.class);
    private static final String SERVICE_URL = "http://localhost:65011/shipping-cost";
    private static final String ORIGIN_PARAM_KEY = "origin";
    private static final String DESTINATION_PARAM_KEY = "destination";
    private static ShippingCostCalculatorService INSTANCE;
    private List<ShippingCostCalculator> shippingOptions;

    public static ShippingCostCalculatorService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ShippingCostCalculatorService();
        }
        return INSTANCE;
    }

    public List<ShippingCostCalculator> getShippingCost(String destination, String origin) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(SERVICE_URL);

        builder.addParameter(ORIGIN_PARAM_KEY, origin);
        builder.addParameter(DESTINATION_PARAM_KEY, destination);

        logger.info("Getting shipping cost options");
        logger.debug("URI built for getting shipping cost options {} ", builder.build());

        this.shippingOptions = new ArrayList<>();

        try {
            shippingOptions.add(shippingCostModelFromJSON(builder.build(), "expressCourier"));
            shippingOptions.add(shippingCostModelFromJSON(builder.build(), "truck"));
            shippingOptions.add(shippingCostModelFromJSON(builder.build(), "truckViaHighway"));
            shippingOptions.add(shippingCostModelFromJSON(builder.build(), "timeMachine"));
        } catch (JSONException ex) {
            logger.error("JSONException found {}", ex.getMessage());
        }

        logger.debug("Shipping options {}", shippingOptions);
        return shippingOptions;
    }

    private ShippingCostCalculator shippingCostModelFromJSON(URI uri, String option) throws IOException, URISyntaxException, JSONException {
        JSONObject shippingOption = getResponseFromShippingCostCalculatorService(uri).getJSONObject(option);
        return new ShippingCostCalculator(
                option,
                shippingOption.getString("destinationFound"),
                shippingOption.getInt("cost"),
                shippingOption.getString("originFound"),
                shippingOption.getString("details"),
                shippingOption.getString("currency"),
                shippingOption.getInt("timeInHours"),
                shippingOption.getInt("distanceInKm"));
    }

    private JSONObject getResponseFromShippingCostCalculatorService(URI uri) throws IOException, URISyntaxException, JSONException {
        return new JSONObject(execute(uri));
    }

    private String execute(URI uri) throws IOException, URISyntaxException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }

    public List<ShippingCostCalculator> getShippingOptions() {
        return shippingOptions;
    }
}
