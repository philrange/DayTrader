package daytrader.util;

import com.lmax.api.Session;

public interface ILoginManager {

    /**
     * Login manager will deal with getting username & password
     */
    void login();

    /**
     * To be used as a callback by ILoginDetailsProvider
     * @param username user
     * @param password pass
     */
    void login(String username, String password);
    String getUrl();
    String getProductType();
}
