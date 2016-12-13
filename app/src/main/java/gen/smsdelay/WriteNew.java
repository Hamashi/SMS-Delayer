package gen.smsdelay;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
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
import android.widget.TimePicker;
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
    String phoneString;
    String messageString;

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
                sendSMS();
            }
        });
    }

    protected void sendSMS() {
        phoneString = phoneText.getText().toString();
        date = (DatePicker) findViewById(R.id.datePicker);
        time = (TimePicker) findViewById(R.id.simpleTimePicker);

        phoneString= phoneText.getText().toString();
        messageString = messageTest.getText().toString();

        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                schedule(phoneString, messageString, date, time);
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

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //alarmManager.set(AlarmManager.RTC_WAKEUP, timestamp, getService(this, 0, , PendingIntent.FLAG_ONE_SHOT));
    }

    public boolean sendSMS(String number, String message)
    {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, message, null, null);

        return true;
    }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

        @Override
        public void onRequestPermissionsResult(final int requestCode, String permissions[], final int[] grantResults) {
            sendButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                    if ( grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneString, null, messageString, null, null);
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
        });
    }
}