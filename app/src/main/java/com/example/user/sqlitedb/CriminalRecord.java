package com.example.user.sqlitedb;

public class CriminalRecord
{  //table is created to store data so that the activities dont have to go to the database everytime  by this class it can easily access the data of the db and thus saving space by usong setter and getter
    public int getId() {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCases()
    {
        return cases;
    }

    public void setCases(String cases)
    {
        this.cases = cases;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    int id;
    String name ,cases ,desc;  //first highlight them and click on generate then on constructor click on all 4 optns when const created again come here highlight them right click on generate and on getter and setter


    public CriminalRecord(int id, String name, String cases, String desc) {
        this.id = id;
        this.name = name;
        this.cases = cases;
        this.desc = desc;
    }

    public CriminalRecord()
    {

    }


}
