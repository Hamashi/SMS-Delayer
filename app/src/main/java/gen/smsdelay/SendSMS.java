package gen.smsdelay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;


public class SendSMS extends BroadcastReceiver {

    public static String MESSAGE;
    public static String NUMBER;

    public void onReceive(Context context, Intent intent) {

        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(NUMBER, null, MESSAGE, null, null);

    }

}
