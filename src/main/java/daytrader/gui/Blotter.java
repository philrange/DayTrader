package daytrader.gui;

import javax.swing.*;
import java.awt.*;

public class Blotter extends JPanel implements IBlotter {


    private JTextArea trades;
    private JPanel panel;
    private JLabel tradesLabel;

    @Override
    public void onTrade(String trade) {

         trades.append(trade);
    }

    @Override
    public Component getComponent() {
        return this;
    }
}
