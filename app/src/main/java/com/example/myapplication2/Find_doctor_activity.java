package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Find_doctor_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_doctor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        CardView back = findViewById(R.id.cardfdback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Find_doctor_activity.this, home_activity.class));
            }
        });
        CardView fphysician = findViewById(R.id.cardfdfamilyphysican);
        fphysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Find_doctor_activity.this, doctor_details_activity.class);
                it.putExtra("title", "Family Physician");
                startActivity(it);
            }
        });

        CardView dietician = findViewById(R.id.cardfddietician);
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Find_doctor_activity.this, doctor_details_activity.class);
                it.putExtra("title", "Dietician");
                startActivity(it);
            }
        });
        CardView dentist = findViewById(R.id.cardfddentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Find_doctor_activity.this, doctor_details_activity.class);
                it.putExtra("title", "Dentist");
                startActivity(it);
            }
        });
        CardView surgeon = findViewById(R.id.cardfdsurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Find_doctor_activity.this, doctor_details_activity.class);
                it.putExtra("title", "Surgeon");
                startActivity(it);
            }
        });
        CardView cardiologist = findViewById(R.id.cardfdcardiologist);
        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Find_doctor_activity.this, doctor_details_activity.class);
                it.putExtra("title", "Cardilologist");
                startActivity(it);
            }
        });
    }
}