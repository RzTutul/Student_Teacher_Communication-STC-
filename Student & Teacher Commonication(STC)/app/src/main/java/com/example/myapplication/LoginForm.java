package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginForm extends AppCompatActivity implements View.OnClickListener {
    AutoCompleteTextView autoCompleteTextView;
    Button button;
    EditText usertxt, passtxt;
    DatabaseReference databaseReference;
    Iterator iterator;
    private List<Student> studentList;
    private CustomAdapter customAdapter;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        databaseReference = FirebaseDatabase.getInstance().getReference("Student");
        button = findViewById(R.id.loginbtn);
        usertxt = findViewById(R.id.userid);
        passtxt = findViewById(R.id.passid);
        progressBar = findViewById(R.id.ProgressBarID);
        autoCompleteTextView = findViewById(R.id.autocompleteId);
        button.setOnClickListener(this);

        String[] Catagories = getResources().getStringArray(R.array.Catagories);

        ArrayAdapter adapter = new ArrayAdapter(LoginForm.this, android.R.layout.simple_list_item_1, Catagories);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name;
                name = autoCompleteTextView.getText().toString();
                Toast.makeText(LoginForm.this, name, Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("Login");
        studentList = new ArrayList<>();
        customAdapter = new CustomAdapter(LoginForm.this, studentList);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.loginbtn) {


            String id = usertxt.getText().toString();

            String pass = passtxt.getText().toString().trim();

            String catagories = autoCompleteTextView.getText().toString().trim();

            progressBar.setVisibility(View.VISIBLE);
            iterator = studentList.iterator();

            while (iterator.hasNext()) {
                Student st = (Student) iterator.next();
                String uid = st.getId();
                String upass = st.getPass();
                String ucata = st.getCatagories();

                if (catagories.equals("Student")) {
                    if (id.equals(uid) && pass.equals(upass)) {
                        progressBar.setVisibility(View.GONE);

                        Intent intent = new Intent(LoginForm.this, StudentPanel.class);
                        intent.putExtra("key", id);
                        startActivity(intent);

                    }


                } else if (catagories.equals("Teacher")) {
                    if (id.equals(uid) && pass.equals(upass)) {
                        Toast.makeText(getApplicationContext(), "ok" + id, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginForm.this, TeacherPanel.class);
                        startActivity(intent);
                    }

                } else if (catagories.equals("Admin")) {
                    if (id.equals(uid) && pass.equals(upass)) {
                        Toast.makeText(getApplicationContext(), "ok" + id, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginForm.this, AdminPanel.class);
                        startActivity(intent);
                    }
                }


            }
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


    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Student student = dataSnapshot1.getValue(Student.class);
                    studentList.add(student);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        super.onStart();
    }


}
