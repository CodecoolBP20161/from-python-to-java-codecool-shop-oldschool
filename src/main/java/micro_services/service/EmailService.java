package micro_services.service;


import micro_services.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private static EmailService INSTANCE;

    public static EmailService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EmailService();
        }
        return INSTANCE;
    }

//    TODO: needs refactoring
    public void sendEmail(Email email) {

        logger.info("Sending emails...");

        Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email.getFromAddress(), email.getPassword());
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email.getFromAddress()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email.getToAddress()));
            message.setSubject(email.getSubject());
            message.setText(email.getMessage());

            Transport.send(message);

            logger.debug("Sending email from {} to {} with the subject: {}", email.getFromAddress(), email.getToAddress(), email.getSubject());

        } catch (MessagingException e){
            throw new RuntimeException(e);
        }
    }
}

