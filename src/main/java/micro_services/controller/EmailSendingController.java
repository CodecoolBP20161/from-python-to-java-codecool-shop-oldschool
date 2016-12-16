package micro_services.controller;


import micro_services.dao.implementation.EmailDaoJDBC;
import micro_services.model.Email;
import micro_services.model.EmailStatus;
import micro_services.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class EmailSendingController {
    private static final EmailService emailService = EmailService.getInstance();

    private static final EmailDaoJDBC emailDatabase = new EmailDaoJDBC();

    private static final Logger logger = LoggerFactory.getLogger(EmailSenderController.class);


    public static void sendEmail() {
        logger.debug("Mails that should be sent out: ", emailDatabase.getBy(EmailStatus.IN_PROGRESS));

        for (Email email : emailDatabase.getBy(EmailStatus.IN_PROGRESS)) {

                emailService.sendEmail(email);
                emailDatabase.changeStatus(EmailStatus.SENT, email);

            logger.info("After sending the email, its status is: ", email.getStatus());
        }
    }

}
