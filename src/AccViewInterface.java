import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AccViewInterface extends JFrame {

    public AccViewInterface() {
        setTitle("Money Bank - Account View Interface");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(25, 20, 50));

        // Header
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
        welcomeLabel.setBounds(450, 20, 150, 40);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(252, 203, 45)); // Gold color
        headerPanel.add(welcomeLabel);

        JButton logoutButton = new JButton("LOG OUT");
        logoutButton.setBounds(900, 25, 80, 30);
        logoutButton.setForeground(new Color(28, 0, 56));
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        headerPanel.add(logoutButton);

        //  logout button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose current frame
                dispose();

                // Open BankLoginInterface
                new BankLoginInterface();
            }
        });

        JLabel savingAccountsLabel = new JLabel("Saving Accounts");
        savingAccountsLabel.setBounds(50, 80, 200, 30);
        savingAccountsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        savingAccountsLabel.setForeground(new Color(252, 203, 45));
        add(savingAccountsLabel);

        JLabel currentAccountsLabel = new JLabel("Current Accounts");
        currentAccountsLabel.setBounds(50, 300, 200, 30);
        currentAccountsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        currentAccountsLabel.setForeground(new Color(252, 203, 45));
        add(currentAccountsLabel);

        JLabel updatePasswordsLabel = new JLabel("Update Passwords");
        updatePasswordsLabel.setBounds(50, 530, 200, 30);
        updatePasswordsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        updatePasswordsLabel.setForeground(new Color(252, 203, 45));
        add(updatePasswordsLabel);

        JPanel savingTablePanel = createTablePanel(50, 120, 900, 150);
        add(savingTablePanel);

        JPanel currentTablePanel = createTablePanel(50, 340, 900, 150);
        add(currentTablePanel);

        JPanel loginTablePanel = createTablePanel(50, 570, 900, 80);
        add(loginTablePanel);

        // Saving Accounts Table
        JTable savingTable = createAndPopulateTable("savingacc", new String[]{"IniName", "FullName", "Address", "NIC", "Purpose", "Username", "Action"}, "Delete");
        savingTablePanel.add(new JScrollPane(savingTable), BorderLayout.CENTER);

        addButtonActions(savingTable, "savingacc");

        // Current Accounts Table
        JTable currentTable = createAndPopulateTable("currentacc", new String[]{"IniName", "FullName", "Address", "NIC", "Purpose", "Username", "Action"}, "Delete");
        currentTablePanel.add(new JScrollPane(currentTable), BorderLayout.CENTER);

        // Add ActionListener to the Delete Button for Current Accounts
        addButtonActions(currentTable, "currentacc");

        JTable loginTable = createAndPopulateTable("tbllogin", new String[]{"Username", "Password", "Action"}, "Update");
        loginTablePanel.add(new JScrollPane(loginTable), BorderLayout.CENTER);

        addButtonActions(loginTable, "tbllogin");

        // Set frame visible
        setVisible(true);
    }

    private JPanel createTablePanel(int x, int y, int width, int height) {
        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(x, y, width, height);
        tablePanel.setBackground(new Color(35, 25, 65));
        tablePanel.setLayout(new BorderLayout());
        return tablePanel;
    }

    private JTable createAndPopulateTable(String tableName, String[] columnNames, String actionColumnValue) {
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/moneybank", "root", "root");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName)) {

            while (rs.next()) {
                Object[] rowData = new Object[columnNames.length];
                for (int i = 0; i < columnNames.length - 1; i++) {
                    rowData[i] = rs.getString(columnNames[i]);
                }
                rowData[columnNames.length - 1] = actionColumnValue;
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new JTable(model);
    }

    private void addButtonActions(JTable table, String tableName) {
        table.getColumn("Action").setCellRenderer(new ButtonRenderer());
        table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(), table, tableName));
    }

//    public static void main(String[] args) {
//        new AccViewInterface();
//    }
}

// ButtonRenderer class
class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

// ButtonEditor class
class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;
    private String tableName;

    public ButtonEditor(JCheckBox checkBox, JTable table, String tableName) {
        super(checkBox);
        this.table = table;
        this.tableName = tableName;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            if (label.equals("Delete")) {
                // Perform deletion after NIC confirmation
                handleDeleteAction();
            } else if (label.equals("Update")) {
                handleUpdateAction();
            }
        }
        isPushed = false;
        return label;
    }

    private void handleDeleteAction() {
        String enteredNIC = JOptionPane.showInputDialog(null, "Enter NIC to confirm deletion:");
        if (enteredNIC != null && enteredNIC.equals(table.getValueAt(table.getSelectedRow(), 3))) { // Column 3 = NIC
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/moneybank", "root", "root");
                 Statement stmt = con.createStatement()) {

                String nic = table.getValueAt(table.getSelectedRow(), 3).toString();
                stmt.executeUpdate("DELETE FROM " + tableName + " WHERE NIC='" + nic + "'");
                JOptionPane.showMessageDialog(null, "Account deleted successfully.");
                ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Deletion failed: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect NIC entered!");
        }
    }

    private void handleUpdateAction() {
        String newPassword = JOptionPane.showInputDialog(null, "Enter new password:");
        if (newPassword != null) {
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/moneybank", "root", "root");
                 Statement stmt = con.createStatement()) {

                String username = table.getValueAt(table.getSelectedRow(), 0).toString(); // Column 0 = Username
                stmt.executeUpdate("UPDATE tbllogin SET Password='" + newPassword + "' WHERE Username='" + username + "'");
                JOptionPane.showMessageDialog(null, "Password updated successfully.");
                table.setValueAt(newPassword, table.getSelectedRow(), 1); // Update the table model

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Password update failed: " + ex.getMessage());
            }
        }
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
