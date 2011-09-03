package daytrader;

import com.lmax.api.FailureResponse;
import com.lmax.api.FixedPointNumber;
import com.lmax.api.Session;
import com.lmax.api.TimeInForce;
import com.lmax.api.account.AccountStateEvent;
import com.lmax.api.account.AccountStateEventListener;
import com.lmax.api.account.LogoutCallBack;
import com.lmax.api.order.*;
import com.lmax.api.orderbook.OrderBookEvent;
import com.lmax.api.orderbook.OrderBookEventListener;
import com.lmax.api.orderbook.OrderBookSubscriptionRequest;
import com.lmax.api.reject.InstructionRejectedEvent;
import com.lmax.api.reject.InstructionRejectedEventListener;
import daytrader.enums.TradeSide;
import daytrader.gui.IMainScreen;
import daytrader.util.DefaultSubscriptionCallback;
import daytrader.util.ILoginManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import daytrader.strategy.ITradingStrategy;

public class DayTrader implements LogoutCallBack, OrderBookEventListener, OrderEventListener, InstructionRejectedEventListener, OrderCallback, AccountStateEventListener {


    private ILoginManager loginManager;
    private Session session;

    private ITradingStrategy strategy;
    private IMainScreen mainScreen;
    private static final long eurUsdInstrumentID = 4001;
    private static long usdJpyInstrumentID = 4004;
    private static final long _24hourTestInstrumentID = 100437;
    private FixedPointNumber quantity = FixedPointNumber.ONE;
    private long instrumentId = eurUsdInstrumentID;



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
        session.registerAccountStateEventListener(this);

        // Subscribe to my order events.
        session.subscribe(new OrderSubscriptionRequest(), new DefaultSubscriptionCallback());
        // Subscribe to the order book that I'm interested in.
        session.subscribe(new OrderBookSubscriptionRequest(getInstrumentId()), new DefaultSubscriptionCallback());




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
    /**
     * Immediate notification of successful trade - no details other than the ID are included
     */
    public void onSuccess(long orderId) {
        System.out.println("onSuccess " + orderId);
//         strategy.handleTradeEvent(orderId);
//        mainScreen.onTrade(strategy.getTradeDetails(orderId));
        mainScreen.sendInfo("Trade Successful - " + orderId);
    }

    @Override
    public void onFailure(FailureResponse failureResponse) {
        //System.out.println("Failed to logout.");

        System.out.println(failureResponse);
        //notify strategy
    }

    ILoginManager getLoginManager() {
        return loginManager;
    }

    public void logout() {
        session.logout(this);

    }

    @Override
    /**
     * Price update event
     */
    public void notify(OrderBookEvent orderBookEvent) {
        strategy.handlePriceEvent(orderBookEvent);
        mainScreen.onPriceEvent(orderBookEvent);
//        System.out.println(orderBookEvent);
    }

    @Override
    /**
     * Notification of a trade + all the details
     */
    public void notify(Order order) {
        System.out.println(order);
        strategy.handleTradeEvent(order);
        mainScreen.onTrade(order);
    }

    @Override
    public void notify(InstructionRejectedEvent instructionRejectedEvent) {
        System.out.println(instructionRejectedEvent);
        //notify strategy
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

    public void sendMarketOrder(long instrumentId, TradeSide side, FixedPointNumber quantity) {

                                           // todo bid/ask right way round?


        FixedPointNumber quantityToSend = side == TradeSide.BUY ? quantity : quantity.negate();
        System.out.println(side + " " + quantityToSend);

        session.placeMarketOrder(new MarketOrderSpecification(instrumentId, quantityToSend, TimeInForce.FILL_OR_KILL), this);
    }

    public ITradingStrategy getStrategy() {
        return strategy;
    }

    @Override
    public void notify(AccountStateEvent accountStateEvent) {
        System.out.println(accountStateEvent);

    }

    public long getInstrumentId() {
        return instrumentId;
    }

    public void sendMarketOrder(TradeSide side) {
                                    sendMarketOrder(getInstrumentId(), side, quantity);
    }
}
