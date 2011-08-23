package daytrader.util;

import com.lmax.api.FailureResponse;
import com.lmax.api.SubscribeCallBack;

public class DefaultSubscriptionCallback implements SubscribeCallBack
    {
        public void onSuccess()
        {
            System.out.println("Subscription successful");
        }

        @Override
        public void onFailure(final FailureResponse failureResponse)
        {
            throw new RuntimeException("Failed");
        }
    }
