package micro_services.email_sender.dao.implementation;


import micro_services.email_sender.dao.EmailDao;
import micro_services.email_sender.model.Email;
import micro_services.email_sender.model.EmailStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static micro_services.email_sender.dao.implementation.DatabaseConnector.getConnection;

public class EmailDaoJDBC implements EmailDao {

    @Override
    public void add(Email email) {
        String query = "INSERT INTO emails (to_address, " +
                "password, " +
                "from_address, " +
                "subject, " +
                "message) " +
                "VALUES (?, ?, ?, ?, ?);";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email.getToAddress());
            preparedStatement.setString(2, email.getPassword());
            preparedStatement.setString(3, email.getFromAddress());
            preparedStatement.setString(4, email.getSubject());
            preparedStatement.setString(5, email.getMessage());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Email> getBy(EmailStatus status) {
        String query = "SELECT * FROM emails WHERE email_status =CAST(? AS email_status);";

        List<Email> emailList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, status.name());
            ResultSet resultSet = preparedStatement.executeQuery();
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
                "SET email_status = CAST(? AS email_status)" +
                "WHERE to_address = ? AND " +
                "subject = ?;";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, status.name());
            preparedStatement.setString(2, email.getToAddress());
            preparedStatement.setString(3, email.getSubject());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
