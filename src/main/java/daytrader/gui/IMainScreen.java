package daytrader.gui;

import com.lmax.api.orderbook.OrderBookEvent;

public interface IMainScreen {

    void show(String stuff);

    void onTrade(String trade);

    void onPriceEvent(OrderBookEvent event);

}
