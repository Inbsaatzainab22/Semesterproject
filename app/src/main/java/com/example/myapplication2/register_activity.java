package com.example.myapplication2;

import android.content.Intent;
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

public class register_activity extends AppCompatActivity {

    EditText edusername, edemail, edpassword, edconfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edusername = findViewById(R.id.editTextregusername);
        edpassword = findViewById(R.id.editTextregpassword);
        edemail = findViewById(R.id.editTextemail);
        edconfirm = findViewById(R.id.editTextconpassword);
        btn = findViewById(R.id.buttonregister);
        tv = findViewById(R.id.textViewaccount);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edusername.getText().toString();
                String password = edpassword.getText().toString();
                String email = edemail.getText().toString();
                String confirm = edconfirm.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if (username.length()==0 || password.length()==0 || email.length()==0 || confirm.length()==0)
                {
                    Toast.makeText(getApplicationContext(), "please fill all details ", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(password.compareTo(confirm)==0)
                    {
                       if(isvalid(password)){
                           db.register(username,email,password);
                           Toast.makeText(getApplicationContext(), "Password is set  ", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(register_activity.this, Login_Activity.class));
                       }
                       else {
                           Toast.makeText(getApplicationContext(), "Password must fulfill the conditions ", Toast.LENGTH_SHORT).show();
                       }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Password and confirm password didn't match ", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register_activity.this, Login_Activity.class));
            }
        });
    }
    public static boolean isvalid ( String passwordhere){
         int f1=0, f2=0 , f3=0;
         if (passwordhere.length()< 0)
         {
              return  false;
         }
         else {
             for (int p=0; p<passwordhere.length();p++)
             {
                 if(Character.isLetter(passwordhere.charAt(p)))
                 {
                     f1=1;
                 }
             }
             for (int r=0; r<passwordhere.length();r++)
             {
                 if(Character.isDigit(passwordhere.charAt(r)))
                 {
                     f2=1;
                 }
             }
             for (int s=0; s<passwordhere.length();s++)
             {
                 if(Character.isLetter(passwordhere.charAt(s)))
                 {
                     f3=1;
                 }
             }
             if(f1==1 && f2==1 && f3==1)
                 return true;
             return false;
    }

    }


    }

