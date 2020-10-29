package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class DbGateway {
    private static DbGateway dbGateway;
    private static SQLiteDatabase db;


    public static DbGateway getInstance(Context context){
        if (dbGateway == null){
            dbGateway = new DbGateway(context);
        }
        return dbGateway;
    }

    private DbGateway(Context context){
        IniciarCriareFazerUpGradeDatabaseDbHelper dbHelper = new IniciarCriareFazerUpGradeDatabaseDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static SQLiteDatabase getDatabase(){

        return db;
    }
}