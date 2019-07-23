package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TeacherPanel extends AppCompatActivity implements View.OnClickListener {

    CardView exam,tclass,attendace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_panel);

        setTitle("Main DashBoard");
        exam = findViewById(R.id.examshdualID);

        tclass = findViewById(R.id.tclasstimeID);

        attendace = findViewById(R.id.attenaceCardID);

        exam.setOnClickListener(this);
        tclass.setOnClickListener(this);
        attendace.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.examshdualID)
        {
            Intent intent = new Intent(TeacherPanel.this,ExamShedual.class);
            startActivity(intent);
        }
 else if (view.getId()==R.id.tclasstimeID)
        {
            Intent intent = new Intent(TeacherPanel.this,ClassTimeTeacher.class);
            startActivity(intent);
        }

 else  if(view.getId()==R.id.attenaceCardID)
        {
            Intent i = new Intent(TeacherPanel.this,AttendanceList.class);

            startActivity(i);
        }

 else  if(view.getId()==R.id.resultCardId)
        {
            Intent i = new Intent(TeacherPanel.this,ClassTimeTeacher.class);

            startActivity(i);
        }


    }
}
