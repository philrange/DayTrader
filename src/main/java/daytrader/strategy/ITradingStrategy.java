package daytrader.strategy;


import com.lmax.api.orderbook.OrderBookEvent;
import daytrader.enums.TradeSide;

public interface ITradingStrategy {

    void handlePriceEvent(OrderBookEvent event);

    void handleTradeEvent(long orderId);

    void handleUserInput(TradeSide side);


    String getTradeDetails(long orderId);
}
