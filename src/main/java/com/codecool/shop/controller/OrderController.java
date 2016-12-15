package com.codecool.shop.controller;

import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.database.CustomerDaoJDBC;
import com.codecool.shop.dao.implementation.database.LineItemDaoJDBC;
import com.codecool.shop.dao.implementation.database.OrderDaoJDBC;
import com.codecool.shop.model.Customer;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrderController {

    protected static CustomerDao customerDataStore = new CustomerDaoJDBC();
    protected static OrderDao orderDataStore = new OrderDaoJDBC();
    protected static LineItemDao lineItemDataStore = new LineItemDaoJDBC();
    static Map params = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private static final String SERVICE_URL = "http://localhost:60000";
    private static final String TO_ADDRESS_PARAM_KEY = "to";
    private static final String PASSWORD_PARAM_KEY = "password";
    private static final String FROM_ADDRESS_PARAM_KEY = "from";
    private static final String SUBJECT_PARAM_KEY = "subject";
    private static final String MESSAGE_PARAM_KEY = "message";


    public static ModelAndView renderEmail(Request req, Response res) throws IOException, URISyntaxException {

        Order order = orderDataStore.find(Integer.parseInt(req.params(":order-id")));
        Customer customer = customerDataStore.find(order.getCustomer().getId());
        List<LineItem> orderLineItems = lineItemDataStore.getBy(order);
        params = createEmailBody(customer, order, orderLineItems);
        getEmailService();

        return new ModelAndView(params, "/payment");
    }

    public static String getEmailService() throws IOException, URISyntaxException {
        logger.info("Getting the EmailSenderService to send out necessary emails");

        URIBuilder builder = new URIBuilder(SERVICE_URL + "/email");
        builder.addParameter(TO_ADDRESS_PARAM_KEY, String.valueOf(params.get("to")));
        builder.addParameter(PASSWORD_PARAM_KEY, String.valueOf(params.get("password")));
        builder.addParameter(FROM_ADDRESS_PARAM_KEY, String.valueOf(params.get("from")));
        builder.addParameter(SUBJECT_PARAM_KEY, String.valueOf(params.get("subject")));
        builder.addParameter(MESSAGE_PARAM_KEY, String.valueOf(params.get("message")));

        logger.debug("URI for email sending: ", builder);
        return execute(builder.build());
    }

    public static Map createEmailBody(Customer customer, Order order, List<LineItem> orderLineItems){
        Double totalPrice = 0d;
        Map emailParams = new HashMap<>();
        StringBuilder message = new StringBuilder();

        message.append("Dear " + customer.getName() + "\r\n");
        message.append("Your order: " +"\r\n");

        for (int i = 0; i < orderLineItems.size(); i++) {
            LineItem lineItem = orderLineItems.get(i);
            message.append("Product name: " + lineItem.getProduct().getName()+
                    "  Quantity: " + lineItem.getQuantity() +
                    "  DefaultPrice: " + lineItem.getProduct().getDefaultPrice()+ "\r\n");
            totalPrice += (double)lineItem.getSubtotalPrice();
        }

        message.append("Total price: " + totalPrice+ "\r\n");
        message.append("Best Regards, \r\n");
        message.append("Codecool");


        emailParams.put("to", customer.getEmail());
        emailParams.put("from", "girhes.cc.2016@gmail.com");
        emailParams.put("password", "Girhes2016");
        emailParams.put("subject", "New Order");
        emailParams.put("message", message.toString());
        return emailParams;
    }


    private static String execute(URI url) throws IOException, URISyntaxException {
        logger.debug("The URL built to send email with details: " + url);
        return org.apache.http.client.fluent.Request.Get(url)
                .execute()
                .returnContent()
                .asString();
    }
}
