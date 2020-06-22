package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    public static DataBase mSQLiteHelper;
    ArrayList<String> ProductList2 = new ArrayList<String>();
    public void ClickFunction2(View view) {
        int tmp=0;
        mSQLiteHelper = new DataBase(this, "RECORDDB.sqlite", null, 1);
        mSQLiteHelper.setupDatabase();
        EditText product2 = (EditText) findViewById(R.id.editText);//nazwa
        EditText product3 = (EditText) findViewById(R.id.editText3);//skladnik
        final ListView MyListView = (ListView) findViewById(R.id.ListId);
        ProductList2.add(product2.getText().toString());

        if(mSQLiteHelper.getLastID()==0) {
            mSQLiteHelper.insertData("1", "test".toString(), product2.getText().toString(), product3.getText().toString());
        }
        else                 if(product2.getText().toString().equals(mSQLiteHelper.getLastName())){
            mSQLiteHelper.insertData(String.valueOf(mSQLiteHelper.getLastID()),"test",product2.getText().toString(),product3.getText().toString());
        }
        if(tmp==0){
            Toast.makeText( Main3Activity.this, "Dodano Produkt", Toast.LENGTH_SHORT).show();
        }
        else{
            mSQLiteHelper.insertData(String.valueOf(mSQLiteHelper.getLastID()+1),"test",product2.getText().toString(),product3.getText().toString());
        }
        for(int i=0 ;i<ProductList2.size()-1;i++){
            if(ProductList2.get(i).equals(product2.getText().toString())){
                Toast.makeText(Main3Activity.this, "Ten produkt został juz dodany", Toast.LENGTH_SHORT).show();
                ProductList2.remove(product2.getText().toString());
            }
            else{
                Toast.makeText(Main3Activity.this, "Dodano Produkt", Toast.LENGTH_SHORT).show();
                if(product2.getText().toString().equals(mSQLiteHelper.getLastName())){
                    mSQLiteHelper.insertData(String.valueOf(mSQLiteHelper.getLastID()),"test",product2.getText().toString(),product3.getText().toString());
                }
                else{
                    mSQLiteHelper.insertData(String.valueOf(mSQLiteHelper.getLastID()+1),"test",product2.getText().toString(),product3.getText().toString());
                }
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ProductList2);
        MyListView.setAdapter(arrayAdapter);
        MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductList2.remove(position);
                MyListView.invalidateViews();
                Toast.makeText(Main3Activity.this, "Usunięto produkt", Toast.LENGTH_SHORT).show();
            }
        });
        tmp+=1;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("ble","Main3Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ImageView image = (ImageView) findViewById(R.id.imageView2);
        image.animate().alpha(0.25f).setDuration(0);
    }

}

