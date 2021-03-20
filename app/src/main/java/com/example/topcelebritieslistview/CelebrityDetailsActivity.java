package com.example.topcelebritieslistview;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

class CelebrityDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button saveBT = (Button) findViewById(R.id.button_save);
        Button cancelBT = (Button) findViewById(R.id.button_cancel);

        TextView etfname = (TextView) findViewById(R.id.etfname);
        TextView etlname =(TextView) findViewById(R.id.etlname);
        TextView etDate = (TextView) findViewById(R.id.etDate);

        cancelBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
        saveBT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String fname = etfname.getText().toString();
                String lname = etlname.getText().toString();
                String date = etDate.getText().toString();

                Celebrity celebrity = new Celebrity(fname,lname,date);

                myHelper.add(celebrity);
                Intent main = new Intent(v.getContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
            }
        });
        
        myHelper = new DatabaseHelper(this);
        myHelper.open();

    }







}
