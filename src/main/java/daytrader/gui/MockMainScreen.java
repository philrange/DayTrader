package daytrader.gui;

import com.lmax.api.orderbook.OrderBookEvent;
import daytrader.DayTrader;
import sun.java2d.ScreenUpdateManager;
import sun.tools.jar.Main;

public class MockMainScreen implements IMainScreen {

    public MockMainScreen(DayTrader dayTrader) {
        this.dayTrader = dayTrader;
    }

    private DayTrader dayTrader;

    @Override
    public void show(String stuff) {
        System.out.println("showing main screen");

    }

    @Override
    public void onTrade(String trade) {
        System.out.println("Main Screen got trade " + trade);

    }

    @Override
    public void onPriceEvent(OrderBookEvent event) {

    }
}
