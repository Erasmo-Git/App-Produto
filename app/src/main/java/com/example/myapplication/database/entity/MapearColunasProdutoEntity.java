package com.example.myapplication.database.entity;

import android.provider.BaseColumns;

public final class MapearColunasProdutoEntity implements BaseColumns {

    private MapearColunasProdutoEntity(){}

    public static final String TABLE_NAME = "Produto";
    public static final String COLLUMN_NAME_NOME = "nome";
    public static final String COLLUMN_NAME_VALOR = "valor";
}
