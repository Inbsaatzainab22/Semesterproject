package com.example.myapplication2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class Lab_test_activity extends AppCompatActivity {
   private String [][] packages = {
           {"Package 1 : Blood test", " "," "," ","899"},
           {"Package 2 : Immunity test ", " "," "," ","920"},
           {"Package 3 : Thyriod check up", " "," "," ","456"},
           {"Package 4 : Sugar test", " "," "," ","984"},
           {"Package 5 : Malaria test", " "," "," ","899"},
   };
   private String [] package_details =
           {
                "Blood Glucose Fasting\n" +
                        "Complete Hemogram\n" +
                        "HbA1c\n" +
                        "Iron studies\n" +
                        "Kidney Function test \n" +
                        "LDH serum \n" +
                        "Lipid protein profilen" +
                        "Liver Function test", "COVID-19", "Thyriod profile-total",
                        "Complete Hemogram\n"+
                        "CRP\n" +
                        "Iron studies\n" ,
                        "Kidney Function test\n" +
                                "Vitamin-D \n" +
                                "Liver Function test \n" +
                                "Lipid profile\n"
           };
   HashMap<String,String> item;
   ArrayList list;
   SimpleAdapter sa;
   Button btngotocart, btnback;
   ListView listview = findViewById(R.id.listViewcart);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btngotocart = findViewById(R.id.buttoncartback);
        btnback = findViewById(R.id.buttonHADback);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lab_test_activity.this, home_activity.class));
            }
        });
        list = new ArrayList();
        for(int i =0 ; i<packages.length; i++)
        {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line1", packages[i][1]);
            item.put("line1", packages[i][2]);
            item.put("line1", packages[i][3]);
            item.put("line1", "Total cost : "+ packages[i][4]+"/-");
            list.add(item);

            sa = new SimpleAdapter(this,list,
                    R.layout.multi_lines,
                    new String[]{"line1","line2","line3","line4","line5"},
                    new int[]{R.id.linea, R.id.lineb,R.id.linec,R.id.lined,R.id.linee});
            ListView lst = findViewById(R.id.listViewcart);
            listview.setAdapter(sa);
            lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long f) {
                    Intent it = new Intent(Lab_test_activity.this, Lab_test_details_activity.class);

                    it.putExtra("text1", packages[i][0]);
                    it.putExtra("text2", package_details[i]);
                    it.putExtra("text3", packages[i][4]);

                    startActivity(it);
                }

                });
            btngotocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Lab_test_activity.this, cart_lab_activity.class));
                }
            });

        }

    }
}