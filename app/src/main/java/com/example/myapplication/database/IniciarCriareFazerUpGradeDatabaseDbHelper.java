package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.database.contract.OquePrecisaFazerParaManipularTabelaProdutoContract;

public class IniciarCriareFazerUpGradeDatabaseDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NOME = "db.produto";
    public static final int DATABASE_VERSION = 1;

    public IniciarCriareFazerUpGradeDatabaseDbHelper(@Nullable Context context) {
        super(context, DATABASE_NOME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(OquePrecisaFazerParaManipularTabelaProdutoContract.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(OquePrecisaFazerParaManipularTabelaProdutoContract.removerTabela());
        db.execSQL(OquePrecisaFazerParaManipularTabelaProdutoContract.criarTabela());
    }
}
