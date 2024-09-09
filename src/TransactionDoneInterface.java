import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TransactionDoneInterface extends JFrame {
    private JLabel balanceAmountLabel;

    public TransactionDoneInterface(String customerName){
        // Set up the JFrame
        setTitle("Money Bank - Customer Transaction Done Interface");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Set background color for JFrame
        getContentPane().setBackground(new Color(25, 20, 50));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 800, 80);
        headerPanel.setBackground(new Color(25, 20, 50));
        headerPanel.setLayout(null);
        add(headerPanel);

        // Bank name
        JLabel bankNameLabel = new JLabel("MONEY BANK");
        bankNameLabel.setBounds(30, 20, 300, 40);
        bankNameLabel.setFont(new Font("Algerian", Font.BOLD, 30));
        bankNameLabel.setForeground(new Color(252, 203, 45));
        headerPanel.add(bankNameLabel);

        // Welcome Label
        JLabel welcomeLabel = new JLabel("WELCOME");
        welcomeLabel.setBounds(400, 20, 150, 40);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(252, 203, 45)); // Gold color
        headerPanel.add(welcomeLabel);

        // Log Out Button
        JButton logoutButton = new JButton("LOG OUT");
        logoutButton.setBounds(700, 25, 80, 30); // Adjusted the button size and position
        logoutButton.setForeground(new Color(28, 0, 56));
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        headerPanel.add(logoutButton);

        // Add action listener to the button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose current frame
                dispose();

                // Open SavingAccountInterface
                new BankLoginInterface();
            }
        });

        // Hello Label with dynamic customer name
        JLabel helloLabel = new JLabel("HELLO : " + customerName);
        helloLabel.setBounds(20, 60, 200, 30);
        helloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        helloLabel.setForeground(Color.WHITE);
        headerPanel.add(helloLabel);

        // Account Balance Label
        JLabel balanceLabel = new JLabel("YOUR ACCOUNT BALANCE :");
        balanceLabel.setBounds(20, 100, 200, 30);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        balanceLabel.setForeground(Color.WHITE);
        add(balanceLabel);

        balanceAmountLabel = new JLabel();
        balanceAmountLabel.setBounds(220, 100, 100, 30);
        balanceAmountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        balanceAmountLabel.setForeground(Color.WHITE);
        add(balanceAmountLabel);

        // Thank you Label
        JLabel thankYouLabel = new JLabel("Thank you for banking with MONEY BANK... Have a nice day");
        thankYouLabel.setBounds(100, 500, 600, 40);
        thankYouLabel.setFont(new Font("Algerian", Font.BOLD | Font.ITALIC, 18));
        thankYouLabel.setForeground(new Color(255, 255, 255)); // White color
        add(thankYouLabel);


        // Image Panel (Right side)
        JLabel imageLabel = new JLabel(new ImageIcon("BankLogin.jpg"));
        imageLabel.setBounds(360, 160, 400, 300);
        add(imageLabel);

        // Display the JFrame
        setVisible(true);
    }

    public TransactionDoneInterface() {

    }

    // Method to get the balance from the database
    private String getBalanceFromDatabase(String customerName) {
        String balance = "0.00";
        try {
            // Assuming you're using MySQL, adjust the connection string accordingly
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/moneybank", "root", "root");
            String sql = "SELECT Balance FROM transaction WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customerName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                balance = rs.getString("Balance");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return balance;
    }

//    public static void main(String[] args){
//        new TransactionDoneInterface("");
//    }
}
