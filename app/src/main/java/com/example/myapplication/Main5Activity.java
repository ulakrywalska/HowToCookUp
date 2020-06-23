package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main5Activity extends AppCompatActivity {
    ArrayList<String> myList = new ArrayList<String>();
    Integer[] images = {R.drawable.food};
    public static DataBase mSQLiteHelper;
    List<DataBaseHelper> myList2 = new ArrayList<DataBaseHelper>();
    public void clickFunc(View view){
        startActivity(new Intent(getApplicationContext(),PulpitActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
//        LinearLayout ll = new LinearLayout(this);
//        TextView tx= new TextView(this);
//        tx.setText("ANDROID APP");
//        ll.addView(tx);
//        setContentView(ll);
        ImageView imageee = (ImageView) findViewById(R.id.imageView8);
        imageee.animate().alpha(0.25f).setDuration(0);


        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Object> object = (ArrayList<Object>) args.getSerializable("ARRAYLIST");

        mSQLiteHelper = new DataBase(this, "RECORDDB.sqlite", null, 1);
        mSQLiteHelper.setupDatabase();
        final List<String> strings = new ArrayList<String>(object.size());
        for (Object o : object) {
            strings.add(o != null ? o.toString() : null);
        }
        Log.i("megawazne", strings.toString());
        myList2 = mSQLiteHelper.getElements(strings);


        ListView MyListView = (ListView) findViewById(R.id.finalList);
        for(DataBaseHelper db:myList2){
            myList.add(db.getNazwa());
        }
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, myList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text size 25 dip for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,40);

                // Get the Layout Parameters for ListView Current Item View
//                ViewGroup.LayoutParams params = view.getLayoutParams();
//
//                // Set the height of the Item View
//                params.height = 50;
//                view.setLayoutParams(params);

                // Return the view
                return view;
            }
        };
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.listview_header,MyListView,false);
        MyListView.addHeaderView(header);

        // DataBind ListView with items from ArrayAdapter
        MyListView.setAdapter(arrayAdapter);

        MyListView.setAdapter(arrayAdapter);
        MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Main5Activity.this, Main6Activity.class);
                String clicked = myList2.get(position-1).getOpis();
                Log.i("Co klinal",clicked);
                intent.putExtra("clicked",clicked);
                Log.i("listaLength", String.valueOf(myList2.isEmpty()));
                    startActivity(intent);
            }
        });
//        TextView dynamicTextView = new TextView(this);
//        dynamicTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//        dynamicTextView.setText(" Hello World ");
//        EditText et = new EditText(this);
//
//        et.setText("weeeeeeeeeee~!");
//        LinearLayout layout=(LinearLayout)findViewById(R.id.caly);
//        LinearLayout my_root = (LinearLayout) findViewById(R.id.caly);
//        layout.addView(et);
    }
}
