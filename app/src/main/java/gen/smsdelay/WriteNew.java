package gen.smsdelay;

import android.icu.util.Calendar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;

public class WriteNew extends AppCompatActivity {
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
        phoneString= phoneText.getText().toString();
        messageString = messageTest.getText().toString();

        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMS(phoneString, messageString);
            }
        });

    }


    public void schedule(String number, String message, DatePicker datePicker)
    {
        Date calendar;
        calendar = getDateFromDatePicker(datePicker);
    }

    public boolean sendSMS(String number, String message)
    {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, message, null, null);

        return true;
    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker)
    {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}