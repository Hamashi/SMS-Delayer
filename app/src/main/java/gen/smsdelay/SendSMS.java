package gen.smsdelay;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;

public class SendSMS extends BroadcastReceiver {

    public static String MESSAGE;
    public static String NUMBER;

    public void onReceive(Context context, Intent intent) {

        SmsManager manager = SmsManager.getDefault();
        String message = intent.getParcelableExtra(MESSAGE);
        String number = intent.getParcelableExtra(NUMBER);

        manager.sendTextMessage(number, null, message, null, null);

        notif(context);

    }
    public void notif(Context context){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.delaysms_launcher)
                        .setContentTitle("SMS Delayer")
                        .setContentText("SMS Sent");

        NotificationManager mNotifyMgr =
                (NotificationManager)  context.getSystemService(context.NOTIFICATION_SERVICE);

        mNotifyMgr.notify(1, mBuilder.build());
    }


}
