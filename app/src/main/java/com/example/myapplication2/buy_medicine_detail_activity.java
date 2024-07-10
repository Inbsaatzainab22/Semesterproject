package com.example.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class buy_medicine_detail_activity extends AppCompatActivity {
    TextView tvpackagename = findViewById(R.id.textViewBM), tvtotalcost = findViewById(R.id.textViewBMDtotalcost);
    EditText eddetails = findViewById(R.id.editTextMBDmultiline);
    Button btnmedback = findViewById(R.id.buttonmediback);
    Button btnaddmedtocart = findViewById(R.id.buttonmedigotocart);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        eddetails.setKeyListener(null);
       Intent intent;
        intent = getIntent();
        tvpackagename.setText(intent.getStringExtra("text1"));
        eddetails.setText(intent.getStringExtra("text2"));
        tvtotalcost.setText(intent.getStringExtra("text3")+"/-");

        btnmedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buy_medicine_detail_activity.this, buy_medicine_activity.class));
            }
        });
        btnaddmedtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                String product = tvpackagename.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcare", null, 1);
                if (db.checkcart(username, product )==1)
                {
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                }
                else {
                    db= new Database(getApplicationContext(), "healthcare", null, 1);
                    db.addcart(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(), "Record inserted to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(buy_medicine_detail_activity.this, buy_medicine_activity.class));
                }
            }
        });
    }
}