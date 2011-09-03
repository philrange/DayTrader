package daytrader.gui;

import com.lmax.api.FixedPointNumbers;
import com.lmax.api.order.Order;
import com.lmax.api.orderbook.OrderBookEvent;
import daytrader.DayTrader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainScreen implements IMainScreen {


    private final DayTrader dayTrader;
    private JPanel mainPanel;
    private JButton logoutButton;
    private IBlotter blotter;
    private Quotes quotes;
    private JTextPane notifications;
    private JTextPane accountBalancePane;
    private double accountBalance;

    public MainScreen(final DayTrader dayTrader) {
        this.dayTrader = dayTrader;

        logoutButton.setMnemonic(KeyEvent.VK_ENTER);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dayTrader.logout();
            }
        });

    }


    @Override
    public void show(String stuff) {
        System.out.println("showing");

//        blotterPanel.add(blotter.getComponent());
//        quotesPanel.add(quotes);

        quotes.setStrategy(dayTrader.getStrategy());

        JFrame frame = new JFrame("DayTrader - Logged in as: " + stuff);
        frame.setPreferredSize(new Dimension(600,400));
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void onTrade(Order trade) {
        blotter.onTrade(trade.toString());
        if (trade.getStopReferencePrice() != null) {
            accountBalance += FixedPointNumbers.doubleValue(trade.getQuantity()) * FixedPointNumbers.doubleValue(trade.getStopReferencePrice());
        }
        accountBalancePane.setText(Double.toString(accountBalance));
    }

    @Override
    public void onPriceEvent(OrderBookEvent event) {
        if (!event.getAskPrices().isEmpty() && !event.getBidPrices().isEmpty()) {
            quotes.updatePrices(event.getBidPrices().get(0).getPrice(), event.getAskPrices().get(0).getPrice());
        }
    }

    @Override
    public void sendInfo(String s) {

    }

}
