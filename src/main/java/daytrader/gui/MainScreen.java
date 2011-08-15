package daytrader.gui;

import daytrader.DayTrader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainScreen implements IMainScreen {


    private DayTrader dayTrader;
    private JPanel mainPanel;
    private JButton logoutButton;

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

        JFrame frame = new JFrame("DayTrader - Logged in as: " + stuff);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
