package daytrader.gui;

import com.lmax.api.order.Order;
import com.lmax.api.orderbook.OrderBookEvent;

public interface IMainScreen {

    void show(String stuff);

    void onTrade(Order trade);

    void onPriceEvent(OrderBookEvent event);

    void sendInfo(String s);
}
