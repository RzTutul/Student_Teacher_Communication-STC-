package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddStudent extends AppCompatActivity implements View.OnClickListener {

    AutoCompleteTextView autoCompleteTextView;
    Button button;
    EditText usertxt, passtxt;
    private List<Student> studentList;
    private CustomAdapter customAdapter;
    ProgressBar progressBar;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstudent);

        setTitle("Add Login Member");

        button = findViewById(R.id.loginbtn);
        usertxt = findViewById(R.id.userid);
        passtxt = findViewById(R.id.passid);
        progressBar = findViewById(R.id.ProgressBarID);
        autoCompleteTextView = findViewById(R.id.autocompleteId);
        button.setOnClickListener(this);

        String[] Catagories = getResources().getStringArray(R.array.Catagories);

        ArrayAdapter adapter = new ArrayAdapter(AddStudent.this, android.R.layout.simple_list_item_1, Catagories);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name;
                name = autoCompleteTextView.getText().toString();
                Toast.makeText(AddStudent.this, name, Toast.LENGTH_SHORT).show();
            }
        });



        databaseReference = FirebaseDatabase.getInstance().getReference("Login");
        studentList = new ArrayList<>();
        customAdapter = new CustomAdapter(AddStudent.this,studentList);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.loginbtn) {

            SaveData();
        }


    }

    private void SaveData() {
        String id = usertxt.getText().toString().trim();
        String pass = passtxt.getText().toString().trim();
        String catagories = autoCompleteTextView.getText().toString().trim();

        String key = databaseReference.push().getKey();
        Student student = new Student(id, pass, catagories);
        databaseReference.child(key).setValue(student);
        Toast.makeText(getApplicationContext(), "succesfully", Toast.LENGTH_SHORT).show();
    }
}
