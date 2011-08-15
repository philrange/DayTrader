package daytrader.strategy;


public interface ITradingStrategy {

    void handlePriceEvent();

    void handleTradeEvent();

    void handleUserInput(String input);


}
