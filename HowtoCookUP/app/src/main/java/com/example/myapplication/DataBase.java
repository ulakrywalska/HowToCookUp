package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        public String getLastName(){
            SQLiteDatabase database = getWritableDatabase();
            Cursor c = database.rawQuery("Select nazwaPrzepisu FROM PRZEPISY ORDER BY ID DESC LIMIT 1",null);
            int n1 = c.getColumnIndex("nazwaPrzepisu");
            c.moveToFirst();
            String result="";
            Log.i("testLastName","test");
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
            Cursor c = database.rawQuery("Select ID FROM PRZEPISY ORDER BY ID DESC LIMIT 1",null);
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
