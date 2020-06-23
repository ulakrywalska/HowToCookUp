package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Main6Activity extends AppCompatActivity {
    public void clickFunction(View view){
        startActivity(new Intent(getApplicationContext(),PulpitActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Intent intent = getIntent();
        TextView opis = (TextView) findViewById(R.id.finalnyOpis);
        opis.setText(intent.getStringExtra("clicked"));
        ImageView image = (ImageView) findViewById(R.id.imageView8);
        image.animate().alpha(0.25f).setDuration(0);

    }
    }
