package micro_services.email_sender.dao.implementation;


import micro_services.email_sender.dao.EmailDao;
import micro_services.email_sender.model.Email;
import micro_services.email_sender.model.EmailStatus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmailDaoJDBC implements EmailDao {

    @Override
    public void add(Email email) {
        String query = "INSERT INTO emails (to_address, " +
                "password, " +
                "from_address, " +
                "subject, " +
                "message) " +
                "VALUES ('" + email.getToAddress() + "', '" +
                email.getPassword() + "', '" +
                email.getFromAddress() + "', '" +
                email.getSubject() + "', '" +
                email.getMessage() + "');";

        DatabaseConnector.executeQuery(query);
    }

    @Override
    public List<Email> getBy(EmailStatus status) {
        String query = "SELECT * FROM emails WHERE email_status ='" + status + "';";

        List<Email> emailList = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                Email email = new Email(
                        resultSet.getString("to_address"),
                        resultSet.getString("password"),
                        resultSet.getString("from_address"),
                        resultSet.getString("subject"),
                        resultSet.getString("message"));

                email.setStatus(status);
                emailList.add(email);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emailList;
    }

    @Override
    public void changeStatus(EmailStatus status, Email email) {
        String query = "UPDATE emails " +
                "SET email_status = '" + status + "' " +
                "WHERE to_address = '" + email.getToAddress() + "' AND " +
                "subject = '" + email.getSubject() + "';";
        System.out.println("query = " + query);
        DatabaseConnector.executeQuery(query);
    }
}
