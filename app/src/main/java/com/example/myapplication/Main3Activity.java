package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main3Activity extends AppCompatActivity {
    public static DataBase mSQLiteHelper;
    int tmp=0;
    private Button button;
    ArrayList<String> ProductList2 = new ArrayList<String>();
    public void ClickFunctionAdd(View view){
        EditText nazwaPrzepsiu = (EditText) findViewById(R.id.nazwaPrzepisu);
        EditText opis = (EditText) findViewById(R.id.opis2);
        EditText skladniki = (EditText) findViewById(R.id.nazwaPrzepisu);
        mSQLiteHelper = new DataBase(this, "RECORDDB.sqlite", null, 1);
        mSQLiteHelper.setupDatabase();
        Log.i("getLastID()",mSQLiteHelper.getLastID().toString());
        int id = mSQLiteHelper.getLastID()+1;
        Log.i("testID",String.valueOf(id));
        Log.i("prawda",mSQLiteHelper.isCreate("q","test").toString());
        if(!(mSQLiteHelper.isCreate(nazwaPrzepsiu.getText().toString(),opis.getText().toString()))) {
            for (String s : ProductList2) {
                mSQLiteHelper.insertData(String.valueOf(id), nazwaPrzepsiu.getText().toString(), opis.getText().toString(), s);
            }
            startActivity(new Intent(getApplicationContext(), PulpitActivity.class));
            final Toast toast = Toast.makeText(getApplicationContext(), "Dodano Przepis \\nDziękujemy!", Toast.LENGTH_SHORT);
            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 1000);

        }
        else{
            final Toast toast = Toast.makeText(getApplicationContext(), "Dany przepis jest już dodany", Toast.LENGTH_SHORT);
            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 1000);
            nazwaPrzepsiu.setText("");
            opis.setText("");
            skladniki.setText("");
            ProductList2.removeAll(ProductList2);
        }
    }
    public void ClickFunction2(View view) {
        mSQLiteHelper = new DataBase(this, "RECORDDB.sqlite", null, 1);
        mSQLiteHelper.setupDatabase();
        EditText product2 = (EditText) findViewById(R.id.skladnik);//skladniki
        EditText product3 = (EditText) findViewById(R.id.nazwaPrzepisu);//nazwa
        final ListView MyListView = (ListView) findViewById(R.id.ListId);
        Log.i("Ula","Ula"+mSQLiteHelper.getLastName());
        ProductList2.add(product2.getText().toString());
//        if(mSQLiteHelper.getLastID()==0) {
//            mSQLiteHelper.insertData("1", "test".toString(), product2.getText().toString(), product3.getText().toString());
//        }
//        else                 if(product2.getText().toString().equals(mSQLiteHelper.getLastName())){
//            mSQLiteHelper.insertData(String.valueOf(mSQLiteHelper.getLastID()),"test",product2.getText().toString(),product3.getText().toString());
//        }
        if(tmp==0){
            final Toast toast = Toast.makeText(getApplicationContext(), "Dodano Produkt", Toast.LENGTH_SHORT);
            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
                }, 1000);

        }
//        else{
//            mSQLiteHelper.insertData(String.valueOf(mSQLiteHelper.getLastID()+1),"test",product2.getText().toString(),product3.getText().toString());
//        }
            for (int i = 0; i < ProductList2.size() - 1; i++) {
                if (ProductList2.get(i).equals(product2.getText().toString())) {
                    final Toast toast = Toast.makeText(getApplicationContext(), "Ten produkt został juz dodany", Toast.LENGTH_SHORT);
                    toast.show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1000);
                    ProductList2.remove(product2.getText().toString());

                } else {
//                    Toast.makeText(Main3Activity.this, "Dodano Produkt", Toast.LENGTH_SHORT).show();
                    final Toast toast = Toast.makeText(getApplicationContext(), "Dodano Produkt", Toast.LENGTH_SHORT);
                    toast.show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1000);
//                if(product2.getText().toString().equals(mSQLiteHelper.getLastName())){
//                    mSQLiteHelper.insertData(String.valueOf(mSQLiteHelper.getLastID()),"test",product2.getText().toString(),product3.getText().toString());
//                }
//                else{
//                    mSQLiteHelper.insertData(String.valueOf(mSQLiteHelper.getLastID()+1),"test",product2.getText().toString(),product3.getText().toString());
//                }
                }
            }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ProductList2);
        MyListView.setAdapter(arrayAdapter);
        MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductList2.remove(position);
                MyListView.invalidateViews();
//                Toast.makeText(Main3Activity.this, "Usunięto produkt", Toast.LENGTH_SHORT).show();
                final Toast toast = Toast.makeText(getApplicationContext(), "Usunięto produkt", Toast.LENGTH_SHORT);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 1000);
            }
        });
        tmp+=1;
        product2.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("ble", "Main3Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ImageView image = (ImageView) findViewById(R.id.opis);
        image.animate().alpha(0.25f).setDuration(0);
    }


}

