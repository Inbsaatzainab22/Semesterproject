package com.example.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class order_details_activty extends AppCompatActivity {
    private  String[][] order_details = {};

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnodback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_details_activty);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnodback = findViewById(R.id.buttonbackOD);
        lst = findViewById(R.id.listViewOD);

        btnodback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(order_details_activty.this, home_activity.class));
            }
        });

        Database db = new Database(getApplicationContext(),"healthcare",null, 1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();
        ArrayList dbData = db.getorderdata(username);

        order_details = new String[dbData.size()][];
        for (int i =0; i<order_details.length;i++)
        {
            order_details[i] = new String[5];
            String arrdata = dbData.get(i).toString();
            String[] strdata = arrdata.split(java.util.regex.Pattern.quote("$"));
            order_details[i][0]= strdata[0];
            order_details[i][1]= strdata[1];
            if(strdata[7].compareTo("medicine")==0)
            {
                order_details[i][3]= "Del : "+ strdata[4];
            }
            else {
                order_details[i][3]= "Del : "+ strdata[4] + " " + strdata[5];
            }
            order_details[i][2]= "Rs. "+ strdata[6];
            order_details[i][4]= strdata[7];
            list = new ArrayList();
            for(int j=0; j<order_details.length; j++)
            {
                item = new HashMap<String, String>();
                item.put("line 1", order_details[j][0]);
                item.put("line 2", order_details[j][1]);
                item.put("line 3", order_details[j][2]);
                item.put("line 4", order_details[j][3]);
                item.put("line 5", order_details[j][4]);
                list.add(item);

            }
            sa = new SimpleAdapter(this , list,
                    R.layout.multi_lines,
                    new String[]{"line1","line2","line3","line4","line5"},
                    new int[]{R.id.linea,R.id.lineb,R.id.linec,R.id.lined,R.id.lined

                    });

            lst.setAdapter(sa);

        }


    }
}