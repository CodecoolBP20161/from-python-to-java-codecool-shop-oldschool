package micro_services.dao;


import micro_services.model.Email;
import micro_services.model.EmailStatus;

import java.util.List;


public interface EmailDao {

    void add(Email email);
    List<Email> getBy(EmailStatus status);

}
