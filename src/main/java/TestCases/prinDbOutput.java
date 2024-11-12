package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class prinDbOutput {

    // Method to fetch activation link from the database
    public static String fetchActivationLink(String email) {
        String activationLink = null;

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

            // Step 3: Execute the SQL query to fetch the activation link based on the provided email
            String query = "SELECT activation_link FROM sit_sign_up_user WHERE sign_up_email = '" + email + "'";
            resultSet = statement.executeQuery(query);

            // Step 4: Process the result
            if (resultSet.next()) {
                activationLink = resultSet.getString("activation_link");
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

        return activationLink;
    }

    // Method to fetch email from Excel
    public static String getEmailFromExcel() {
        String email = null;
        try {
            // Excel file location
            File excelFile = new File("TestData\\TestDataFile.xlsx");
            FileInputStream fis = new FileInputStream(excelFile);

            // Load the workbook
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            // Load the signup sheet (assuming it's at index 1)
            XSSFSheet signUpSheet = workbook.getSheetAt(1);

            // Read email from the first row, first column (adjust the index if needed)
            email = signUpSheet.getRow(1).getCell(0).toString();

            // Close workbook and file input stream
            workbook.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return email;
    }

    public static void main(String[] args) {
        // Fetch email from Excel
        String email = getEmailFromExcel();

        // Fetch activation link from the database using the email
        String activationLink = fetchActivationLink(email);
        System.out.println("Activation Link: " + activationLink);
    }
}
