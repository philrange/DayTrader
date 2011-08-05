package daytrader;

import daytrader.util.LoginManager;

public class DayTrader {

    public static void main(String[] args) {

         new LoginManager("https://testapi.lmaxtrader.com", "CFD_DEMO");



        //keep running
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
