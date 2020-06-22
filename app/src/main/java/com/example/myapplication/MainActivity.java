package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button button;
    ArrayList<String> ProductList = new ArrayList<String>();
    public void ClickFunction(View view){
        EditText product = (EditText) findViewById(R.id.ProductId);
      final   ListView MyListView = (ListView) findViewById(R.id.ListID);
      int tmp=0;
      /*  for(int i=0;i<ProductList.size();i++){
            if(ProductList.get(i)!=product.getText().toString()){
                Toast.makeText(MainActivity.this, "Ten prodkut został już dodany", Toast.LENGTH_SHORT).show();
            }*/

            ProductList.add(product.getText().toString());
        if(tmp==0){
            Toast.makeText( MainActivity.this, "Dodano Produkt", Toast.LENGTH_SHORT).show();
        }

        for(int i=0 ;i<ProductList.size()-1;i++){
                if(ProductList.get(i).equals(product.getText().toString())){
                    Toast.makeText(MainActivity.this, "Ten produkt został juz dodany", Toast.LENGTH_SHORT).show();
                    ProductList.remove(product.getText().toString());
            }
            else{
                    Toast.makeText( MainActivity.this, "Dodano Produkt", Toast.LENGTH_SHORT).show();
                }

        }

       //  Toast.makeText(MainActivity.this, "Dodano Produkt", Toast.LENGTH_SHORT).show();
     /*   for(int i=0;i<ProductList.size();i++){
            if(ProductList.get(i)==product.getText().toString()){
                Toast.makeText(MainActivity.this, "Ten produkt został juz wybrany", Toast.LENGTH_SHORT).show();
            }
        }*/
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ProductList);
        MyListView.setAdapter(arrayAdapter);
        MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductList.remove(position);
                MyListView.invalidateViews();
                Toast.makeText(MainActivity.this, "Usunięto produkt", Toast.LENGTH_SHORT).show();
            }
        });
        tmp+=1;
        }
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ble","scena2");
        setContentView(R.layout.activity_main);
    ImageView image = (ImageView) findViewById(R.id.imageView3);
    image.animate().alpha(0.25f).setDuration(0);
        button = (Button) findViewById(R.id.button2ID);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain5Activity();
            }
        });
        new DataBaseAsync();

}
    public void openMain5Activity(){
        Intent intent = new Intent(this, Main5Activity.class);
        startActivity(intent);
    }

}
