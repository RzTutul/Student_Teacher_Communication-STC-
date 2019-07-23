package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminPanel extends AppCompatActivity implements View.OnClickListener {

    CardView teacher,Student,AddCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        teacher = findViewById(R.id.TeacherCardId);
        Student= findViewById(R.id.StudentCardID);
        AddCard = findViewById(R.id.AddCardID);

        teacher.setOnClickListener(this);
        Student.setOnClickListener(this);
        AddCard.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.StudentCardID)
        {
            Intent intent = new Intent(AdminPanel.this,StudentPanel.class);
            startActivity(intent);

        }
         if (view.getId()==R.id.TeacherCardId)
        {
            Intent intent = new Intent(AdminPanel.this,TeacherPanel.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.AddCardID) {
            Intent intent = new Intent(AdminPanel.this,AddStudent.class);
            startActivity(intent);
        }

    }
}
