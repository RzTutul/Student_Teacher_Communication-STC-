package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClassTimeTeacher extends AppCompatActivity {

    EditText tname, tcourse, msg;
    Button send;
    DatabaseReference databaseReference;

    private List<Student> studentList;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_time_teacher);
        setTitle("Class Shedual/Changing");
        tcourse = findViewById(R.id.corsetxtid);
        msg = findViewById(R.id.textid);

        send = findViewById(R.id.sendid);

        databaseReference = FirebaseDatabase.getInstance().getReference("Notification");


        studentList = new ArrayList<>();
        customAdapter = new CustomAdapter(ClassTimeTeacher.this, studentList);


        send.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {

                String subject=tcourse.getText().toString().trim();
                String body=msg.getText().toString().trim();

                SaveData();

                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.notifiactionicon)
                                .setContentTitle(subject)
                                .setContentText(body);
                Intent notificationIntent = new Intent(getApplicationContext(), NotifiactionView.class);
                PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(contentIntent);

                // Add as notification
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(0, builder.build());


            }
        });

    }

    private void SaveData() {

        String coursecode = tcourse.getText().toString().trim();
        String topic = msg.getText().toString().trim();
        String date = "25 june 2019";

        String key = databaseReference.push().getKey();
        Student student = new Student(coursecode, topic, date);
        databaseReference.child(key).setValue(student);
        Toast.makeText(getApplicationContext(), "succesfully", Toast.LENGTH_SHORT).show();
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
    }
}
