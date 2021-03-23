package com.example.topcelebritieslistview;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class CelebrityDetailsActivity extends AppCompatActivity {

    private DatabaseHelper myHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrity_details);

        Button saveBT = (Button) findViewById(R.id.button_save);
        Button cancelBT = (Button) findViewById(R.id.button_cancel);
        TextView tvId = (TextView) findViewById(R.id.tvId);
        EditText etfname = (EditText) findViewById(R.id.etfname);
        EditText etlname =(EditText) findViewById(R.id.etlname);
        EditText etDate = (EditText) findViewById(R.id.etDate);

        //Declaration de la fonction callback datepicker
        DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                etDate.setText(new StringBuilder().append(month +1).
                        append("-").append(dayOfMonth).append("-").append(year).append(" "));
            }
        };


        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }

            private void showDatePicker() {
                DatePickerFragment date= new DatePickerFragment();
                final Calendar c = Calendar.getInstance();
                Bundle args = new Bundle();
                args.putInt("day",c.get(Calendar.DAY_OF_YEAR));
                args.putInt("month",c.get(Calendar.MONTH));
                args.putInt("year",c.get(Calendar.YEAR));

                date.setArguments(args);
                date.setCallBack(onDate);
                date.show(getSupportFragmentManager(),"Date Picker");
            }
        });


        cancelBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        


        myHelper = new DatabaseHelper(this);
        myHelper.open();

        Intent intent = getIntent();
        boolean fromAdd= intent.getBooleanExtra("fromAdd",false);

        if(!fromAdd){
            Bundle b= intent.getExtras();
            Celebrity vSelectedCelebrity= b.getParcelable("SelectedCelebrity");
            tvId.setText(String.valueOf(vSelectedCelebrity.getId()));
            etfname.setText(vSelectedCelebrity.getName());
            etlname.setText(vSelectedCelebrity.getLastName());
            etDate.setText(vSelectedCelebrity.getDate());

        }


        saveBT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String fname = etfname.getText().toString();
                String lname = etlname.getText().toString();
                String date = etDate.getText().toString();


                if(fromAdd){
                    Celebrity celebrity = new Celebrity(fname,lname,date);
                    myHelper.add(celebrity);
                    Intent main = new Intent(v.getContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(main);
                }else{
                    long id = Long.parseLong(tvId.getText().toString());
                    Celebrity celebrity = new Celebrity(id,etfname.getText().toString(),etlname.getText().toString(),etDate.getText().toString());
                    int n = myHelper.update(celebrity);

                    Intent main = new Intent(v.getContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(main);


                }


            }
        });


    }

}
