package daytrader.strategy;

import com.lmax.api.FixedPointNumber;
import com.lmax.api.FixedPointNumbers;
import org.junit.Test;

import java.math.BigDecimal;

public class WaitForRunOfSimilarTicksStrategyTest {


    @Test
    public void testHandlePriceEvent() throws Exception {


        FixedPointNumber a = FixedPointNumber.valueOf("1.2454");
        System.out.println(a.longValue());

    }

    @Test
    public void testHandleTradeEvent() throws Exception {

    }

    @Test
    public void testHandleUserInput() throws Exception {

    }

    @Test
    public void testGetTradeDetails() throws Exception {

    }
}
