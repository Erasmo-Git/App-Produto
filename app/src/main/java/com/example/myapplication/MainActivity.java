package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.modelo.Produto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE_NOVO_PRODUTO = 1;
    private final int RESULT_CODE_NOVO_PRODUTO = 10;
    private final int REQUEST_CODE_EDITAR_PRODUTO = 2;
    private final int RESULT_CODE_PRODUTO_EDITADO = 11;

    private ListView listViewProdutos;
    private ArrayAdapter<Produto> adapaterProdutos;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("produtos");

        listViewProdutos = findViewById(R.id.listView_produtos);
        ArrayList<Produto> Produtos = new ArrayList<Produto>();
//        ArrayList<Produto> Produtos = this.criarListaProdutos();

        adapaterProdutos = new ArrayAdapter<Produto>(MainActivity.this, android.R.layout.simple_list_item_1, Produtos);
        listViewProdutos.setAdapter(adapaterProdutos);
        definirOnClickListenerListView();


    }

    private void definirOnClickListenerListView (){
        listViewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produtoClicado = adapaterProdutos.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroProdutoActivity.class);
                intent.putExtra("produtoEdicao", produtoClicado);
                startActivityForResult(intent, REQUEST_CODE_EDITAR_PRODUTO);
            }
        });
    }

//    private ArrayList<Produto> criarListaProdutos(){
//        ArrayList<Produto> produtos = new ArrayList<Produto>();
//        produtos.add(new Produto("notebook", 3500f));
//        produtos.add(new Produto("mouse", 40f));
//        produtos.add(new Produto("roteador", 190.90f));
//        return produtos;
//    }
    public void onClickNovoProduto (View v){
        Intent intent = new Intent(MainActivity.this, CadastroProdutoActivity.class);
        startActivityForResult(intent, REQUEST_CODE_NOVO_PRODUTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_NOVO_PRODUTO && resultCode == RESULT_CODE_NOVO_PRODUTO){
            Produto produto = (Produto) data.getExtras().getSerializable("novoProduto");
            produto.setId(++id);
            this.adapaterProdutos.add(produto);
        }else if (requestCode == REQUEST_CODE_EDITAR_PRODUTO && resultCode == RESULT_CODE_PRODUTO_EDITADO){
            Produto produtoEditado = (Produto) data.getExtras().getSerializable("produtoEditado");
            for (int i = 0; i < adapaterProdutos.getCount(); i++){
                Produto produto = adapaterProdutos.getItem(i);
                if (produto.getId() == produtoEditado.getId()){
                    adapaterProdutos.remove(produto);
                    adapaterProdutos.insert(produtoEditado, i);
                    break;
                }
            }
            Toast.makeText(MainActivity.this, "Produto Editado", Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}