package com.example.passwordlocker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "passwordDB";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "userpassword";

    private static final String USERNAME_COL = "username";

    private static final String APPLICATION_COL = "application";

    private static final String PASSWORD_COL = "password";

    private static final String DESC_COL = "description";


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+TABLE_NAME + " ("
                + USERNAME_COL + " TEXT,"
                + APPLICATION_COL + " TEXT,"
                + PASSWORD_COL + " TEXT,"
                + DESC_COL + " TEXT)";

        sqLiteDatabase.execSQL(query);
    }

    public void addNewApplication(InfromationClass infromationClass){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(USERNAME_COL,infromationClass.getUserName());
        values.put(APPLICATION_COL,infromationClass.getApplicationName());
        values.put(PASSWORD_COL,infromationClass.getPassword());
        values.put(DESC_COL,infromationClass.getDescription());

        db.insert(TABLE_NAME , null , values);
        db.close();

    }

    public ArrayList<InfromationClass> showAll(){
        SQLiteDatabase db = this.getReadableDatabase();

        // to work here....... correct the where condition.......
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME , null);

        ArrayList<InfromationClass> infromationClassArrayList = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                infromationClassArrayList.add(new InfromationClass(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
            }while (cursor.moveToNext());
        }

        cursor.close();
        return infromationClassArrayList;

    }

    public InfromationClass getItemValue(String userName){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " where " + USERNAME_COL + " = '" +userName+"'";

        Cursor  cursor = database.rawQuery(query,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        InfromationClass infromationClass = new InfromationClass(cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));

        cursor.close();
        return  infromationClass;

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
