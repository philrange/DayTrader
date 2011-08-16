package daytrader.strategy;

import com.lmax.api.orderbook.OrderBookEvent;

public class WaitForRunOfSimilarTicksStrategy implements ITradingStrategy{

    @Override
    public void handlePriceEvent(OrderBookEvent event) {

    }

    @Override
    public void handleTradeEvent(long orderId) {

    }

    @Override
    public void handleUserInput(String input) {

    }

    @Override
    public String getTradeDetails(long orderId) {
       throw new UnsupportedOperationException("getTradeDetails");
    }
}
