package com.example.myapplication.database.contract;

import com.example.myapplication.database.entity.MapearColunasProdutoEntity;

public final class OquePrecisaFazerParaManipularTabelaProdutoContract {

    private OquePrecisaFazerParaManipularTabelaProdutoContract(){}

    public static final String criarTabela(){
        return "CREATE_TABLE" +
                MapearColunasProdutoEntity.TABLE_NAME + "(" +
                MapearColunasProdutoEntity._ID + " INTEGER PRIMARY KEY, " +
                MapearColunasProdutoEntity.TABLE_NAME + "TEXT, " +
                MapearColunasProdutoEntity.COLLUMN_NAME_VALOR + "REAL)";
    }

    public static final String removerTabela(){
        return "DROP TABLE IF EXISTS" + MapearColunasProdutoEntity.TABLE_NAME;
    }
}
