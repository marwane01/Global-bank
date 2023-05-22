package com.example.globalbank.database.local;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_Bene")
public class BeneModel {

    @PrimaryKey(autoGenerate = true)

    public int id;

    @ColumnInfo(name = "full_name")
    String full_name;
    @ColumnInfo(name = "RIB")
    String RIB;

    public BeneModel() {
    }

    public BeneModel(String full_name, String RIB) {

        this.full_name = full_name;
        this.RIB = RIB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getRIB() {
        return RIB;
    }

    public void setRIB(String RIB) {
        this.RIB = RIB;
    }
}
