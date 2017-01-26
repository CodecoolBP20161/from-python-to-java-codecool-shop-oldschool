package micro_services.email_sender.service;


import micro_services.email_sender.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private static EmailService INSTANCE;

    //    Default Gmail SMTP settings
    private static String MAIL_SMTP_AUTH = "true";
    private static String MAIL_SMTP_STARTTLS_ENABLE = "true";
    private static String MAIL_SMTP_HOST = "smtp.gmail.com";
    private static String MAIL_SMTP_PORT = "587";

    public static EmailService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EmailService();
        }
        return INSTANCE;
    }

    private Properties setProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", MAIL_SMTP_AUTH);
        props.put("mail.smtp.starttls.enable", MAIL_SMTP_STARTTLS_ENABLE);
        props.put("mail.smtp.host", MAIL_SMTP_HOST);
        props.put("mail.smtp.port", MAIL_SMTP_PORT);

        return props;
    }

    private Session getSession(Email email) {
        return Session.getInstance(setProperties(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        logger.info("Authentication for: ", email.getFromAddress(), email.getPassword());
                        return new PasswordAuthentication(email.getFromAddress(), email.getPassword());
                    }
                });
    }

    public void sendEmail(Email email) {
        logger.info("Sending emails...");
        logger.info("Trying to send an email from {} (pwd): {} to {} with the subject: {} and message: {}",
                email.getFromAddress(), email.getPassword(), email.getToAddress(), email.getSubject(), email.getMessage());

        try {
            Message message = new MimeMessage(getSession(email));
            message.setFrom(new InternetAddress(email.getFromAddress()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email.getToAddress()));
            message.setSubject(email.getSubject());
            message.setText(email.getMessage());

            Transport.send(message);

            logger.debug("Sending message {}", message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

