package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.database.ClasseUtilizadaParaManipularTabelaProdutoDAO;
import com.example.myapplication.modelo.Produto;

public class CadastroProdutoActivity extends AppCompatActivity {

    private final int RESULT_CODE_EXCLUIR_PRODUTO = 16;
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

        Produto produto = new Produto(id, nome, valor);
        ClasseUtilizadaParaManipularTabelaProdutoDAO produtoDAO = new ClasseUtilizadaParaManipularTabelaProdutoDAO(getBaseContext()); // getBaseContext: da acesso a classe produtoDAO
        boolean salvou = produtoDAO.Salvar(produto);
        if (salvou) {
            finish();
        } else {
            Toast.makeText(CadastroProdutoActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
        }
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