package com.example.myapplication2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class activity_book_appointment extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    TextView tv = findViewById(R.id.textviewMchecktitle);
    private DatePickerDialog dpd;
    private TimePickerDialog tpd;
    Button buttondate, buttontime, btnbook, btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_appointment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ed1 = findViewById(R.id.editTextMcheckfullname);
        ed2 = findViewById(R.id.editTextaddress);
        ed3 = findViewById(R.id.editTextMcheckpin);
        ed4 = findViewById(R.id.editTextMcheckcontact);
        buttondate = findViewById(R.id.buttondate);
        buttontime = findViewById(R.id.buttontime);
        btnback = findViewById(R.id.buttonback);
        btnbook = findViewById(R.id.buttonMcheckbook);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons fees"+fees+"/-");
    // date picker
        initDatePicker();
        buttondate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show();
            }
        });
    // time picker
        initTimerPicker();
        buttontime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tpd.show();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_book_appointment.this, Find_doctor_activity.class));

            }
        });
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext(),"healthcare", null, 1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                if (db.checkappointmentexist(username,title+" => "+ fullname,address,contact,buttondate.getText().toString(),buttontime.getText().toString())==1)
                {
                    Toast.makeText(getApplicationContext(), "Appointment already booked", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addorder(username,title+" => "+fullname,address,contact,0,buttondate.getText().toString(),buttontime.getText().toString(),Float.parseFloat(fees),"appointment");
                    Toast.makeText(getApplicationContext(), "Appointment already booked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(activity_book_appointment.this, home_activity.class ));
                }
            }
        });
    }
    private void initDatePicker(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        DatePickerDialog.OnDateSetListener  dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                i1= i1+ 1;
              buttondate.setText(i2 + "/" + i1 + "/" + i);

            }
        };

        dpd = new DatePickerDialog(this, style, dateSetListener,year,month,day);
        dpd.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);



    }

    private  void initTimerPicker()
    {
        Calendar cal = Calendar.getInstance();
        int hr = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int i, int i2) {
                buttontime.setText(i +":" + i2);
            }
        };


         tpd = new TimePickerDialog(this, style, timeSetListener, hr, min, true);

    }
}