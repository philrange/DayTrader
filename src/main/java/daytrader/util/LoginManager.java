package daytrader.util;

import com.lmax.api.FailureResponse;
import com.lmax.api.LmaxApi;
import com.lmax.api.Session;
import com.lmax.api.account.LoginCallback;
import com.lmax.api.account.LoginRequest;
import daytrader.gui.LoginScreen;

public class LoginManager implements LoginCallback {


    public String getUrl() {
        return url;
    }

    public String getProductType() {
        return productType;
    }

    private String url;
    private String productType;

    @Override
    public void onLoginSuccess(Session session) {
        System.out.println("Logged in - " + session);
    }

    @Override
    public void onLoginFailure(FailureResponse failureResponse) {
        System.out.println("Failed to login - " + failureResponse);
    }

    public LoginManager(String url, String productType) {
        this.url = url;
        this.productType = productType;

        new LoginScreen(this);

    }

    public void login(String username, String password) {


        LoginRequest loginRequest = new LoginRequest(username, password, LoginRequest.ProductType.valueOf(productType));

        LmaxApi lmaxApi = new LmaxApi(url);
        // Login to LMAX!
        lmaxApi.login(loginRequest, this);
        System.out.println("logging in...");

    }
}

