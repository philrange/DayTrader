package daytrader.util;

import com.lmax.api.FailureResponse;
import com.lmax.api.Session;
import com.lmax.api.account.LoginCallback;
import daytrader.gui.LoginScreen;

public class LoginManager implements LoginCallback {

    @Override
    public void onLoginSuccess(Session session) {
        System.out.println("Logged in - " + session);
    }

    @Override
    public void onLoginFailure(FailureResponse failureResponse) {
        System.out.println("Failed to login - " + failureResponse);
    }

    public LoginManager(String url, String productTypeString ) {

        new LoginScreen(this, url, productTypeString);
    }
}
