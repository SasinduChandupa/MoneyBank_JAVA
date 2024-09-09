import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CurrentAccountInterface extends JFrame {

    private JTextField iniNameField, fullNameField, addressField, nicField, userNameField, depositAmountField;
    private JPasswordField passwordField;
    private JComboBox<String> purposeComboBox;

    public CurrentAccountInterface() {
        // Set up the JFrame
        setTitle("Money Bank - Current Account");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(28, 0, 56));

        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1000, 80);
        headerPanel.setBackground(new Color(28, 0, 56));
        headerPanel.setLayout(null);
        add(headerPanel);

        JLabel bankNameLabel = new JLabel("MONEY BANK");
        bankNameLabel.setBounds(30, 20, 300, 40);
        bankNameLabel.setFont(new Font("Algerian", Font.BOLD, 30));
        bankNameLabel.setForeground(Color.WHITE);
        headerPanel.add(bankNameLabel);

        JLabel welcomeLabel = new JLabel("WELCOME");
        welcomeLabel.setBounds(500, 20, 150, 40);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(252, 203, 45));
        headerPanel.add(welcomeLabel);

        JButton backButton = new JButton("Go back");
        backButton.setBounds(870, 20, 100, 40);
        backButton.setBackground(new Color(28, 0, 56));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBorder(BorderFactory.createEmptyBorder());
        headerPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose current frame
                dispose();

                // Open BankDashboardInterface
                String username = "";
                new BankDashboardInterface(username);
            }
        });

        JLabel currentAccountLabel = new JLabel("CURRENT ACCOUNT");
        currentAccountLabel.setBounds(30, 100, 280, 40);
        currentAccountLabel.setFont(new Font("Algerian", Font.BOLD, 26));
        currentAccountLabel.setForeground(new Color(252, 203, 45));
        add(currentAccountLabel);

        JButton viewAllCurrentButton = new JButton("View All current Account ➔");
        viewAllCurrentButton.setBounds(700, 100, 250, 30);
        viewAllCurrentButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewAllCurrentButton.setBackground(Color.WHITE);
        viewAllCurrentButton.setForeground(new Color(28, 0, 56));
        viewAllCurrentButton.setBorder(BorderFactory.createEmptyBorder());
        add(viewAllCurrentButton);

        viewAllCurrentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose the current frame
                dispose();

                // Open AccViewInterface to display saving accounts
                new AccViewInterface();
            }
        });

        iniNameField = addFormRow("Name with initials:", 30, 160);
        fullNameField = addFormRow("Full Name:", 30, 200);
        addressField = addFormRow("Address:", 30, 240);
        nicField = addFormRow("NIC:", 30, 280);
        userNameField = addFormRow("User Name:", 30, 360);
        passwordField = addPasswordRow("Password:", 30, 400);
        depositAmountField = addFormRow("Deposit Amount:", 30, 440);
        purposeComboBox = addDropdownRow("Purpose:", 30, 320);

        JLabel imageLabel = new JLabel(new ImageIcon("BankLogin.jpg"));
        imageLabel.setBounds(350, 160, 600, 400);
        add(imageLabel);

        JButton confirmButton = new JButton("Create");
        confirmButton.setBounds(750, 600, 80, 30);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.setForeground(new Color(28, 0, 56));
        confirmButton.setBorder(BorderFactory.createEmptyBorder());
        add(confirmButton);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCurrentAccount();
            }
        });

        // Set frame visible
        setVisible(true);
    }

    private JTextField addFormRow(String labelText, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, 120, 30);
        add(label);

        JTextField textField = new JTextField();
        textField.setBackground(new Color(141, 128, 240));
        textField.setForeground(new Color(28, 0, 56));
        textField.setBounds(x + 140, y, 150, 30);
        add(textField);
        return textField;
    }

    private JPasswordField addPasswordRow(String labelText, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, 120, 30);
        add(label);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBackground(new Color(141, 128, 240));
        passwordField.setForeground(new Color(28, 0, 56));
        passwordField.setBounds(x + 140, y, 150, 30);
        add(passwordField);
        return passwordField;
    }

    private JComboBox<String> addDropdownRow(String labelText, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, 120, 30);
        add(label);

        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Apply Check Book", "Apply Business Loan"});
        comboBox.setBackground(new Color(141, 128, 240));
        comboBox.setForeground(new Color(28, 0, 56));
        comboBox.setBounds(x + 140, y, 150, 30);
        add(comboBox);
        return comboBox;
    }

    private void saveCurrentAccount() {
        // Get data from form fields
        String iniName = iniNameField.getText();
        String fullName = fullNameField.getText();
        String address = addressField.getText();
        String nic = nicField.getText();
        String purpose = (String) purposeComboBox.getSelectedItem();
        String userName = userNameField.getText();
        String password = new String(passwordField.getPassword());
        double depositAmount = Double.parseDouble(depositAmountField.getText());

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/moneybank", "root", "root")) {
            String accQuery = "INSERT INTO currentacc (IniName, FullName, Address, NIC, Purpose, UserName) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstAcc = con.prepareStatement(accQuery)) {
                pstAcc.setString(1, iniName);
                pstAcc.setString(2, fullName);
                pstAcc.setString(3, address);
                pstAcc.setString(4, nic);
                pstAcc.setString(5, purpose);
                pstAcc.setString(6, userName);
                pstAcc.executeUpdate();
            }

            String loginQuery = "INSERT INTO tbllogin (UserName, Password) VALUES (?, ?)";
            try (PreparedStatement pstLogin = con.prepareStatement(loginQuery)) {
                pstLogin.setString(1, userName);
                pstLogin.setString(2, password);
                pstLogin.executeUpdate();
            }

            String transactionQuery = "INSERT INTO transaction (UserName, Balance) VALUES (?, ?)";
            try (PreparedStatement pstTransaction = con.prepareStatement(transactionQuery)) {
                pstTransaction.setString(1, userName);
                pstTransaction.setDouble(2, depositAmount);
                pstTransaction.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Current account created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while creating account. Please check your data.");
        }
    }

//    public static void main(String[] args) {
//        new CurrentAccountInterface();
//    }
}
