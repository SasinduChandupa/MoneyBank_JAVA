import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3308/moneybank";
            String user = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Customer Login
    public static boolean validateCustomerLogin(String username, String password) {
        if (connection == null) {
            System.out.println("Database connection is null");
            return false;
        }

        String query = "SELECT * FROM tbllogin WHERE username = ? AND password = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Admin Login
    public static boolean validateAdminLogin(String username, String password) {
        if (connection == null) {
            System.out.println("Database connection is null");
            return false;
        }

        String query = "SELECT * FROM Adminlogin WHERE username = ? AND password = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // If a record is found, return true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Create saving Acc
    public static boolean saveSavingAccount(SavingAccount account) {
        String query = "INSERT INTO SavingAcc (IniName, FullName, Address, NIC, Purpose, UserName) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, account.getIniName());
            statement.setString(2, account.getFullName());
            statement.setString(3, account.getAddress());
            statement.setLong(4, account.getNic());
            statement.setString(5, account.getPurpose());
            statement.setString(6, account.getUserName());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean saveLogin(Login login) {
        String query = "INSERT INTO tbllogin (username, password) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login.getUserName());
            statement.setString(2, login.getPassword());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateBalanceAndSaveTransaction(String username, double depositAmount) {
        if (connection == null) {
            System.out.println("Database connection is null");
            return false;
        }

        try {
            // Start a transaction
            connection.setAutoCommit(false);

            // current balance
            String selectQuery = "SELECT Balance FROM transaction WHERE username = ?";
            PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
            selectStmt.setString(1, username);
            ResultSet rs = selectStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("User not found.");
                return false;
            }

            double currentBalance = rs.getDouble("Balance");

            // Add deposit amount to current balance
            double newBalance = currentBalance + depositAmount;

            // Update the balance in the database
            String updateQuery = "UPDATE transaction SET Balance = ? WHERE username = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
            updateStmt.setDouble(1, newBalance);
            updateStmt.setString(2, username);

            int rowsUpdated = updateStmt.executeUpdate();
            if (rowsUpdated > 0) {
                connection.commit();
                System.out.println("Balance updated successfully.");
                return true;
            } else {
                connection.rollback();
                System.out.println("Failed to update balance.");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean transferAmount(String username, double transferAmount, String transferAccountNo) {
        if (connection == null) {
            System.out.println("Database connection is null");
            return false;
        }

        try {
            connection.setAutoCommit(false);

            //current balance
            String selectQuery = "SELECT Balance FROM transaction WHERE username = ?";
            PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
            selectStmt.setString(1, username);
            ResultSet rs = selectStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("User not found.");
                return false;
            }

            double currentBalance = rs.getDouble("Balance");

            // Check current balance is sufficient for the transfer
            if (currentBalance < transferAmount) {
                System.out.println("Insufficient funds.");
                return false;
            }

            double newBalance = currentBalance - transferAmount;

            // Update the balance and save the last transaction account number
            String updateQuery = "UPDATE transaction SET Balance = ?, LastTransactionAccNo = ? WHERE username = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
            updateStmt.setDouble(1, newBalance);
            updateStmt.setString(2, transferAccountNo);
            updateStmt.setString(3, username);

            int rowsUpdated = updateStmt.executeUpdate();
            if (rowsUpdated > 0) {
                connection.commit();
                System.out.println("Transfer successful.");
                return true;
            } else {
                connection.rollback();
                System.out.println("Failed to update balance.");
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}