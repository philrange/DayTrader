package daytrader.gui;

import com.lmax.api.FixedPointNumber;
import com.lmax.api.orderbook.PricePoint;
import daytrader.DayTrader;
import daytrader.enums.TradeSide;
import daytrader.strategy.ITradingStrategy;

import javax.print.attribute.standard.Sides;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quotes extends JPanel {
    private JButton bidButton;
    private JButton askButton;
    private JPanel panel;
    private ITradingStrategy strategy;

    public Quotes() {

        bidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                     tradeOnBid();
            }
        });

        askButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                     tradeOnAsk();
            }
        });
    }

    private void tradeOnAsk() {
                        trade(TradeSide.BUY);
    }

    private void tradeOnBid() {
                 trade(TradeSide.SELL);
    }

    private void trade(TradeSide side) {
        strategy.handleUserInput(side);
    }

    public void updatePrices(FixedPointNumber bid, FixedPointNumber ask) {
        bidButton.setText(bid.toString());
        askButton.setText(ask.toString());
    }

    public void setStrategy(ITradingStrategy strategy) {
        this.strategy = strategy;
    }

}
