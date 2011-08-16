package daytrader.strategy;

import com.lmax.api.orderbook.OrderBookEvent;

public class DummyStrategy implements ITradingStrategy {


    @Override
    public void handlePriceEvent(OrderBookEvent event) {
            System.out.println("got a price " + event);
    }

    @Override
    public void handleTradeEvent(long orderId) {
        System.out.println("got a trade");
    }

    @Override
    public void handleUserInput(String input) {
        System.out.println("User did " + input);

    }

    @Override
    public String getTradeDetails(long orderId) {
        return null;
    }
}
