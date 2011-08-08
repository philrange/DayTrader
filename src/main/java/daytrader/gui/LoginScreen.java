package daytrader.gui;

import com.lmax.api.LmaxApi;
import com.lmax.api.account.LoginRequest;
import com.lmax.api.account.LoginRequest.ProductType;
import daytrader.util.LoginManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

public class LoginScreen {
    private JTextField username;
    private JTextField password;
    private JButton loginButton;
    private JLabel serverDetails;
    private JPanel loginPanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel serverDetailsLabel;



    public LoginScreen(final LoginManager manager) {

        serverDetails.setText(manager.getUrl() + " : " + manager.getProductType());


        JFrame frame = new JFrame("Login");
        frame.setContentPane(loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.login(username.getText(), password.getText());

            }
        });

    }


}
