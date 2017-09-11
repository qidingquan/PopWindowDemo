package com.example.qdq.popwindowdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layout_title;
    private LinearLayout layout_tab;
    private TextView tv_one;
    private TextView tv_two;
    private TextView tv_three;

    private ListPopwindow listPopwindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout_title= (LinearLayout) findViewById(R.id.layout_title);
        layout_tab= (LinearLayout) findViewById(R.id.layout_tab);
        tv_one= (TextView) findViewById(R.id.tv_one);
        tv_two= (TextView) findViewById(R.id.tv_two);
        tv_three= (TextView) findViewById(R.id.tv_three);

        tv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPop(view);
            }
        });

    }
    private void showPop(View view){
        if(listPopwindow==null){
            listPopwindow=new ListPopwindow(this);
        }
        listPopwindow.showAsDropDown(view);
    }
}
