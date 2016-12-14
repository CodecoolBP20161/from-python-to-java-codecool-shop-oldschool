package micro_services.dao;


import micro_services.model.Email;
import micro_services.model.EmailStatus;


public interface EmailDao {

    void add(Email email);
    void getBy(EmailStatus status);

}
