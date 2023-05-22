package com.example.globalbank.database.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(entities = {BeneModel.class},version = 1 , exportSchema = false)

public abstract class RoomDB extends RoomDatabase {

    private static RoomDB database;
    private static final String DATABASE_NAME = "database_name";

    public synchronized static RoomDB getInstance(Context context){
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),RoomDB.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    //create Dao
    public abstract MainDao mainDao();


}
