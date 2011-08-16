package daytrader.strategy;


import com.lmax.api.orderbook.OrderBookEvent;

public interface ITradingStrategy {

    void handlePriceEvent(OrderBookEvent event);

    void handleTradeEvent(long orderId);

    void handleUserInput(String input);


    String getTradeDetails(long orderId);
}
