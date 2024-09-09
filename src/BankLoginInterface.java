import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankLoginInterface extends JFrame {

    public BankLoginInterface() {
        setTitle("Bank Login Interface");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1000, 80);
        headerPanel.setBackground(new Color(19, 44, 115));
        headerPanel.setLayout(null);
        add(headerPanel);

        JLabel bankNameLabel = new JLabel("MONEY BANK");
        bankNameLabel.setBounds(350, 20, 300, 40);
        bankNameLabel.setFont(new Font("Algerian", Font.BOLD, 40));
        bankNameLabel.setForeground(Color.WHITE);
        headerPanel.add(bankNameLabel);

        JLabel background = new JLabel(new ImageIcon("BankLogin.jpg"));
        background.setBounds(0, 0, 1000, 700);
        add(background);
        background.setLayout(null);

        JPanel adminPanel = new JPanel();
        adminPanel.setBounds(50, 200, 300, 200);
        adminPanel.setBackground(new Color(0, 0, 0, 150));//set bg color
        adminPanel.setLayout(null);
        background.add(adminPanel);

        JLabel adminLabel = new JLabel("Admin Login");
        adminLabel.setBounds(100, 10, 150, 30);
        adminLabel.setForeground(Color.YELLOW);
        adminLabel.setFont(new Font("Algerian", Font.BOLD, 20));
        adminPanel.add(adminLabel);


        JLabel adminUserNameLabel = new JLabel("User Name");
        adminUserNameLabel.setBounds(10, 50, 100, 25);
        adminUserNameLabel.setForeground(Color.WHITE);
        adminUserNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        adminUserNameLabel.setForeground(new Color(252, 203, 45));
        adminPanel.add(adminUserNameLabel);

        JTextField adminUserName = new JTextField();
        adminUserName.setBounds(120, 50, 150, 25);
        adminUserName.setName("AdminUserName");
        adminPanel.add(adminUserName);

        JLabel adminPasswordLabel = new JLabel("Password");
        adminPasswordLabel.setBounds(10, 90, 100, 25);
        adminPasswordLabel.setForeground(Color.WHITE);
        adminPasswordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        adminPasswordLabel.setForeground(new Color(252, 203, 45));
        adminPanel.add(adminPasswordLabel);

        JPasswordField adminPassword = new JPasswordField();
        adminPassword.setBounds(120, 90, 150, 25);
        adminPassword.setName("AdminPassword");
        adminPanel.add(adminPassword);

        JButton adminLoginBtn = new JButton("Login");
        adminLoginBtn.setBounds(100, 130, 100, 30);
        adminLoginBtn.setName("adminloginbtn");
        adminPanel.add(adminLoginBtn);

        JPanel customerPanel = new JPanel();
        customerPanel.setBounds(630, 200, 300, 200);
        customerPanel.setBackground(new Color(0, 0, 0, 150)); // Set bg color
        customerPanel.setLayout(null);
        background.add(customerPanel);

        JLabel customerLabel = new JLabel("Customer Login");
        customerLabel.setBounds(50, 10, 200, 30);
        customerLabel.setForeground(Color.YELLOW);
        customerLabel.setFont(new Font("Algerian", Font.BOLD, 20));
        customerPanel.add(customerLabel);

        JLabel customerUserNameLabel = new JLabel("User Name");
        customerUserNameLabel.setBounds(10, 50, 100, 25);
        customerUserNameLabel.setForeground(Color.WHITE);
        customerUserNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        customerUserNameLabel.setForeground(new Color(252, 203, 45));
        customerPanel.add(customerUserNameLabel);

        JTextField customerUserName = new JTextField();
        customerUserName.setBounds(120, 50, 150, 25);
        customerUserName.setName("CustomerUserName");
        customerPanel.add(customerUserName);

        JLabel customerPasswordLabel = new JLabel("Password");
        customerPasswordLabel.setBounds(10, 90, 100, 25);
        customerPasswordLabel.setForeground(Color.WHITE);
        customerPasswordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        customerPasswordLabel.setForeground(new Color(252, 203, 45));
        customerPanel.add(customerPasswordLabel);

        JPasswordField customerPassword = new JPasswordField();
        customerPassword.setBounds(120, 90, 150, 25);
        customerPassword.setName("CustomerPassword");
        customerPanel.add(customerPassword);

        JButton customerLoginBtn = new JButton("Login");
        customerLoginBtn.setBounds(100, 130, 100, 30);
        customerLoginBtn.setName("Customerloginbtn");
        customerPanel.add(customerLoginBtn);

        adminLoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = adminUserName.getText();
                String password = new String(adminPassword.getPassword());

                if (DatabaseManager.validateAdminLogin(username, password)) {
                    //open the Admin Dashboard
                    dispose();
                    new BankDashboardInterface(username);
                } else {
                    // Login failed
                    JOptionPane.showMessageDialog(null, "Invalid admin username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        customerLoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = customerUserName.getText();
                String password = new String(customerPassword.getPassword());

                if (DatabaseManager.validateCustomerLogin(username, password)) {
                    dispose();
                    new CustomerInterface(username);
                } else {
                    // Login failed
                    JOptionPane.showMessageDialog(null, "Invalid customer username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new BankLoginInterface();
    }
}
