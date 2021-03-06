package micro_services.email_sender.controller;


import micro_services.email_sender.dao.implementation.EmailDaoJDBC;
import micro_services.email_sender.model.Email;
import micro_services.email_sender.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class EmailSenderController {

    private static final Logger logger = LoggerFactory.getLogger(EmailSenderController.class);
    private final EmailService emailService;
    private final EmailDaoJDBC emailDatabase = new EmailDaoJDBC();

    public EmailSenderController(EmailService emailService) {
        this.emailService = emailService;
    }

    public String saveEmail(Request request, Response response) {
        logger.info("Adding emails to the database");

        emailDatabase.add(
                new Email(
                        request.queryParams("to"),
                        request.queryParams("password"),
                        request.queryParams("from"),
                        request.queryParams("subject"),
                        request.queryParams("message")
                ));

        return "Email saved to the database";
    }

    public String status(Request request, Response response) {
        return "ok";
    }
}
