package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Student> {

    private Activity context;

    private List<Student> studentList;

    public CustomAdapter(Activity context, List<Student> studentList) {
        super(context, R.layout.simple_layout, studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.simple_layout, null, true);
        Student student = studentList.get(position);
        TextView id = view.findViewById(R.id.Suserid);
        TextView pass = view.findViewById(R.id.Spass);
        TextView catagories = view.findViewById(R.id.Scatagories);


        id.setText(student.getId());
        pass.setText(student.getPass());
        catagories.setText("Date:"+student.getCatagories());
        return view;
    }
}
