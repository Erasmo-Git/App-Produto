package com.example.myapplication;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Produto implements Serializable {

    private int id;
    private String nome;
    private float valor;

    public Produto(int id, String nome, float valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @NonNull
    @Override
    public String toString() { return nome + "                                            " + valor; }
}
