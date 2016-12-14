package micro_services.dao.implementation;


import micro_services.dao.EmailDao;
import micro_services.dao.implementation.DatabaseConnector;
import micro_services.model.Email;
import micro_services.model.EmailStatus;

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
                                            "message, " +
                                            "status) " +
                        "VALUES (" + email.getToAddress() + ", '" +
                                     email.getPassword() + "', '" +
                                     email.getFromAddress() + "', " +
                                     email.getSubject() + ", '" +
                                     email.getMessage() + "', " +
                                     email.getStatus() + ");";
        DatabaseConnector.executeQuery(query);
    }

    @Override
    public List<Email> getBy(EmailStatus status) {
        String query = "SELECT * FROM emails WHERE status ='" + status + "';";

        List<Email> emailList = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                Email email = new Email(
                        resultSet.getString("to_address"),
                        resultSet.getString("password"),
                        resultSet.getString("from_address"),
                        resultSet.getString("message"),
                        resultSet.getString("subject"),
                        resultSet.getString("status");

                emailList.add(email);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emailList;
        }

    }
}
