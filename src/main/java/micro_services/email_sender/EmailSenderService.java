package micro_services.email_sender;


import micro_services.email_sender.controller.EmailSenderController;
import micro_services.email_sender.controller.EmailSendingController;
import micro_services.email_sender.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

import static spark.Spark.*;


public class EmailSenderService {

    private static final Logger logger = LoggerFactory.getLogger(EmailSenderService.class);

    private static final Timer timer = new Timer();

    private EmailSenderController controller;

    public static void main(String[] args) {
        logger.debug("Starting " + EmailSenderService.class.getName() + "...");

        port(60000);

        EmailSenderService application = new EmailSenderService();

        application.controller = new EmailSenderController(EmailService.getInstance());

        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                System.out.println("every secound");
                EmailSendingController.sendEmail();
            }
        };

        timer.schedule(task, 1000, 1000);

        // --- MAPPINGS ---
        get("/status", application.controller::status);
        get("/email", application.controller::saveEmail);

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
