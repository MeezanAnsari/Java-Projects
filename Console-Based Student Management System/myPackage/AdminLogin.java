package myPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLogin {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdminLogin window = new AdminLogin();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AdminLogin() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Login Form");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        JLabel usernameLabel = new JLabel("Username:");
        frame.getContentPane().add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setColumns(10);
        frame.getContentPane().add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        frame.getContentPane().add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        frame.getContentPane().add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("batman") && password.equals("bruce")) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password.");
                }
            }
        });
        frame.getContentPane().add(loginButton);
    }
}
