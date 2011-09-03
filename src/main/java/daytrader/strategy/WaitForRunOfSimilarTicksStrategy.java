package daytrader.strategy;

import com.lmax.api.FixedPointNumber;
import com.lmax.api.FixedPointNumbers;
import com.lmax.api.order.Order;
import com.lmax.api.orderbook.OrderBookEvent;
import com.lmax.api.orderbook.PricePoint;
import daytrader.DayTrader;
import daytrader.enums.TradeSide;

import java.util.HashMap;
import java.util.Map;

public class WaitForRunOfSimilarTicksStrategy implements ITradingStrategy {


    private int ticksInARow;
    private boolean positionOpen;
    private boolean lastTradeWasBuy;
    private FixedPointNumber lastPrice = FixedPointNumber.ZERO;
//    private FixedPointNumber lastTradedPrice;

    private final DayTrader dayTrader;
    private Map<Long, String> trades;


    public WaitForRunOfSimilarTicksStrategy(DayTrader dayTrader) {
        this.dayTrader = dayTrader;
        this.trades = new HashMap<Long, String>();
    }


    @Override
    public void handlePriceEvent(OrderBookEvent event) {
        updateNoOfTicks(event);


        if (Math.abs(ticksInARow) >= 3) {
            if (positionOpen) {
                if (priceIsBetter(event)) {
                    hitPrice();
                    positionOpen = false; //todo - wait for trade confirmation ?
                }
            } else {
                hitPrice();
                positionOpen = true;      //todo - wait for trade confirmation ?
            }
        }
    }

    private boolean priceIsBetter(OrderBookEvent event) {

        if (lastTradeWasBuy) {
            // we are selling
//            return currentPrice > lastTradedPrice;
            return event.getBidPrices().get(0).getPrice().longValue() > event.getLastTradedPrice().longValue();
        } else {
            // we are buying
//            return currentPrice < lastTradedPrice;
            return event.getAskPrices().get(0).getPrice().longValue() < event.getLastTradedPrice().longValue();
        }

    }

    private void updateNoOfTicks(OrderBookEvent event) {

        FixedPointNumber price = null;
        try {
            if (!event.getAskPrices().isEmpty()) {
                price = event.getAskPrices().get(0).getPrice();

                if (isUpTick(price)) {
                    ticksInARow++;
                } else {
                    ticksInARow--;
                }
                lastPrice = price != null ? price : lastPrice;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("tick count: " + ticksInARow);
    }

    private boolean isUpTick(FixedPointNumber price) {
        return price.longValue() > lastPrice.longValue();
    }

    private void hitPrice() {
        if (ticksInARow > 0) {
            sell();
        } else {
            buy();
        }
        ticksInARow = 0;
    }

    private void buy() {

        dayTrader.sendMarketOrder(TradeSide.BUY);
        lastTradeWasBuy = true;
    }

    private void sell() {

        lastTradeWasBuy = false;
    }

    @Override
    public void handleTradeEvent(Order orderId) {

    }

    @Override
    public void handleUserInput(TradeSide side) {

    }

    @Override
    public String getTradeDetails(long orderId) {
        throw new UnsupportedOperationException("getTradeDetails");
    }
}
