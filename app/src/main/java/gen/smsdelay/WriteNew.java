package gen.smsdelay;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.util.Log;
import java.util.Date;
import android.Manifest;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import java.util.Date;

import static android.app.PendingIntent.getService;

public class WriteNew extends AppCompatActivity {

    private DatePicker date;
    private TimePicker time;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0;
    Button sendButton;
    EditText phoneText;
    EditText messageTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sendButton = (Button) findViewById(R.id.sendButton);
        phoneText = (EditText) findViewById(R.id.phoneText);
        messageTest = (EditText) findViewById(R.id.messageText);

        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(phoneText.getText().toString(), null, messageTest.getText().toString(), null, null);
            }
        });
    }

    public void schedule(String number, String message, DatePicker datePicker, TimePicker time)
    {
        // Retrieving date
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        //Retrieving hour
        int hour = time.getHour();
        int minute = time.getMinute();

        Date date = new Date(year, month, day, hour, minute);
        long timestamp = date.getTime();

        Intent senderIntent = new Intent(this, SendSMS.class);
        senderIntent.putExtra(SendSMS.NUMBER, number);
        senderIntent.putExtra(SendSMS.MESSAGE, message);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, timestamp, getService(this, 0, senderIntent, PendingIntent.FLAG_ONE_SHOT));
    }
}

    /* Old Code for sms

    protected void sendSMS() {
        phoneString = phoneText.getText().toString();
        messageString = messageTest.getText().toString();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }
     //inside SetOnclickListenenr
                     //sendSMS();
                Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneText.getText().toString()));
                sms.putExtra("sms_body", messageTest.getText().toString());
                startActivity(sms);

    @Override
    public void onRequestPermissionsResult(final int requestCode,String permissions[],final int[] grantResults) {
        if (requestCode) {
             MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneText.getText().toString(), null, messageTest.getText().toString(), null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }

     */


