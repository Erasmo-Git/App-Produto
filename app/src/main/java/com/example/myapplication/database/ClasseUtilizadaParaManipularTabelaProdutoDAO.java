package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.myapplication.database.entity.MapearColunasProdutoEntity;
import com.example.myapplication.modelo.Produto;

import java.util.ArrayList;
import java.util.List;

public class ClasseUtilizadaParaManipularTabelaProdutoDAO { // classe de insserçao de dados

    private final String SQL_LISTAR_TODOS = "select * FROM " + MapearColunasProdutoEntity.TABLE_NAME; // pega todas as linhas e colunas daquelq tabela
    private DbGateway dbGateway; // ponto de acesso ao banco de dados

    public ClasseUtilizadaParaManipularTabelaProdutoDAO(Context context){ //metodo construtor
        dbGateway = DbGateway.getInstance(context); //abrindo conexao
    }

    public boolean Salvar(Produto produto) { // metodo para salvar produto
        ContentValues contentValues = new ContentValues();// para utilizar o insert tem que utilizar objeto da API do android "ContentValues", enviador por intermedio deste objeto

        contentValues.put(MapearColunasProdutoEntity.COLLUMN_NAME_NOME,
                produto.getNome()); // pega nome que vem do objeto produto e atribui a coluna nome (put == add)
        contentValues.put(MapearColunasProdutoEntity.COLLUMN_NAME_VALOR,
                produto.getValor()); // pega valor que vem do objeto produto e atribui a coluna valor (put == add)
        if (produto.getId() > 0 ){
            return DbGateway.getDatabase().update(MapearColunasProdutoEntity.TABLE_NAME,
                    contentValues,
                    MapearColunasProdutoEntity._ID + "=?",
                    new String[]{String.valueOf(produto.getId())}) > 0;
        }
        return DbGateway.getDatabase().insert(MapearColunasProdutoEntity.TABLE_NAME,
                null, contentValues) > 0;//inserindo dados
    }

    public List<Produto> listar(){ // metodo que lista os dados
        List<Produto> produtos = new ArrayList<>();// criado array list, List é uma interface e o arrayList implementa esta
        Cursor cursor = DbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);// faz consulta e obtem os dados
        while (cursor.moveToNext()) {// inserindo os dados na lista de produtos, moveToNext: sempre pega a primeira linha

            int id = cursor.getInt(cursor.getColumnIndex(MapearColunasProdutoEntity._ID));// obtendo os dados para construir o produto, pega o indice para retornar o inteiro refferente a ela
            String nome  = cursor.getString(cursor.getColumnIndex(MapearColunasProdutoEntity.COLLUMN_NAME_NOME)); // obtendo os dados para construir o produto, pega o indice para retornar a string referente a ela
            float valor = cursor.getFloat(cursor.getColumnIndex(MapearColunasProdutoEntity.COLLUMN_NAME_VALOR)); // obtendo os dados para construir o produto, pega o indice para retornar o float referente a ela

            produtos.add(new Produto(id, nome, valor));// inserindo dados obtidos na lista
        }
        cursor.close(); // fecha rawQuery

        return produtos;
    }
}
