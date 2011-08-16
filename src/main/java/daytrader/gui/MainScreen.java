package daytrader.gui;

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

        JFrame frame = new JFrame("DayTrader - Logged in as: " + stuff);
        frame.setPreferredSize(new Dimension(600,400));
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void onTrade(String trade) {
        blotter.onTrade(trade);
    }

    @Override
    public void onPriceEvent(OrderBookEvent event) {
        quotes.updatePrices(event.getAskPrices().get(0).getPrice(), event.getBidPrices().get(0).getPrice());
    }

}
