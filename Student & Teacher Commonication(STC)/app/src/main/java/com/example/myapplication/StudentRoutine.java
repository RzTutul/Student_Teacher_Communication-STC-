package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class StudentRoutine extends AppCompatActivity {

   ImageView r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Student Routine");
        setContentView(R.layout.activity_student_routine);

        int[] routine ={ R.drawable.routine1,R.drawable.routine2};

        r = findViewById(R.id.routineID);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String text = bundle.getString("key");

            if (text.equals("15162103191")) {
                Toast.makeText(StudentRoutine.this,text,Toast.LENGTH_SHORT).show();
                r.setBackgroundResource(routine[0]);
            }
            if (text.equals("15162103201")) {
                Toast.makeText(StudentRoutine.this,text,Toast.LENGTH_SHORT).show();
                r.setBackgroundResource(routine[1]);
            }


        }

    }
}
