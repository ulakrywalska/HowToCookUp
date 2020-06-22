package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PulpitActivity extends AppCompatActivity {
//    SQLiteDatabase myDataBase;
//    SQLiteDatabase myDataBase2;
    public void Button4Function(View view){
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        ImageView image = (ImageView) findViewById(R.id.image_view);
        image.animate().alpha(0.5f).setDuration(6000);
        ImageView imagee = (ImageView) findViewById(R.id.imageView5);
        imagee.animate().translationYBy(-1500f).setDuration(1500);
        button.animate().translationXBy(1500f).setDuration(2000);
        button2.animate().translationXBy(1500f).setDuration(4000);
        button3.animate().translationXBy(1500f).setDuration(6000);
        button4.animate().rotation(960f).setDuration(2000);
        button4.animate().alpha(0f).setDuration(2000);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        try{
//            myDataBase = this.openOrCreateDatabase("Przepisy",MODE_PRIVATE,null);
//            myDataBase.execSQL("CREATE TABLE IF NOT EXISTS przepisy (ID serial, nazwaPrzepisu VARCHAR(60), constraint id_pk primary key (ID))");
//            myDataBase.execSQL("INSERT INTO przepisy(ID,nazwaPrzepisu) VALUES (1,'salatka')");
//            myDataBase2 = this.openOrCreateDatabase("Skladniki",MODE_PRIVATE,null);
//            myDataBase.execSQL("CREATE TABLE IF NOT EXISTS skladniki (ID serial, skladnik VARCHAR(60), constraint id_fk foreign key (ID) references Przepisy(ID))");
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
        setContentView(R.layout.activity_pulpit);
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        button.animate().translationXBy(-1500f).setDuration(0);
        button2.animate().translationXBy(-1500f).setDuration(0);
        button3.animate().translationXBy(-1500f).setDuration(0);
        button4.animate().alpha(0.35f).setDuration(0);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain3Activity();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain4Activity();
            }
        });
    }



    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("baza", myDataBase);
        startActivity(intent);
    }
    public void openMain3Activity(){
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }
    public void openMain4Activity(){
        Intent intent = new Intent(this, Main4Activity.class);
        startActivity(intent);
    }
}
