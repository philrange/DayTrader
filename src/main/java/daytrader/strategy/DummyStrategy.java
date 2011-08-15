package daytrader.strategy;

public class DummyStrategy implements ITradingStrategy {

    @Override
    public void handlePriceEvent() {
        System.out.println("got a price");
    }

    @Override
    public void handleTradeEvent() {
        System.out.println("got a trade");
    }

    @Override
    public void handleUserInput(String input) {
        System.out.println("User did " + input);

    }
}
