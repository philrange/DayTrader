package daytrader.gui;

import com.lmax.api.FixedPointNumber;
import com.lmax.api.orderbook.PricePoint;

import javax.swing.*;

public class Quotes extends JPanel {
    private JButton bidButton;
    private JButton askButton;
    private JPanel panel;

    public void updatePrices(FixedPointNumber bid, FixedPointNumber ask) {
        bidButton.setText(bid.toString());
        askButton.setText(ask.toString());
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
