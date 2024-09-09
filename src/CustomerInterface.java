import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerInterface extends JFrame {
    private JTextField depositTextField;
    private JLabel balanceAmountLabel;
    private JTextField transferAmountTextField;
    private JTextField transferAccountNoTextField;

    public CustomerInterface(String customerName) {
        setTitle("Money Bank - Customer Interface");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(25, 20, 50));

        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1000, 80);
        headerPanel.setBackground(new Color(25, 20, 50));
        headerPanel.setLayout(null);
        add(headerPanel);

        JLabel bankNameLabel = new JLabel("MONEY BANK");
        bankNameLabel.setBounds(30, 20, 300, 40);
        bankNameLabel.setFont(new Font("Algerian", Font.BOLD, 30));
        bankNameLabel.setForeground(new Color(252, 203, 45));
        headerPanel.add(bankNameLabel);

        JLabel welcomeLabel = new JLabel("WELCOME");
        welcomeLabel.setBounds(480, 20, 150, 40);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(252, 203, 45));
        headerPanel.add(welcomeLabel);

        JLabel helloLabel = new JLabel("HELLO : " + customerName);
        helloLabel.setBounds(20, 60, 200, 30);
        helloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        helloLabel.setForeground(Color.WHITE);
        headerPanel.add(helloLabel);

        JLabel thankYouLabel = new JLabel("Thank you for banking with MONEY BANK... Have a nice day");
        thankYouLabel.setBounds(180, 600, 600, 40);
        thankYouLabel.setFont(new Font("Algerian", Font.BOLD | Font.ITALIC, 18));
        thankYouLabel.setForeground(new Color(255, 255, 255));
        add(thankYouLabel);

        JButton logoutButton = new JButton("LOG OUT");
        logoutButton.setBounds(800, 25, 80, 30);
        logoutButton.setForeground(new Color(28, 0, 56));
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        headerPanel.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose current frame
                dispose();

                // Open BankLoginInterface (log back in)
                new BankLoginInterface();
            }
        });

        JLabel balanceLabel = new JLabel("YOUR ACCOUNT BALANCE :");
        balanceLabel.setBounds(20, 120, 200, 30);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        balanceLabel.setForeground(Color.WHITE);
        add(balanceLabel);

        balanceAmountLabel = new JLabel();
        balanceAmountLabel.setBounds(220, 120, 100, 30);
        balanceAmountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        balanceAmountLabel.setForeground(Color.WHITE);
        add(balanceAmountLabel);

        String balance = getBalanceFromDatabase(customerName);
        balanceAmountLabel.setText(balance);

        depositTextField = addFormRow("DEPOSIT", 20, 200, 200, 200);
        transferAmountTextField = addFormRow("TRANSFER AMOUNT", 20, 250, 200, 250);
        transferAccountNoTextField = addFormRow("TRANSFER ACCOUNT NO", 20, 300, 200, 300);

        JLabel imageLabel = new JLabel(new ImageIcon("BankLogin.jpg"));
        imageLabel.setBounds(360, 160, 550, 400);
        add(imageLabel);

        JButton proceedButton = new JButton("Proceed Your Transaction");
        proceedButton.setBounds(100, 400, 200, 30);
        add(proceedButton);

        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double depositAmount = Double.parseDouble(depositTextField.getText());
                    boolean success = DatabaseManager.updateBalanceAndSaveTransaction(customerName, depositAmount);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Transaction successful!");
                        balanceAmountLabel.setText(getBalanceFromDatabase(customerName));
                    } else {
                        JOptionPane.showMessageDialog(null, "Transaction failed!");
                    }
                    // Handle transfer
                    double transferAmount = Double.parseDouble(transferAmountTextField.getText());
                    String transferAccountNo = transferAccountNoTextField.getText();
                    boolean transferSuccess = DatabaseManager.transferAmount(customerName, transferAmount, transferAccountNo);
                    if (transferSuccess) {
                        JOptionPane.showMessageDialog(null, "Transfer successful!");
                        balanceAmountLabel.setText(getBalanceFromDatabase(customerName));
                    } else {
                        JOptionPane.showMessageDialog(null, "Transfer failed!");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid deposit amount.");
                }
            }
        });

        setVisible(true);
    }

    private JTextField addFormRow(String labelText, int labelX, int labelY, int fieldX, int fieldY) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.YELLOW);
        label.setBounds(labelX, labelY, 200, 30);
        add(label);

        JTextField textField = new JTextField();
        textField.setBounds(fieldX, fieldY, 150, 30);
        add(textField);
        return textField;
    }

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
                balance = rs.getString("Balance"); //Display available balance
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return balance;
    }

//    public static void main(String[] args) {
//        new CustomerInterface("");  // Replace with actual username from login
//    }
}
