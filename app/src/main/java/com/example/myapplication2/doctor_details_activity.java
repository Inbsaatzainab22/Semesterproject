package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class doctor_details_activity extends AppCompatActivity {
   private String [][] doctor_details1 ={
           {"Doctor name : Zainab", "Hospital Address : Rawalpindi", " Exp : 5 years ", " Mobile no : 03127479888", "789"},
           {"Doctor name : Zuhaan", "Hospital Address : Lahore", " Exp : 6 years ", " Mobile no : 03165439888", "790"},
           {"Doctor name : Salar", "Hospital Address : Islamabad", " Exp : 5 years ", " Mobile no : 0313459888", "791"},
           {"Doctor name : Nouman", "Hospital Address : Peshawer", " Exp : 8 years ", " Mobile no : 031274798342", "792"},
           {"Doctor name : Faris", "Hospital Address : Jehlum", " Exp : 3 years ", " Mobile no : 031287322888", "795"},
   };
    private String [][] doctor_details2 ={
            {"Doctor name : Zainab", "Hospital Address : Rawalpindi", " Exp : 5 years ", " Mobile no : 03127479888", "789"},
            {"Doctor name : Zuhaan", "Hospital Address : Lahore", " Exp : 6 years ", " Mobile no : 03165439888", "790"},
            {"Doctor name : Salar", "Hospital Address : Islamabad", " Exp : 5 years ", " Mobile no : 0313459888", "791"},
            {"Doctor name : Nouman", "Hospital Address : Peshawer", " Exp : 8 years ", " Mobile no : 031274798342", "792"},
            {"Doctor name : Faris", "Hospital Address : Jehlum", " Exp : 3 years ", " Mobile no : 031287322888", "795"},
    };
    private String [][] doctor_details3 ={
            {"Doctor name : Zainab", "Hospital Address : Rawalpindi", " Exp : 5 years ", " Mobile no : 03127479888", "789"},
            {"Doctor name : Zuhaan", "Hospital Address : Lahore", " Exp : 6 years ", " Mobile no : 03165439888", "790"},
            {"Doctor name : Salar", "Hospital Address : Islamabad", " Exp : 5 years ", " Mobile no : 0313459888", "791"},
            {"Doctor name : Nouman", "Hospital Address : Peshawer", " Exp : 8 years ", " Mobile no : 031274798342", "792"},
            {"Doctor name : Faris", "Hospital Address : Jehlum", " Exp : 3 years ", " Mobile no : 031287322888", "795"},
    };
    private String [][] doctor_details4 ={
            {"Doctor name : Zainab", "Hospital Address : Rawalpindi", " Exp : 5 years ", " Mobile no : 03127479888", "789"},
            {"Doctor name : Zuhaan", "Hospital Address : Lahore", " Exp : 6 years ", " Mobile no : 03165439888", "790"},
            {"Doctor name : Salar", "Hospital Address : Islamabad", " Exp : 5 years ", " Mobile no : 0313459888", "791"},
            {"Doctor name : Nouman", "Hospital Address : Peshawer", " Exp : 8 years ", " Mobile no : 031274798342", "792"},
            {"Doctor name : Faris", "Hospital Address : Jehlum", " Exp : 3 years ", " Mobile no : 031287322888", "795"},
    };
    private String [][] doctor_details5 ={
            {"Doctor name : Zainab", "Hospital Address : Rawalpindi", " Exp : 5 years ", " Mobile no : 03127479888", "789"},
            {"Doctor name : Zuhaan", "Hospital Address : Lahore", " Exp : 6 years ", " Mobile no : 03165439888", "790"},
            {"Doctor name : Salar", "Hospital Address : Islamabad", " Exp : 5 years ", " Mobile no : 0313459888", "791"},
            {"Doctor name : Nouman", "Hospital Address : Peshawer", " Exp : 8 years ", " Mobile no : 031274798342", "792"},
            {"Doctor name : Faris", "Hospital Address : Jehlum", " Exp : 3 years ", " Mobile no : 031287322888", "795"},
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String [][] doctor_details={};
        TextView tv = findViewById(R.id.textViewHADtitle);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);
        HashMap<String, String> item;
        ArrayList list;
        SimpleAdapter sa;

        if(title.compareTo("Family Physician")==0)
        {
            doctor_details= doctor_details1;
        }
        else
        if(title.compareTo("Dietician")==0)
        {
            doctor_details= doctor_details2;
        }
        else
            if(title.compareTo("Dentist")==0)
        {
            doctor_details= doctor_details3;
        }
            else
        if(title.compareTo("Surgeon")==0)
        {
            doctor_details= doctor_details4;
        }
        else
        if(title.compareTo("Cardilologist")==0)
        {
            doctor_details= doctor_details5;
        }

        Button btn = findViewById(R.id.buttonHADback);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(doctor_details_activity.this, Find_doctor_activity.class));
            }
        });
        list = new ArrayList();
        for(int i=0; i<doctor_details.length; i++)
        {
            item = new HashMap<String, String>();
            item.put("line 1", doctor_details[i][0]);
            item.put("line 2", doctor_details[i][1]);
            item.put("line 3", doctor_details[i][2]);
            item.put("line 4", doctor_details[i][3]);
            item.put("line 5", "Cons Fees : " +doctor_details[i][4]+"/-");
            list.add(item);

        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.linea, R.id.lineb,R.id.linec,R.id.lined,R.id.linee});
        ListView lst = findViewById(R.id.imageviewHAD);
        lst.setAdapter(sa);
         String[][] finalDoctor_details = doctor_details;
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long f) {
                Intent it = new Intent(doctor_details_activity.this, activity_book_appointment.class);
                it.putExtra("text1", title);
                it.putExtra("text2", finalDoctor_details[i][0]);
                it.putExtra("text3", finalDoctor_details[i][1]);
                it.putExtra("text4", finalDoctor_details[i][3]);
                it.putExtra("text5", finalDoctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}