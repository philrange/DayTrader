package daytrader.util;

import com.lmax.api.Session;
import com.lmax.api.StreamFailureListener;
import com.lmax.api.SubscribeCallBack;
import com.lmax.api.SubscriptionRequest;
import com.lmax.api.account.*;
import com.lmax.api.heartbeat.HeartbeatCallback;
import com.lmax.api.heartbeat.HeartbeatEventListener;
import com.lmax.api.heartbeat.HeartbeatRequest;
import com.lmax.api.order.*;
import com.lmax.api.orderbook.*;
import com.lmax.api.position.PositionEventListener;
import com.lmax.api.reject.InstructionRejectedEventListener;

public class MockSession implements Session {
    @Override
    public void start() {

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
