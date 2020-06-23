package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Main4Activity extends AppCompatActivity {
    public void searchFunction(View view){
        EditText nazwa = (EditText) findViewById(R.id.nazwa);
        EditText  skladniki = (EditText) findViewById(R.id.skladniki);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ImageView image = (ImageView) findViewById(R.id.imageView4);
        image.animate().alpha(0.25f).setDuration(0);
    }
}