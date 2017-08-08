package com.sanju.developer.shoppercom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OwnerChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_choice);
        Button kurti= (Button) findViewById(R.id.kurti);
        Button legi= (Button) findViewById(R.id.legi);
        Button SuitUnstitche= (Button) findViewById(R.id.SuitUnstitche);
        Button top= (Button) findViewById(R.id.top);
        Button purse= (Button) findViewById(R.id.purse);
        Button suitprint= (Button) findViewById(R.id.suitprint);
        Button Woolens= (Button) findViewById(R.id.Woolens);
        Button more= (Button) findViewById(R.id.more);

        kurti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),KurtaAdd.class);
                startActivity(intent);

            }
        });
        legi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LeggingAdd.class);
                startActivity(intent);
                finish();

            }
        });
        SuitUnstitche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),HeavySuitAdd.class);
                startActivity(intent);

            }
        });
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),TopAdd.class);
                startActivity(intent);

            }
        });
        purse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PurseAdd.class);
                startActivity(intent);

            }
        });
        suitprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SuitPrintAdd.class);
                startActivity(intent);

            }
        });
        Woolens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),WoolenAdd.class);
                startActivity(intent);

            }
        }); more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MoreAdd.class);
                startActivity(intent);

            }
        });

    }
}
