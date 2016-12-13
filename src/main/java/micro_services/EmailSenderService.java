package micro_services;


import micro_services.controller.EmailSenderController;
import micro_services.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

import static spark.Spark.*;

public class EmailSenderService {

    private static final Logger logger = LoggerFactory.getLogger(EmailSenderService.class);

    private EmailSenderController controller;

    public static void main(String[] args) {
        logger.debug("Starting " + EmailSenderService.class.getName() + "...");

        port(60000);

        EmailSenderService application = new EmailSenderService();

        application.controller = new EmailSenderController(EmailService.getInstance());

        // --- MAPPINGS ---
        get("/status", application.controller::status);
        post("/api/email", application.controller::saveEmail);

        // --- EXCEPTION HANDLING ---
        exception(URISyntaxException.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("URI building error, maybe wrong format? : %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("Unexpected error occurred: %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });
    }
}
