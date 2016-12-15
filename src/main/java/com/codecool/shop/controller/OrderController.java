package com.codecool.shop.controller;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private static final String SERVICE_URL = "http://localhost:60000";
    private static final String TO_ADDRESS_PARAM_KEY = "to";
    private static final String PASSWORD_PARAM_KEY = "password";
    private static final String FROM_ADDRESS_PARAM_KEY = "from";
    private static final String SUBJECT_PARAM_KEY = "subject";
    private static final String MESSAGE_PARAM_KEY = "message";

    public static ModelAndView redirectToEmailService(spark.Request request, spark.Response response) throws IOException, URISyntaxException {
        getEmailService();
        Map params = new HashMap<>();
        return new ModelAndView(params, "/payment");
    }


    public static String getEmailService() throws IOException, URISyntaxException {
        logger.info("Getting the EmailSenderService to send out necessary emails");

        URIBuilder builder = new URIBuilder(SERVICE_URL + "/email");
//        builder.addParameter(TO_ADDRESS_PARAM_KEY, to);
//        builder.addParameter(PASSWORD_PARAM_KEY, password);
//        builder.addParameter(FROM_ADDRESS_PARAM_KEY, from);
//        builder.addParameter(SUBJECT_PARAM_KEY, subject);
//        builder.addParameter(MESSAGE_PARAM_KEY, message);

        logger.debug("URI for email sending: ", builder);

        return execute(builder.build());
    }

    private static String execute(URI url) throws IOException, URISyntaxException {
        logger.debug("The URL built to send email with details: " + url);
        return Request.Get(url)
                .execute()
                .returnContent()
                .asString();
    }
}
