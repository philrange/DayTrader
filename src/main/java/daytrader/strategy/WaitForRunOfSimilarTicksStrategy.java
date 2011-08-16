package daytrader.strategy;

import com.lmax.api.orderbook.OrderBookEvent;
import daytrader.enums.TradeSide;

public class WaitForRunOfSimilarTicksStrategy implements ITradingStrategy{

    @Override
    public void handlePriceEvent(OrderBookEvent event) {

    }

    @Override
    public void handleTradeEvent(long orderId) {

    }

    @Override
    public void handleUserInput(TradeSide side) {

    }

    @Override
    public String getTradeDetails(long orderId) {
       throw new UnsupportedOperationException("getTradeDetails");
    }
}
