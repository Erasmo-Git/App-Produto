package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.database.ClasseUtilizadaParaManipularTabelaProdutoDAO;
import com.example.myapplication.modelo.Produto;

public class MainActivity extends AppCompatActivity {

    private ListView listViewProdutos;
    private ArrayAdapter<Produto> adapaterProdutos;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("produtos");
        listViewProdutos = findViewById(R.id.listView_produtos);
        definirOnClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ClasseUtilizadaParaManipularTabelaProdutoDAO produtoDAO = new ClasseUtilizadaParaManipularTabelaProdutoDAO(getBaseContext());
        adapaterProdutos = new ArrayAdapter<Produto>(MainActivity.this, android.R.layout.simple_list_item_1, produtoDAO.listar());
        listViewProdutos.setAdapter(adapaterProdutos);
    }

    private void definirOnClickListenerListView (){
        listViewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produtoClicado = adapaterProdutos.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroProdutoActivity.class);
                intent.putExtra("produtoEdicao", produtoClicado);
                startActivity(intent);
            }
        });
    }

    public void onClickNovoProduto (View v){
        Intent intent = new Intent(MainActivity.this, CadastroProdutoActivity.class);
        startActivity(intent);
    }
}