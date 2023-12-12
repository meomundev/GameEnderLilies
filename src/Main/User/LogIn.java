package Main.User;

import Main.MyConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogIn extends JDialog {
    private JTextField tfUserName;
    private JPasswordField pfPassword;
    private JButton btnLogIn;
    private JButton btnSignUp;
    private boolean loginSuccessful = false;

    public LogIn(JFrame parent) {
        super(parent, "Login", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(648, 360);

        // Thiết lập Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitle = new JLabel("Login");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 56));
        gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel lblUserName = new JLabel("Username:");
        lblUserName.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(lblUserName, gbc);

        gbc.gridx++;
        tfUserName = new JTextField(20);
        tfUserName.setFont(new Font("Arial", Font.PLAIN, 24)); // Thiết lập font lớn hơn cho TextField
        panel.add(tfUserName, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(lblPassword, gbc);

        gbc.gridx++;
        pfPassword = new JPasswordField(20);
        pfPassword.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(pfPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        btnLogIn = new JButton("Log In");
        btnLogIn.setFont(new Font("Arial", Font.PLAIN, 24));
        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = tfUserName.getText();
                String password = String.valueOf(pfPassword.getPassword());
                String query = "select * from player where UserName=? and Password=?";

                try (PreparedStatement ps = MyConnection.getConnection().prepareStatement(query)) {
                    ps.setString(1, userName);
                    ps.setString(2, password);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            loginSuccessful = true;
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(LogIn.this, "Login Fail! Try Again.", "Error", JOptionPane.ERROR_MESSAGE);
                            getClear();
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(btnLogIn, gbc);

        gbc.gridy++;
        btnSignUp = new JButton("Sign Up");
        btnSignUp.setFont(new Font("Arial", Font.PLAIN, 24));
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUp(LogIn.this);
            }
        });
        panel.add(btnSignUp, gbc);

        add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);
    }

    public void getClear() {
        tfUserName.setText("");
        pfPassword.setText("");
    }

    public boolean loginSuccessful() {
        return loginSuccessful;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LogIn(null));
    }
}
