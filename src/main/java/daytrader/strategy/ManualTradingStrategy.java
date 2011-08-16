package daytrader.strategy;

import com.lmax.api.orderbook.OrderBookEvent;
import daytrader.DayTrader;

public class ManualTradingStrategy implements ITradingStrategy{



    private final DayTrader dayTrader;


    public ManualTradingStrategy(DayTrader dayTrader) {
        this.dayTrader = dayTrader;
    }

    @Override
    public void handlePriceEvent(OrderBookEvent event) {


    }

    @Override
    public void handleTradeEvent(long orderId) {

    }

    @Override
    public void handleUserInput(String input) {

        if (input.startsWith("buy")) {
             dayTrader.sendMarketOrder();
        }
    }

    @Override
    public String getTradeDetails(long orderId) {
        return "trade " + orderId;
    }
}
