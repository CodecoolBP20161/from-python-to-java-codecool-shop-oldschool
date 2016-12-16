package micro_services.email_sender.dao;


import micro_services.email_sender.model.Email;
import micro_services.email_sender.model.EmailStatus;

import java.util.List;


public interface EmailDao {

    void add(Email email);
    List<Email> getBy(EmailStatus status);
    void changeStatus(EmailStatus status, Email email);

}
