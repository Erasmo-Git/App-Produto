package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CadastroProdutoActivity extends AppCompatActivity {

    private final int RESULT_CODE_NOVO_PRODUTO = 10;
    private final int RESULT_CODE_PRODUTO_EDITADO = 11;
    private final int RESULT_CODE_EXCLUIR_PRODUTO = 8;

    private boolean edicao = false;
    private boolean exclusao = true;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        setTitle("CADASTRO DE PRODUTO");
        carregarProduto();
    }

    private void carregarProduto (){
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("produtoEdicao") != null){
            Produto produto = (Produto) intent.getExtras().get("produtoEdicao");
            EditText editTextNome = findViewById(R.id.et_nome);
            EditText editTextValor = findViewById(R.id.et_valor);
            editTextNome.setText(produto.getNome());
            editTextValor.setText(String.valueOf(produto.getValor()));
            edicao = true;
            id = produto.getId();
        }
    }
    public void onClickVoltar (View v){
        finish();
    }
    public void onClickSalvar (View v){
        EditText editTextNome = findViewById(R.id.et_nome);
        EditText editTextValor = findViewById(R.id.et_valor);

        String nome = editTextNome.getText().toString();
        float valor = Float.parseFloat(editTextValor.getText().toString());

        if(edicao) {
            Produto produto = new Produto(id, nome, valor);
            Intent intent = new Intent();
            intent.putExtra("produtoEditado", produto);
            setResult(RESULT_CODE_PRODUTO_EDITADO, intent);
        }else {
            Produto produto = new Produto(id, nome, valor);
            Intent intent = new Intent();
            intent.putExtra("novoProduto", produto);
            setResult(RESULT_CODE_NOVO_PRODUTO, intent);
        }
        finish();
    }

    public void onClickExcluir(View v) {
        EditText editTextNome = findViewById(R.id.et_nome);
        EditText editTextValor = findViewById(R.id.et_valor);
        String nome = editTextNome.getText().toString();
        float valor = Float.parseFloat(editTextValor.getText().toString());
        Produto produtoExcluir = new Produto(id, nome, valor);
        Intent intent = new Intent();
        intent.putExtra("excluirProduto", produtoExcluir);
        setResult(RESULT_CODE_EXCLUIR_PRODUTO, intent);
        finish();
    }

}