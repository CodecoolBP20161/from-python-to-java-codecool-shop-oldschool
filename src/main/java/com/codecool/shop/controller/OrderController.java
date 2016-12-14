package com.codecool.shop.controller;

import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.dao.DataStorageFactory;
import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;


public class OrderController {

    protected static CustomerDao customerDataStore = DataStorageFactory.customerDaoFactory();
    protected static OrderDao orderDataStore = DataStorageFactory.orderDaoFactory();
    protected static LineItemDao lineItemDataStore = DataStorageFactory.lineItemDaoFactory();

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private static final String SERVICE_URL = "http://localhost:60000";
    private static final String TO_ADDRESS_PARAM_KEY = "to";
    private static final String PASSWORD_PARAM_KEY = "password";
    private static final String FROM_ADDRESS_PARAM_KEY = "from";
    private static final String SUBJECT_PARAM_KEY = "subject";
    private static final String MESSAGE_PARAM_KEY = "message";

    public static ModelAndView renderEmail(Request req, Response res) throws IOException, URISyntaxException {
        getEmailService();
        Order order = orderDataStore.find(Integer.parseInt(req.params(":order-id")));

        return new ModelAndView(null, "product/shopping_cart");
    }

    public static String getEmailService() throws IOException, URISyntaxException {
        logger.info("Getting the EmailSenderService to send out necessary emails");

        URIBuilder builder = new URIBuilder(SERVICE_URL + "/email");
//        builder.addParameter(TO_ADDRESS_PARAM_KEY, to);
//        builder.addParameter(PASSWORD_PARAM_KEY, password);
//        builder.addParameter(FROM_ADDRESS_PARAM_KEY, from);
//        builder.addParameter(SUBJECT_PARAM_KEY, subject);
//        builder.addParameter(MESSAGE_PARAM_KEY, message);

        return execute(builder.build());
    }



    private static String execute(URI url) throws IOException, URISyntaxException {
        logger.debug("The URL built to send email with details: " + url);
        return org.apache.http.client.fluent.Request.Get(url)
                .execute()
                .returnContent()
                .asString();
    }
}
