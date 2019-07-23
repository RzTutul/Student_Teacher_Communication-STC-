package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class attendceCustomAdapter extends BaseAdapter {

    int[] stdpic;
    String[] stdname;
    Context context; //rececive activity
    LayoutInflater inflater; //Conver xml to view


    public attendceCustomAdapter(Context context, String[] stdname, int[] stdpic) {

        this.context = context;
        this.stdname = stdname;
        this.stdpic = stdpic;

    }

    @Override
    public int getCount() {
        return stdname.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.attendacelistlayout, viewGroup, false);


            ImageView imageView = null;
            try {
                imageView = (ImageView) view.findViewById(R.id.iconimage);

                TextView textView = (TextView) view.findViewById(R.id.stdnameid);

                CheckBox checkBox = view.findViewById(R.id.list_view_item_checkbox);

                imageView.setImageResource(stdpic[i]);
                textView.setText(stdname[i]);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return view;
    }
}
