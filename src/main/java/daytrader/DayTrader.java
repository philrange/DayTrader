package daytrader;

import com.lmax.api.FailureResponse;
import com.lmax.api.Session;
import com.lmax.api.account.LogoutCallBack;
import daytrader.gui.IMainScreen;
import daytrader.util.ILoginManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import daytrader.strategy.ITradingStrategy;

import java.awt.*;

public class DayTrader implements LogoutCallBack {


    private ILoginManager loginManager;
    private Session session;

    private ITradingStrategy strategy;
    private IMainScreen mainScreen;



    public void setStrategy(ITradingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setMainScreen(IMainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/daytrader-application-context.xml");
        DayTrader dayTrader = (DayTrader) applicationContext.getBean("dayTrader");
        dayTrader.getLoginManager().login();


        //keep running
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            } finally {
                //dayTrader.session.logout(dayTrader);
            }
        }

    }


    public Session getSession() {
        return session;
    }

    /*
    *  Called by LoginManager after successful login
    */
    public void start(Session session) {

        this.session = session;
        // get some session details to display
        System.out.println("starting");
        System.out.println(session.getAccountDetails().getUsername());


        // register listeners
//        session.

        // subscribe to events & order book


        //display GUI
        mainScreen.show(session.getAccountDetails().getUsername());

        //start session?
        session.start();
    }


    public void setLoginManager(ILoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @Override
    public void onSuccess() {
        System.out.println("Logged out.");
        System.exit(0);
    }

    @Override
    public void onFailure(FailureResponse failureResponse) {
        System.out.println("Failed to logout.");
    }

    public ILoginManager getLoginManager() {
        return loginManager;
    }

    public void logout() {
        session.logout(this);

    }
}
