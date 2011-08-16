package daytrader.gui;

import java.awt.*;

public interface IBlotter  {


    void onTrade(String trade);

    Component getComponent();
}
