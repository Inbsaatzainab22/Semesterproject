package com.example.myapplication2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class cart_buy_medicine_activity extends AppCompatActivity {
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    TextView tvtcost = findViewById(R.id.textViewBMtcost);
    private DatePickerDialog dpd;
    private Button btndeldate = findViewById(R.id.buttonBMcheckout), btncheckout = findViewById(R.id.buttonBMcheckout), btncartback = findViewById(R.id.buttoncartback);
    private String[][] packages= {};
    ListView lst = findViewById(R.id.listViewBMcart);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart_buy_medicine);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();
        Database db = new Database(getApplicationContext(),"myapplication2",null, 1);
        float tamount = 0;
        ArrayList dbdata = db.getcartdata(username,"medicine");
        Toast.makeText(getApplicationContext(), ""+dbdata, Toast.LENGTH_SHORT).show();

        packages = new String[dbdata.size()][];
        for (int i =0; i<packages.length;i++)
        {
            packages[i]= new String[5];
        }
        for (int i =0; i<dbdata.size();i++)
        {   String arrdata = dbdata.get(i).toString();
            String[] strdata = arrdata.split(java.util.regex.Pattern.quote("$"));
            packages[i][0] = strdata[0];
            packages[i][4] = "Cost : " + strdata[1]+"/-";
            tamount = tamount + Float.parseFloat(strdata[1]);
        }
        tvtcost.setText("Total Cost /- "+ tamount);
        list = new ArrayList();
        for(int i=0; i<packages.length; i++)
        {
            item = new HashMap<String, String>();
            item.put("line 1", packages[i][0]);
            item.put("line 2", packages[i][1]);
            item.put("line 3", packages[i][2]);
            item.put("line 4", packages[i][3]);
            item.put("line 5", packages[i][4]);
            list.add(item);

        }
        sa = new SimpleAdapter(this , list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.linea,R.id.lineb,R.id.linec,R.id.lined,R.id.lined

                });

        lst.setAdapter(sa);
        btncartback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(cart_buy_medicine_activity.this, buy_medicine_activity.class));
            }
        });
        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(cart_buy_medicine_activity.this, buy_medicine_book_activity.class);
                it.putExtra("price", tvtcost.getText());
                it.putExtra("date", btndeldate.getText());

                startActivity(it);


            }
        });
        // date picker
        initDatePicker();
        btndeldate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show();
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
                btndeldate.setText(i2 + "/" + i1 + "/" + i);

            }
        };

        dpd = new DatePickerDialog(this, style, dateSetListener,year,month,day);
        dpd.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);



    }

}