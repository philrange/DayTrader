package daytrader.util;

import com.lmax.api.FailureResponse;
import com.lmax.api.LmaxApi;
import com.lmax.api.Session;
import com.lmax.api.account.LoginCallback;
import com.lmax.api.account.LoginRequest;
import daytrader.DayTrader;
import daytrader.gui.LoginScreen;

public class LoginManager implements ILoginManager, LoginCallback {

    private ILoginDetailsProvider loginScreen;
    private DayTrader dayTrader;
    private String productType;

    public void setLmaxApi(LmaxApi lmaxApi) {
        this.lmaxApi = lmaxApi;
    }

    private LmaxApi lmaxApi;

    public String getUrl() {
        return lmaxApi.toString();
    }

    public String getProductType() {
        return productType;
    }


    @Override
    public void onLoginSuccess(Session session) {
        System.out.println("Logged in - " + session);

       ((LoginScreen)loginScreen).close();  // TODO : make the screen close itself
       loginScreen = null;
        // open main trading screen
        dayTrader.start(session);
    }

    @Override
    public void onLoginFailure(FailureResponse failureResponse) {
        System.out.println("Failed to login - " + failureResponse);

        //TODO : remove this as it's for testing purposes only
        onLoginSuccess(new MockSession());
    }

    public LoginManager(DayTrader dayTrader, String productType) {
        this.dayTrader = dayTrader;
        this.productType = productType;

    }

    public void login() {
        loginScreen.login(this);
    }

    @Override
    public void login(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password, LoginRequest.ProductType.valueOf(productType));


        // Login to LMAX!
        System.out.println("logging in...");
        lmaxApi.login(loginRequest, this);

    }

    public void setLoginScreen(LoginScreen loginScreen) {
        this.loginScreen = loginScreen;
    }


}

