package daytrader.util;

import com.lmax.api.FailureResponse;
import com.lmax.api.SubscribeCallBack;

public class DefaultSubscriptionCallback implements SubscribeCallBack
    {
        public void onSuccess()
        {
        }

        @Override
        public void onFailure(final FailureResponse failureResponse)
        {
            throw new RuntimeException("Failed");
        }
    }
