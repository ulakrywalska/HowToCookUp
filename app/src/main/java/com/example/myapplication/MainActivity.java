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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button button;
    int tmp=0;
    public static DataBase mSQLiteHelper;
    ArrayList<String> ProductList = new ArrayList<String>();
    public void searchFunction2(View view){
//        List<DataBaseHelper> myList = new ArrayList<DataBaseHelper>();
        mSQLiteHelper = new DataBase(this, "RECORDDB.sqlite", null, 1);
        mSQLiteHelper.setupDatabase();
//        myList = mSQLiteHelper.getElements(ProductList);
//        Log.i("wazne",myList.get(0).getNazwa()+myList.get(0).getOpis());
//        startActivity(new Intent(getApplicationContext(),Main5Activity.class));


//        Intent intent = new Intent(getApplicationContext(), Main5Activity.class);
//        intent.putExtra("myList", ProductList);
//        startActivity(intent);

        Intent intent = new Intent(MainActivity.this, Main5Activity.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)ProductList);
        intent.putExtra("BUNDLE",args);
        if(!(mSQLiteHelper.getElements(ProductList).isEmpty())) {
            startActivity(intent);
        }
                        else {
            final Toast toast = Toast.makeText(getApplicationContext(), "Nie znaleziono przepisu\n     Przykro nam\nSpróbuj jeszcze raz", Toast.LENGTH_SHORT);
            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 3000);
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }
    public void ClickFunction(View view){
        EditText product = (EditText) findViewById(R.id.ProductId);
      final   ListView MyListView = (ListView) findViewById(R.id.ListID);
      /*  for(int i=0;i<ProductList.size();i++){
            if(ProductList.get(i)!=product.getText().toString()){
                Toast.makeText(MainActivity.this, "Ten prodkut został już dodany", Toast.LENGTH_SHORT).show();
            }*/

            ProductList.add(product.getText().toString());
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

        for(int i=0 ;i<ProductList.size()-1;i++){
                if(ProductList.get(i).equals(product.getText().toString())){
                    final Toast toast = Toast.makeText(getApplicationContext(), "Ten produkt został juz dodany", Toast.LENGTH_SHORT);
                    toast.show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1000);
                    ProductList.remove(product.getText().toString());
            }
            else{
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
        product.setText("");
        }
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ble","scena2");
        setContentView(R.layout.activity_main);
    ImageView image = (ImageView) findViewById(R.id.imageView3);
    image.animate().alpha(0.25f).setDuration(0);
//        button = (Button) findViewById(R.id.buttonSearch);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openMain5Activity();
//            }
//        });

}
    public void openMain5Activity(){
        Intent intent = new Intent(this, Main5Activity.class);
        startActivity(intent);
    }

}
