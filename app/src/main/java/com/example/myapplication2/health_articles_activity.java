package com.example.myapplication2;

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

public class health_articles_activity extends AppCompatActivity {
    private String [][] health_details=
            {
                    {"WALKING DAILY"," "," "," ","Click for more details"},
                    {"COVID-19 HOME CARE"," "," "," ","Click for more details"},
                    {"SMOKING INJURIOUS"," "," "," ","Click for more details"},
                    {"MENSTURAL CARE"," "," "," ","Click for more details"},
                    {"HEALTHY DIET"," "," "," ","Click for more details"},
            };
    private int[] images =
            {
                    R.drawable.health1,
                    R.drawable.health2,
                    R.drawable.health3,
                    R.drawable.health4,
                    R.drawable.health5,
            };
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnhaback;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_health_articles);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnhaback= findViewById(R.id.buttonHADback);
        lst = findViewById(R.id.imageviewHAD);

        btnhaback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(health_articles_activity.this, home_activity.class));
            }
        });
        list = new ArrayList();
        for(int i=0; i<health_details.length; i++)
        {
            item = new HashMap<String, String>();
            item.put("line 1", health_details[i][0]);
            item.put("line 2", health_details[i][1]);
            item.put("line 3", health_details[i][2]);
            item.put("line 4", health_details[i][3]);
            item.put("line 5", health_details[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this , list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.linea,R.id.lineb,R.id.linec,R.id.lined,R.id.lined

                });

        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(health_articles_activity.this, health_articles_details_activity.class);
                it.putExtra("text1", health_details[i][0]);
                it.putExtra("text2",images[i] );
                startActivity(it);
            }
        });
    }
}