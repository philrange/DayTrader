package daytrader.gui;

import com.lmax.api.LmaxApi;
import com.lmax.api.account.LoginRequest;
import com.lmax.api.account.LoginRequest.ProductType;
import daytrader.util.LoginManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class LoginScreen {
    private JTextField username;
    private JTextField password;
    private JButton loginButton;
    private JLabel serverDetails;
    private JPanel loginPanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel serverDetailsLabel;
    private final LmaxApi lmaxApi;


    public LoginScreen(final LoginManager manager, String url, String productTypeString) {

        final ProductType productType = ProductType.valueOf(productTypeString);
        lmaxApi = new LmaxApi(url) ;
        serverDetails.setText(url + " : " + productType.toString());


        JFrame frame = new JFrame("Login");
        frame.setContentPane(loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginRequest loginRequest = new LoginRequest(username.getText(), password.getText(), productType);

                // Login to LMAX!
                lmaxApi.login(loginRequest, manager);
                System.out.println("logging in...");
            }
        });

    }


}
