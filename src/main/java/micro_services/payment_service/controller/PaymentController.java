package micro_services.payment_service.controller;

import org.apache.http.client.utils.URIBuilder;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class PaymentController {
    static String REDIRECT_LINK;

    public static ModelAndView renderPaymentPage(Request req, Response res) throws URISyntaxException {
        REDIRECT_LINK = req.queryParams("return-link");
        URI return_link = new URIBuilder(req.queryParams("return-link")).build();
        Map params = new HashMap<>();
        params.put("total", req.queryParams("total"));
        params.put("return-link", return_link.toASCIIString());
        return new ModelAndView(params, "payment");
    }

    public static ModelAndView handlePayment(Request req, Response res) throws URISyntaxException {
        URI return_link = new URIBuilder(REDIRECT_LINK).build();
        Map params = new HashMap<>();
        res.redirect(return_link.toASCIIString());
        return new ModelAndView(params, "payment");

    }
}
