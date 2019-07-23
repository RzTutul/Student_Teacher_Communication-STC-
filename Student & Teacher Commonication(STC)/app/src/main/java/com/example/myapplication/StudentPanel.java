package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class StudentPanel extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mtoggle;
    CardView Exam, classtime, notificationCard, CGPA, routine,result;

    TextView nametext;
    ImageView imageView;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_panel);

        setTitle("Main DashBoard");

        int[] image = {R.drawable.tutul, R.drawable.shuvo,R.drawable.sasty};

        mdrawerLayout = findViewById(R.id.navigation);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mtoggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        layout=findViewById(R.id.layoutID);

        result = findViewById(R.id.SResultCardID);

        if (loadColor()!=getResources().getColor(R.color.colorPrimary))
        {
            //layout.setBackgroundColor(loadColor());
        }


        imageView = findViewById(R.id.imageID);
        Exam = findViewById(R.id.classTestID);
        CGPA = findViewById(R.id.cgpaID);

        notificationCard = findViewById(R.id.notificationID);

        routine = findViewById(R.id.SroutineID);
        nametext = findViewById(R.id.nameID);

        Exam.setOnClickListener(this);

        notificationCard.setOnClickListener(this);

        CGPA.setOnClickListener(this);

        routine.setOnClickListener(this);

        result.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String text = bundle.getString("key");

            if (text.equals("15162103191")) {
                imageView.setBackgroundResource(image[0]);
                nametext.setText("Rasheduz Zaman Tutul");
            }
            if (text.equals("15162103201")) {
                imageView.setBackgroundResource(image[1]);
                nametext.setText("Bodruddza Shuvo");
            }   if (text.equals("15162103200")) {
                imageView.setBackgroundResource(image[2]);
                nametext.setText("Sasty Mohanta");
            }

        }


    }

    private int loadColor() {

        SharedPreferences sharedPreferences = getSharedPreferences("BackgroundColor", Context.MODE_PRIVATE);
        int SelectColor = sharedPreferences.getInt("myColor",getResources().getColor(R.color.colorPrimary));


        return SelectColor;
    }

    private void storeColor(int color) {

        SharedPreferences sharedPreferences = getSharedPreferences("BackgroundColor", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("myColor",color);
        editor.commit();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mtoggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.classTestID) {
            Intent intent = new Intent(StudentPanel.this, Ctlist.class);

            startActivity(intent);
        }
        else if (view.getId() == R.id.notificationID) {
            Intent i = new Intent(StudentPanel.this, NotifiactionView.class);
            startActivity(i);
        }
        else if (view.getId() == R.id.cgpaID) {
            Intent i = new Intent(StudentPanel.this, BUBTNX.class);
            i.putExtra("key", "cgpa");
            startActivity(i);
        }
           else if (view.getId() == R.id.SResultCardID) {
            Intent i = new Intent(StudentPanel.this, BUBTNX.class);
            i.putExtra("key", "result");
            startActivity(i);
        }



        else if (view.getId() == R.id.SroutineID) {


            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                String text = bundle.getString("key");

                if (text.equals("15162103191")) {
                    Intent intent = new Intent(StudentPanel.this, StudentRoutine.class);
                    intent.putExtra("key", "15162103191");
                    startActivity(intent);
                }
                if (text.equals("15162103201")) {
                    Intent intent = new Intent(StudentPanel.this, StudentRoutine.class);
                    intent.putExtra("key", "15162103201");
                    startActivity(intent);
                }

            }

        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.Home) {
            Intent intent = new Intent(StudentPanel.this, TeacherPanel.class);

            startActivity(intent);
        } else if (menuItem.getItemId() == R.id.setting) {

            layout.setBackgroundColor(getResources().getColor(R.color.Dark));
            storeColor(getResources().getColor(R.color.Dark));


        } else if (menuItem.getItemId() == R.id.teacherlistID) {
            Intent intent = new Intent(StudentPanel.this, TeacherList.class);
            startActivity(intent);
        }
        else if (menuItem.getItemId() == R.id.anxid) {
            Intent intent = new Intent(StudentPanel.this, BUBTNX.class);
            startActivity(intent);
        }

          else if (menuItem.getItemId() == R.id.bubtwebsiteID) {
            Intent intent = new Intent(StudentPanel.this, BUBTNX.class);
            intent.putExtra("key","bubtwebsite");
            startActivity(intent);
        }

          else if (menuItem.getItemId() == R.id.logoutId) {
            Intent intent = new Intent(StudentPanel.this, LoginForm.class);
            startActivity(intent);
        }



        return false;
    }
}
