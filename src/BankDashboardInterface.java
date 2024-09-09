import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankDashboardInterface extends JFrame {

    public BankDashboardInterface(String username) {
        setTitle("Money Bank Dashboard");
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

        JButton logoutButton = new JButton("LOG OUT");
        logoutButton.setBounds(700, 25, 80, 30);
        logoutButton.setForeground(new Color(28, 0, 56));
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        headerPanel.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose current frame
                dispose();

                // Open SavingAccountInterface
                new BankLoginInterface();
            }
        });


        JPanel adminPanel = new JPanel();
        adminPanel.setBounds(30, 100, 300, 60);
        adminPanel.setBackground(new Color(44, 0, 88));
        adminPanel.setLayout(null);
        adminPanel.setBorder(BorderFactory.createLineBorder(new Color(44, 0, 88), 10, true)); // Rounded border
        add(adminPanel);

        JLabel adminNameLabel = new JLabel("ADMIN NAME :");
        adminNameLabel.setBounds(20, 15, 120, 30);
        adminNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        adminNameLabel.setForeground(Color.WHITE);
        adminPanel.add(adminNameLabel);

        JLabel adminNameValueLabel = new JLabel(username);
        adminNameValueLabel.setBounds(150, 15, 100, 30);
        adminNameValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        adminNameValueLabel.setForeground(new Color(252, 203, 45));
        adminPanel.add(adminNameValueLabel);

        JButton savingAccountButton = new JButton("SAVING ACCOUNT");
        savingAccountButton.setBounds(30, 300, 300, 50);
        savingAccountButton.setFont(new Font("Arial", Font.BOLD, 26));
        savingAccountButton.setBackground(new Color(141, 128, 240));
        savingAccountButton.setForeground(new Color(252, 203, 45));
        savingAccountButton.setBorder(BorderFactory.createEmptyBorder());
        add(savingAccountButton);

        savingAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose current frame
                dispose();

                // Open SavingAccountInterface
                new SavingAccountInterface();
            }
        });

        JButton currentAccountButton = new JButton("CURRENT ACCOUNT");
        currentAccountButton.setBounds(30, 380, 300, 50);
        currentAccountButton.setFont(new Font("Arial", Font.BOLD, 26));
        currentAccountButton.setBackground(new Color(141, 128, 240));
        currentAccountButton.setForeground(new Color(252, 203, 45));
        currentAccountButton.setBorder(BorderFactory.createEmptyBorder());
        add(currentAccountButton);

        currentAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose current frame
                dispose();

                // Open SavingAccountInterface
                new CurrentAccountInterface();
            }
        });

        JLabel imageLabel = new JLabel(new ImageIcon("BankLogin.jpg"));
        imageLabel.setBounds(350, 100, 600, 500);
        add(imageLabel);

        setVisible(true);
    }

//    public static void main(String[] args) {
//        String username = null;
//        new BankDashboardInterface(username);
//    }
}
