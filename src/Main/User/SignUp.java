package Main.User;

import Main.MyConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUp extends JDialog {
    private JTextField tfNewUserName;
    private JPasswordField pfNewPassword;
    private JPasswordField pfRepeatNewPassword;
    private JButton btnCreateAccount;

    public SignUp(LogIn parent) {
        super(parent, "Sign Up", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(848, 360);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitle = new JLabel("Sign Up");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 56));
        gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel lblNewUserName = new JLabel("Username:");
        lblNewUserName.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(lblNewUserName, gbc);

        gbc.gridx++;
        tfNewUserName = new JTextField(20);
        tfNewUserName.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(tfNewUserName, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblNewPassword = new JLabel("Password:");
        lblNewPassword.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(lblNewPassword, gbc);

        gbc.gridx++;
        pfNewPassword = new JPasswordField(20);
        pfNewPassword.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(pfNewPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblRepeatNewPassword = new JLabel("Repeat Password:");
        lblRepeatNewPassword.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(lblRepeatNewPassword, gbc);

        gbc.gridx++;
        pfRepeatNewPassword = new JPasswordField(20);
        pfRepeatNewPassword.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(pfRepeatNewPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        btnCreateAccount = new JButton("Done");
        btnCreateAccount.setFont(new Font("Arial", Font.PLAIN, 24));
        btnCreateAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
// kiem tra xem co trung lap ten khong
                String userName = tfNewUserName.getText();
                String password = String.valueOf(pfNewPassword.getPassword());
                String repeatPassword = String.valueOf(pfRepeatNewPassword.getPassword());
                String queryFindUserName = "select * from player where UserName=?";
                String queryInsertUser = "insert into player (UserName, Password) values (?, ?)";
                if (userName.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(SignUp.this, "Please complete all information!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (password.length() < 3) {
                    JOptionPane.showMessageDialog(SignUp.this, "The password must be longer than 2 characters!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!password.equals(repeatPassword)) {
                    JOptionPane.showMessageDialog(SignUp.this, "Your passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try (PreparedStatement ps = MyConnection.getConnection().prepareStatement(queryFindUserName)) {
                    ps.setString(1, userName);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            JOptionPane.showMessageDialog(SignUp.this, "User Name have been used!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try (PreparedStatement psInsert = MyConnection.getConnection().prepareStatement(queryInsertUser)) {
                    psInsert.setString(1, userName);
                    psInsert.setString(2, password);
                    int rowsAffected = psInsert.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(SignUp.this, "Sign up successful!", "Successful", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(SignUp.this, "Fail!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
            }
        });
        panel.add(btnCreateAccount, gbc);

        add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);
    }
}
