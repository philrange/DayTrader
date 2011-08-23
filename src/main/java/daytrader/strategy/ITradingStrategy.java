package daytrader.strategy;


import com.lmax.api.order.Order;
import com.lmax.api.orderbook.OrderBookEvent;
import daytrader.enums.TradeSide;

public interface ITradingStrategy {

    void handlePriceEvent(OrderBookEvent event);

    void handleTradeEvent(Order orderId);

    void handleUserInput(TradeSide side);


    String getTradeDetails(long orderId);
}
