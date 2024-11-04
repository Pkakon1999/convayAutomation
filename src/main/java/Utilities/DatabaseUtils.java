package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseUtils {

    // Method to fetch the password reset link from the database
    public static String fetchResetLink(String email) {
        String resetLink = null;

        // Database connection details
        String jdbcURL = "jdbc:mysql://meet2.synesisit.info:35559/organization_settings_v2";
        String dbUser = "conVeyDevloperUse1ly";
        String dbPassword = "iZTlLI6ujUkvAul4hW";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Step 1: Establish the database connection
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Step 2: Create a statement
            statement = connection.createStatement();

            // Step 3: Execute the SQL query to fetch the reset token based on email
            String query = "SELECT id, activation_link FROM organization_settings_v2.sit_sign_up_user where sit_sign_up_user.user_login ='kakon.synesisit@gmail.com'";
            resultSet = statement.executeQuery(query);

            // Step 4: Process the result
            if (resultSet.next()) {
                String token = resultSet.getString("reset_token");

                // Construct the full reset link (assuming a standard format)
                resetLink = "https://convay.com/reset-password?token=" + token;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resetLink;
    }
}
