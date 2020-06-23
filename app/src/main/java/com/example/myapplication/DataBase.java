package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DataBase extends SQLiteOpenHelper{

        public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context, name, factory, version);
        }
        public void setupDatabase(){
            SQLiteDatabase database = getWritableDatabase();
//            String sql = "CREATE TABLE IF NOT EXISTS PRZEPISY(" +
//                    "id INTEGER PRIMARY KEY AUTOINCREMENT, "
//                    "nazwaPrzepisu VARCHAR, " +
//                    "opis VARCHAR)";

            String sql = "CREATE TABLE IF NOT EXISTS przepisy (ID VARCHAR(60), nazwaPrzepisu VARCHAR(60), opis VARCHAR(60), skladnik VARCHAR(60))";

            database.execSQL(sql);
        }

        //insertData
        public void insertData(String id,String name,String opis,String skladnik){
            SQLiteDatabase database = getWritableDatabase();
            //query to insert record in database table
//            String sql = "INSERT INTO PRZEPISY VALUES(NULL, ?, ?)";
//
//            SQLiteStatement statement = database.compileStatement(sql);
//            statement.clearBindings();
//
//            statement.bindString(1, name);
//            statement.bindString(2, opis);
//
//            statement.executeInsert();
            database.execSQL("INSERT INTO PRZEPISY (ID,nazwaPrzepisu,opis,skladnik) VALUES ('"+id+"','"+name+"','"+opis+"','"+skladnik+"')");
            database = getWritableDatabase();
            Cursor c = database.rawQuery("Select * FROM PRZEPISY",null);
            int n1 = c.getColumnIndex("ID");
            int n2 = c.getColumnIndex("nazwaPrzepisu");
            int n3 = c.getColumnIndex("opis");
            int n4 = c.getColumnIndex("skladnik");
            c.moveToFirst();
            while(c!=null) {
                Log.i("DB", "TestDB");
                Log.i("id", c.getString(n1));
                Log.i("name", c.getString(n2));
                Log.i("opis", c.getString(n3));
                Log.i("skladnik",c.getString(n4));
                if(c.isLast()){
                    break;
                }
                c.moveToNext();
            }
        }
        public List<DataBaseHelper> getElements(List<String> list){
            Log.i("start","Czy wchodzi tu?");
            HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
            ArrayList<DataBaseHelper> gfg = new ArrayList<DataBaseHelper>();
            SQLiteDatabase database = getWritableDatabase();
//            Collections.sort(list);
            Cursor c = database.rawQuery("Select * FROM PRZEPISY",null);
            database = getWritableDatabase();
            int n1 = c.getColumnIndex("ID");
            int n2 = c.getColumnIndex("skladnik");
            c.moveToFirst();
            while(c!=null) {
                hashMap.put(c.getString(n1),new ArrayList<String>());
                if(c.isLast()){
                    break;
                }
                c.moveToNext();
            }
            Log.i("hashmap1",hashMap.toString());
            c.moveToFirst();
            while(c!=null) {
                for ( String key : hashMap.keySet()) {
                    if(key.equals(c.getString(n1))){
                        hashMap.get(key).add(c.getString(n2));
                    }
                }
                if(c.isLast()){
                    break;
                }
                c.moveToNext();
            }
            c.moveToFirst();
            Log.i("hashmap2",hashMap.toString());

            for ( String key2 : hashMap.keySet() ) {
                if (hashMap.get(key2).containsAll(list)) {
                    Log.i("containsAll", "test");
                    Cursor c2 = database.rawQuery("Select nazwaPrzepisu, opis FROM PRZEPISY WHERE ID = '" + key2 + "'", null);
                    c2.moveToFirst();
                    int ww = c2.getColumnIndex("nazwaPrzepisu");
                    int ww2 = c2.getColumnIndex("opis");
//                    while (c2 != null) {
                        Log.i("blad", c2.getString(ww));
                        gfg.add(new DataBaseHelper(c2.getString(ww),c2.getString(ww2)));

//                        if (c2.isLast()) {
//                            break;
//                        }
//                        c2.moveToNext();
//                    }
                }
            }
//            Log.i("infoo",gfg.get(0).getNazwa()+gfg.get(0).getOpis());
            return gfg;
        }
        public Boolean isCreate(String nazwa,String opis){
            SQLiteDatabase database = getWritableDatabase();
            Cursor c2 = database.rawQuery("Select nazwaPrzepisu, opis FROM PRZEPISY WHERE nazwaPrzepisu = '" + nazwa + "' OR opis= '" + opis + "'" , null);
//            c2.moveToFirst();
//            int ww = c2.getColumnIndex("nazwaPrzepisu");
//            int ww2 = c2.getColumnIndex("opis");
////                    while (c2 != null) {
//            Log.i("blad", c2.getString(ww));

//                        if (c2.isLast()) {
//                            break;
//                        }
//                        c2.moveToNext();
//                    }
//            Log.i("infoo",gfg.get(0).getNazwa()+gfg.get(0).getOpis());
        return c2.moveToNext();
        }
        public String getLastName(){
            SQLiteDatabase database = getWritableDatabase();
            Cursor c = database.rawQuery("Select nazwaPrzepisu FROM PRZEPISY",null);
            int n1 = c.getColumnIndex("nazwaPrzepisu");
            c.moveToFirst();
            String result="";
            while(c!=null) {
                Log.i("123", "f"+c.getString(n1));
                result= c.getString(n1);
                if(c.isLast()){
                    break;
                }
                c.moveToNext();
            }
            return result;
        }
        public Integer getLastID(){
            SQLiteDatabase database = getWritableDatabase();
            Cursor c = database.rawQuery("Select ID FROM PRZEPISY",null);
            int n1 = c.getColumnIndex("ID");
            c.moveToFirst();
            int result=0;
            Log.i("bleble","test");
            while(c!=null) {
                Log.i("ble", "f"+c.getString(n1));
                result= Integer.parseInt(c.getString(n1));
                if(c.isLast()){
                    break;
                }
                c.moveToNext();
            }
            return result;
        }


        //deleteData
        public void deleteData(int id){
            SQLiteDatabase database = getWritableDatabase();
            //query to delete record using id
            String sql = "DELETE FROM TASKS WHERE id=?";

            SQLiteStatement statement = database.compileStatement(sql);
            statement.clearBindings();
            statement.bindDouble(1, (double)id);

            statement.execute();
            database.close();
        }

        public Cursor getData(String sql){
            SQLiteDatabase database = getReadableDatabase();
            return database.rawQuery(sql, null);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
