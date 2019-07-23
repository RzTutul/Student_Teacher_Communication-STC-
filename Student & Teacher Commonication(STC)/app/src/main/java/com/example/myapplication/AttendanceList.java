package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AttendanceList extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    DatabaseReference databaseReference;
    EditText id,name,intake;
    Button save;
    ListView listView;
    String[] stdname5;
    String[] stdname2;
    attendceCustomAdapter adapter;
    attendceCustomAdapter adapter2;
    Spinner spinner;



    int[] stdpic5 = {R.drawable.tutul, R.drawable.shuvo, R.drawable.sasty,R.drawable.tutul, R.drawable.shuvo, R.drawable.sasty,};
    int[] stdpic2 = {R.drawable.mamun, R.drawable.shahrukh, R.drawable.header,R.drawable.mamun, R.drawable.shahrukh, R.drawable.header};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);
        setTitle("Attendance List");

        mDisplayDate =  findViewById(R.id.tvDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AttendanceList.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);


            }
        };



        stdname5 = getResources().getStringArray(R.array.studentName);
        stdname2 = getResources().getStringArray(R.array.studentName2);


        listView = findViewById(R.id.listView);

        save = findViewById(R.id.saveID);

        save.setOnClickListener(this);




        //Make user Define CustomeAdapter;
        adapter = new attendceCustomAdapter(AttendanceList.this, stdname5, stdpic5);
        adapter2 = new attendceCustomAdapter(AttendanceList.this, stdname2, stdpic2);

        listView.setAdapter(adapter);


        // Spinner element
       spinner = (Spinner) findViewById(R.id.SpninnerintakeId);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Intake-32,Sec-5");
        categories.add("Intake-32,Sec-2");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


    }

    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.saveID)
        {
            SaveData();
        }

    }

    private void SaveData() {

        Toast.makeText(getApplicationContext(), "succesfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        if (position==0)
        {
            listView.setAdapter(adapter);
            String item = parent.getItemAtPosition(position).toString();
            // Showing selected spinner item
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        }
        if (position==1)
        {
            listView.setAdapter(adapter2);
            String item = parent.getItemAtPosition(position).toString();
            // Showing selected spinner item
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        }

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}

