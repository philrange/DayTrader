package daytrader.util;

import com.lmax.api.*;
import com.lmax.api.account.*;
import com.lmax.api.heartbeat.HeartbeatCallback;
import com.lmax.api.heartbeat.HeartbeatEventListener;
import com.lmax.api.heartbeat.HeartbeatRequest;
import com.lmax.api.order.*;
import com.lmax.api.orderbook.*;
import com.lmax.api.position.PositionEventListener;
import com.lmax.api.reject.InstructionRejectedEventListener;

import java.util.LinkedList;
import java.util.List;

public class MockSession implements Session {

    private OrderBookEventListener orderBookEventListener;
    @Override
    public void start() {

        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             orderBookEventListener.notify(new OrderBookEvent() {
                 @Override
                 public long getInstrumentId() {
                     return 1234;
                 }

                 @Override
                 public FixedPointNumber getValuationBidPrice() {
                     return null;
                 }

                 @Override
                 public FixedPointNumber getValuationAskPrice() {
                     return null;
                 }

                 @Override
                 public List<PricePoint> getBidPrices() {
                     List<PricePoint> list = new LinkedList<PricePoint>();
                     list.add(new PricePoint() {

                         @Override
                         public FixedPointNumber getPrice() {
                             return FixedPointNumber.valueOf(12345);
                         }

                         @Override
                         public FixedPointNumber getQuantity() {
                             return FixedPointNumber.TEN;
                         }
                     });
                     return list;
                 }

                 @Override
                 public List<PricePoint> getAskPrices() {
                     List<PricePoint> list = new LinkedList<PricePoint>();
                     list.add(new PricePoint() {

                         @Override
                         public FixedPointNumber getPrice() {
                             return FixedPointNumber.valueOf(12456);
                         }

                         @Override
                         public FixedPointNumber getQuantity() {
                             return FixedPointNumber.TEN;
                         }
                     });
                     return list;
                 }

                 @Override
                 public FixedPointNumber getLastMarketClosePrice() {
                     return null;
                 }

                 @Override
                 public String getLastMarketClosePriceTimeStamp() {
                     return null;
                 }

                 @Override
                 public FixedPointNumber getMarketClosePrice() {
                     return null;
                 }

                 @Override
                 public long getMarketClosePriceTimeStamp() {
                     return 0;
                 }

                 @Override
                 public FixedPointNumber getLastTradedPrice() {
                     return null;
                 }

                 @Override
                 public FixedPointNumber getDailyHighestTradedPrice() {
                     return null;
                 }

                 @Override
                 public FixedPointNumber getDailyLowestTradedPrice() {
                     return null;
                 }

                 @Override
                 public long getTimeStamp() {
                     return 0;
                 }
             });
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void logout(LogoutCallBack logoutCallBack) {
          logoutCallBack.onSuccess();
    }

    @Override
    public void placeMarketOrder(MarketOrderSpecification marketOrderSpecification, OrderCallback orderCallback) {
         orderCallback.onSuccess(123l);
        orderBookEventListener.notify(new OrderBookEvent() {
            @Override
            public long getInstrumentId() {
                return 123;
            }

            @Override
            public FixedPointNumber getValuationBidPrice() {
                return null;
            }

            @Override
            public FixedPointNumber getValuationAskPrice() {
                return null;
            }

            @Override
            public List<PricePoint> getBidPrices() {
                return null;
            }

            @Override
            public List<PricePoint> getAskPrices() {
                return null;
            }

            @Override
            public FixedPointNumber getLastMarketClosePrice() {
                return null;
            }

            @Override
            public String getLastMarketClosePriceTimeStamp() {
                return null;
            }

            @Override
            public FixedPointNumber getMarketClosePrice() {
                return null;
            }

            @Override
            public long getMarketClosePriceTimeStamp() {
                return 0;
            }

            @Override
            public FixedPointNumber getLastTradedPrice() {
                return null;
            }

            @Override
            public FixedPointNumber getDailyHighestTradedPrice() {
                return null;
            }

            @Override
            public FixedPointNumber getDailyLowestTradedPrice() {
                return null;
            }

            @Override
            public long getTimeStamp() {
                return 0;
            }
        });
    }

    @Override
    public void placeLimitOrder(LimitOrderSpecification limitOrderSpecification, OrderCallback orderCallback) {

    }

    @Override
    public void cancelOrder(CancelOrderRequest cancelOrderRequest, OrderCallback orderCallback) {

    }

    @Override
    public void amendStops(AmendStopsRequest amendStopsRequest, OrderCallback orderCallback) {

    }

    @Override
    public void subscribe(SubscriptionRequest subscriptionRequest, SubscribeCallBack subscribeCallBack) {
    }

    @Override
    public void registerAccountStateEventListener(AccountStateEventListener accountStateEventListener) {

    }

    @Override
    public void registerHistoricMarketDataEventListener(HistoricMarketDataEventListener historicMarketDataEventListener) {

    }

    @Override
    public void registerOrderBookEventListener(OrderBookEventListener orderBookEventListener) {

        this.orderBookEventListener = orderBookEventListener;
    }

    @Override
    public void registerPositionEventListener(PositionEventListener positionEventListener) {

    }

    @Override
    public void registerExecutionEventListener(ExecutionEventListener executionEventListener) {

    }

    @Override
    public void registerOrderEventListener(OrderEventListener orderEventListener) {

    }

    @Override
    public void registerInstructionRejectedEventListener(InstructionRejectedEventListener instructionRejectedEventListener) {

    }

    @Override
    public void registerStreamFailureListener(StreamFailureListener streamFailureListener) {

    }

    @Override
    public void registerHeartbeatListener(HeartbeatEventListener heartbeatEventListener) {

    }

    @Override
    public AccountDetails getAccountDetails() {
        return new AccountDetails(123,"username","GBP","legal entity","locale",true);
    }

    @Override
    public void requestAccountState(AccountStateRequest accountStateRequest, AccountStateCallback accountStateCallback) {

    }

    @Override
    public void requestHistoricMarketData(HistoricMarketDataRequest historicMarketDataRequest, HistoricMarketDataCallback historicMarketDataCallback) {

    }

    @Override
    public void requestHeartbeat(HeartbeatRequest heartbeatRequest, HeartbeatCallback heartbeatCallback) {

    }

    @Override
    public void searchInstruments(SearchInstrumentRequest searchInstrumentRequest, SearchInstrumentCallback searchInstrumentCallback) {

    }
}
