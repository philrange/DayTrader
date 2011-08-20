package daytrader.gui;

import daytrader.util.ILoginDetailsProvider;
import daytrader.util.ILoginManager;
import daytrader.util.LoginManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class LoginScreen implements ILoginDetailsProvider {
    private JTextField username;
    private JTextField password;
    private JButton loginButton;
    private JLabel serverDetails;
    private JPanel loginPanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel serverDetailsLabel;
    private JFrame frame;


    public void close() {

        frame.dispose();
        frame.setVisible(false);
    }

    @Override
    public void login(final ILoginManager manager) {

        serverDetails.setText(manager.getUrl() + " : " + manager.getProductType());


        frame = new JFrame("Login");
        frame.setContentPane(loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        ActionListener loginListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                login(manager, username.getText(), password.getText());

            }
        };

        loginButton.setMnemonic(KeyEvent.VK_ENTER);
        loginButton.addActionListener(loginListener);

        username.addActionListener(loginListener);
        password.addActionListener(loginListener);


    }

    private void login(ILoginManager manager, String username, String password) {
        manager.login(username, password);
    }

}
