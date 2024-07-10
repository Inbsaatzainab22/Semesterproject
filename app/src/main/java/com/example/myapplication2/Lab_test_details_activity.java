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

public class Lab_test_details_activity extends AppCompatActivity {
    TextView tvpackages = findViewById(R.id.textViewBM), tvtotalcost = findViewById(R.id.textViewBMDtotalcost);
    EditText eddetails = findViewById(R.id.editTextMBDmultiline);
    Button btnltaddtocart = findViewById(R.id.buttonBMDgotocart), btnltdback = findViewById(R.id.buttonBMDback);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        eddetails.setKeyListener(null);
        Intent intent = getIntent();
        tvtotalcost.setText(intent.getStringExtra("text1"));
        tvpackages.setText(intent.getStringExtra("text2"));
                tvtotalcost.setText("Total cost" + intent.getStringExtra("text3"+ "/-"));

        btnltdback.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              startActivity(new Intent(Lab_test_details_activity.this, Lab_test_activity.class));
                                          }
                                      }

        );
        btnltaddtocart.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                                              String username = sharedPreferences.getString("username", "").toString();
                                              String product = tvpackages.getText().toString();
                                              float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                                              Database db = new Database(getApplicationContext(), "healthcare", null, 1);

                                              if (db.checkcart(username, product )==1)
                                              {
                                                  Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                                              }
                                              else {
                                                  db= new Database(getApplicationContext(), "healthcare", null, 1);
                                                  db.addcart(username,product,price,"lab");
                                                  Toast.makeText(getApplicationContext(), "Record inserted to Cart", Toast.LENGTH_SHORT).show();
                                                  startActivity(new Intent(Lab_test_details_activity.this, Lab_test_activity.class));
                                          }
                                      }



        });

    }
}