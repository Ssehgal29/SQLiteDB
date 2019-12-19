package com.example.user.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Mydatabase extends SQLiteOpenHelper
{//inherit sqliteopenhelper to use sqlite implement both methods onCreate onUpgrade

    //instance variables defined

    public static final int DATA_VERSION =1;  //to help user or programmer to know how many times the db has been updated

    public static final String DATABASE_NAME = "mydb";

    public static final String TABLE_NAME ="criminal_record"; //no space in table name , no number in table name

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CASES = "cases";
    public static final String DESC = "desc";

    public Mydatabase(Context context) //constructor created keep only one ie context
    {
        super(context, DATABASE_NAME, null, DATA_VERSION); // name-database name factory-null keep it null always
        // null(it creates copy database used for testing purpose) , version ->DataVersion
        //super initialize parent class constructor obj etc
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)  //table create
    {//here we will write query and in caps only
        String query="CREATE TABLE " +TABLE_NAME + "(" + ID +" NUMBER PRIMARY KEY," + NAME + " TEXT," + CASES + " TEXT," + DESC + " TEXT);";

        sqLiteDatabase.execSQL(query);




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) //table upgrade
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME); //table delete
        onCreate(sqLiteDatabase); //jo table drop hoga wo hi firse create hoga

    }

    public void insertRecord(CriminalRecord record)  //when called this method criminal record would be accessed
    {
       SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues(); //data in values and key form

        values.put(ID,record.getId());
        values.put(NAME,record.getName());
        values.put(CASES,record.getCases());
        values.put(DESC,record.getDesc());

        database.insert(TABLE_NAME,null,values);

    }
    public CriminalRecord getSingleRecord(int id)
    {
         SQLiteDatabase db = getReadableDatabase();
       //to retrieve single data
        CriminalRecord record =null;
    Cursor cursor =  db.query(TABLE_NAME,new String [] {ID , NAME ,CASES,DESC},ID + "=?",new String[]{String.valueOf(id)},null,null,null) ; //if we want to get single data from sqlite database so we have to call query method and provide some query
    //query parameters are 1.string type table, 2.String type Column Array 3.String Selection 4.String Array Selection Arguements 5.Group by having order by
    //cursor is predefined class its main task is to point it stores data in a container
        if(cursor.moveToFirst()) //move to first is of boolean type if data is in 1st then true otherwise false
        {
            record = new CriminalRecord(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }
        return record;
    }

    public List<CriminalRecord>  getAllRecord()
    {
        SQLiteDatabase db = getReadableDatabase();
        List<CriminalRecord> list = new ArrayList<CriminalRecord>();

        String query="SELECT * FROM "+TABLE_NAME;
        Cursor cursor =db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do {

                   CriminalRecord record = new CriminalRecord();
                   record.setId(cursor.getInt(0));
                   record.setName(cursor.getString(1));
                   record.setCases(cursor.getString(2));
                   record.setDesc(cursor.getString(3));

                   //add all records to the list
                    list.add(record);



            }
            while (cursor.moveToNext());

        }
        return list;
    }
public void updateRecord(CriminalRecord record)
{
    SQLiteDatabase db =getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(NAME,record.getName());
    values.put(CASES,record.getCases());
    values.put(DESC,record.getDesc());

    db.update(TABLE_NAME,values,ID+"=?",new String[]{String.valueOf(record.getId())});
}

public void deleteRecord(int id)
{
    SQLiteDatabase db =getWritableDatabase();


    db.delete(TABLE_NAME,ID+"=?",new String[]{String.valueOf(id)});
    db.close();

}
}
