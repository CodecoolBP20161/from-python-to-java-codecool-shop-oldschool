package micro_services.controller;


import micro_services.dao.implementation.EmailDaoJDBC;
import micro_services.model.Email;
import micro_services.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.*;

public class EmailServiceController {

    private final EmailService emailService;

    private final EmailDaoJDBC emailDatabase = new EmailDaoJDBC();

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceController.class);

    public EmailServiceController(EmailService emailService){
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

    public void sendEmail() {
        for (Email email : emailDatabase.getBy(EmailStatus.IN_PROGRESS)) {
            emailService.sendEmail(email);
        }

        logger.debug("Mails that should be sent out: ", emailDatabase.getBy(EmailStatus.IN_PROGRESS));
    }

    public String status(Request request, Response response) {
        return "ok";
    }
}
