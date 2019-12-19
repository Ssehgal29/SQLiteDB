package com.example.user.sqlitedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
 Mydatabase db;

 EditText ed,ed2,ed3,ed4;
 Button btn,btn2,btn3,btn4,btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= new Mydatabase(this); //super(mydatabase) will run sqliteopenhelper when obj created it calls const which is made in mydatabase class
        //when sqlitehelper create database it will call method onCreate in Mydatabase
        //this code is used to create database

        ed=findViewById(R.id.editText);
        ed2=findViewById(R.id.editText2);
        ed3=findViewById(R.id.editText3);
        ed4=findViewById(R.id.editText4);
        btn=findViewById(R.id.button);
        btn2=findViewById(R.id.button2);
        btn3=findViewById(R.id.button3);
        btn4=findViewById(R.id.button4);
        btn5=findViewById(R.id.button5);

        btn.setOnClickListener(new View.OnClickListener()  //enter record
        {
            @Override
            public void onClick(View view)
            {
              int id =Integer.parseInt(ed.getText().toString());
              String name = ed2.getText().toString();
              String cases = ed3.getText().toString();
              String desc = ed4.getText().toString();

              CriminalRecord record = new CriminalRecord(id,name,cases,desc);

              db.insertRecord(record);

                Toast.makeText(MainActivity.this, "DATA SAVED", Toast.LENGTH_SHORT).show();

                ed.setText("");
                ed2.setText("");
                ed3.setText("");
                ed4.setText("");


            }
        });
        btn2.setOnClickListener(new View.OnClickListener() //get data from single record
        {
            @Override
            public void onClick(View view)
            {
                String value =ed.getText().toString();

                if(value.equals(""))
                {
                    ed.setError("Please Enter Id");
                }
                else
                {
                    int id =Integer.parseInt(value);

                 CriminalRecord record =   db.getSingleRecord(id);

                    Toast.makeText(MainActivity.this, "Id:-"+record.getId()+"\nName:-"+record.getName()+"\nCases:-"+record.getCases()+"\nDesc:-"+record.getDesc(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() //get data from all records
        {
            @Override
            public void onClick(View view)
            {
             List<CriminalRecord> list =db.getAllRecord();
             for(CriminalRecord record:list)
             {
                 Toast.makeText(MainActivity.this, "Id:-"+record.getId()+"\nName:-"+record.getName()+"\nCases:-"+record.getCases()+"\nDesc:-"+record.getDesc(), Toast.LENGTH_SHORT).show();
             }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {  //update record
            @Override
            public void onClick(View view) {
                int id =Integer.parseInt(ed.getText().toString());
                String name = ed2.getText().toString();
                String cases = ed3.getText().toString();
                String desc = ed4.getText().toString();

                CriminalRecord record = new CriminalRecord(id,name,cases,desc);

                db.updateRecord(record);
                Toast.makeText(MainActivity.this, "RECORD UPDATED", Toast.LENGTH_SHORT).show();

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() { //to delete
            @Override
            public void onClick(View view) {

                String values = ed.getText().toString();
                if(values.equals(""))
                {
                    ed.setError("Please Enter the id");

                }
                else
                {
                    int id =Integer.parseInt(values);
                    db.deleteRecord(id);
                    Toast.makeText(MainActivity.this, "RECORD DELETED", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
