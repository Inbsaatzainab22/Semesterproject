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

public class buy_medicine_activity extends AppCompatActivity {
    private String [][] packages ={
            {"Vitamin E capsules "," "," "," "," 80"},
            {"Multi Vitamin Capsules "," "," "," "," 180"},
            {"Panadol "," "," "," "," 60"},
            {"Anti-Allergy tablets "," "," "," "," 100"},
            {"Indigestion treatment  "," "," "," "," 480"},
            {"Hydrocortisone Cream or Ointment  "," "," "," "," 300"},
            {"Glucose drips "," "," "," "," 200"},
            {"Anti-diarrhoea remedy "," "," "," "," 600"},
            {"trazodone "," "," "," "," 500"}
    };
private String [] packages_details={
        "Clears the skin\n"+
                "Reboost the collagens\n"+
                "offer numerous health benefits due to their potent antioxidant properties.",
        "Helps in glowing  skin\n"+
                "fill nutritional gaps in the diet, ensuring the body gets the necessary nutrients for optimal functioning  \n"+
                "helps to grow hair,nails , teeth",
        "Reduces the pain\n"+
                "better for headache, toothache, backpain\n"+
                "Increase blood circulation",
        "Remove allergies\n"+
                "helps to recover from allergy \n",
        "offer significant benefits for those suffering from digestive discomfort\n" +
                " Antacids work by quickly neutralizing stomach acid, providing fast relief from symptoms like heartburn and indigestion.\n " +
                "They are especially useful for immediate, short-term relief. ",
        "effective topical treatment for a variety of skin conditions and concerns.\n"+
                "suitable for treating conditions like dry skin, eczema, and minor irritations\n"+
                "can deliver active ingredients directly to the affected area",
        "beneficial for patients who are unable to eat or drink due to illness, surgery,\n"+
                "glucose drips can be used to support metabolic processes and hydration,\n"+
                "glucose drips help maintain blood sugar levels",
        "designed to alleviate the symptoms of diarrhea and restore normal bowel function.\n " +
                "They work in various ways to address the underlying causes and provide relief\n",
        "It works by affecting the balance of neurotransmitters in the brain, particularly serotonin, to improve mood,\n" +
                "alleviate anxiety, and promote a sense of well-being.\n"

};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnmedback, btnmedgotocart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
       btnmedback = findViewById(R.id.buttonmediback);
        btnmedgotocart = findViewById(R.id.buttonmedigotocart);
        lst = findViewById(R.id.listViewmedicine);

        btnmedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buy_medicine_activity.this, home_activity.class));
            }
        });
        btnmedgotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buy_medicine_activity.this, cart_buy_medicine_activity.class));
            }
        });
        list = new ArrayList();
        for(int i=0; i<packages.length; i++)
        {
            item = new HashMap<String, String>();
            item.put("line 1", packages[i][0]);
            item.put("line 2", packages[i][1]);
            item.put("line 3", packages[i][2]);
            item.put("line 4", packages[i][3]);
            item.put("line 5", "Total Cost : " + packages[i][4]+"/-");
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
                Intent it = new Intent(buy_medicine_activity.this, buy_medicine_detail_activity.class);
                it.putExtra("text1", packages[i][0]);
                it.putExtra("text2",packages_details[i] );
                it.putExtra("text3", packages[i][4] );
                startActivity(it);
            }
        });


    }
}