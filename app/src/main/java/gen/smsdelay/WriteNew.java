package gen.smsdelay;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

    private Button sendButton;
    private EditText phoneText;
    private EditText messageTest;
    private String phoneString;
    private String messageString;
    private DatePicker date;
    private TimePicker time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sendButton = (Button) findViewById(R.id.sendButton);
        phoneText = (EditText) findViewById(R.id.phoneText);
        messageTest = (EditText) findViewById(R.id.messageText);
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

}