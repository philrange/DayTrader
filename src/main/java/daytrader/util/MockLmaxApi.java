package daytrader.util;

import com.lmax.api.LmaxApi;
import com.lmax.api.account.LoginCallback;
import com.lmax.api.account.LoginRequest;

import java.net.URL;

public class MockLmaxApi extends LmaxApi{


    @Override
    public void login(LoginRequest loginRequest, LoginCallback callback) {
        callback.onLoginSuccess(new MockSession(loginRequest));
    }
}
