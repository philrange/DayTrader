package daytrader;

import com.lmax.api.FailureResponse;
import com.lmax.api.FixedPointNumber;
import com.lmax.api.Session;
import com.lmax.api.TimeInForce;
import com.lmax.api.account.LogoutCallBack;
import com.lmax.api.order.*;
import com.lmax.api.orderbook.OrderBookEvent;
import com.lmax.api.orderbook.OrderBookEventListener;
import com.lmax.api.orderbook.OrderBookSubscriptionRequest;
import com.lmax.api.reject.InstructionRejectedEvent;
import com.lmax.api.reject.InstructionRejectedEventListener;
import daytrader.gui.IMainScreen;
import daytrader.util.DefaultSubscriptionCallback;
import daytrader.util.ILoginManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import daytrader.strategy.ITradingStrategy;

public class DayTrader implements LogoutCallBack, OrderBookEventListener, OrderEventListener, InstructionRejectedEventListener, OrderCallback {


    private ILoginManager loginManager;
    private Session session;

    private ITradingStrategy strategy;
    private IMainScreen mainScreen;
    private final long eurUsdInstrumentID = 4001;
    private long tradeId = 1;
    private final FixedPointNumber quantity = FixedPointNumber.TEN;


    public Session getSession() {
        return session;
    }

    /*
    *  Called by LoginManager after successful login
    */
    public void start(final Session session) {

        this.session = session;
        // get some session details to display
        System.out.println("starting");
        System.out.println(session.getAccountDetails().getUsername());


        // register listeners
        session.registerOrderBookEventListener(this);
        session.registerOrderEventListener(this);
        session.registerInstructionRejectedEventListener(this);

        // Subscribe to my order events.
        session.subscribe(new OrderSubscriptionRequest(), new DefaultSubscriptionCallback());
        // Subscribe to the order book that I'm interested in.
        session.subscribe(new OrderBookSubscriptionRequest(eurUsdInstrumentID), new DefaultSubscriptionCallback());




        //display GUI
        mainScreen.show(session.getAccountDetails().getUsername());



        //start session?
//       session.start();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                session.start();
            }
        });
        t.start();
    }



    public void setStrategy(ITradingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setMainScreen(IMainScreen mainScreen) {
        this.mainScreen = mainScreen;
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
    public void onSuccess(long orderId) {
         strategy.handleTradeEvent(orderId);
        mainScreen.onTrade(strategy.getTradeDetails(orderId));
    }

    @Override
    public void onFailure(FailureResponse failureResponse) {
        System.out.println("Failed to logout.");
    }

    ILoginManager getLoginManager() {
        return loginManager;
    }

    public void logout() {
        session.logout(this);

    }

    @Override
    public void notify(OrderBookEvent orderBookEvent) {
//        System.out.println(orderBookEvent);
        strategy.handlePriceEvent(orderBookEvent);
        mainScreen.onPriceEvent(orderBookEvent);
        System.out.println(orderBookEvent.getBidPrices().get(0).getPrice());
        System.out.println(orderBookEvent.getAskPrices().get(0).getPrice());
    }

    @Override
    public void notify(Order order) {
        System.out.println(order);
    }

    @Override
    public void notify(InstructionRejectedEvent instructionRejectedEvent) {
        System.out.println(instructionRejectedEvent);
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

    public void sendMarketOrder() {

        session.placeMarketOrder(new MarketOrderSpecification(tradeId++, quantity, TimeInForce.FILL_OR_KILL), this);
    }
}
