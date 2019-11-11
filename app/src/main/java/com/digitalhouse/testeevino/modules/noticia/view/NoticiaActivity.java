package com.digitalhouse.testeevino.modules.noticia.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.digitalhouse.testeevino.R;
import com.digitalhouse.testeevino.listener.NoticiaListener;
import com.digitalhouse.testeevino.model.Noticia;
import com.digitalhouse.testeevino.modules.detalheNoticia.view.DetalheNoticiaActivity;
import com.digitalhouse.testeevino.modules.noticia.viewmodel.NoticiaViewModel;
import com.digitalhouse.testeevino.repository.NoticiaAdapter;

import java.util.ArrayList;

public class NoticiaActivity extends AppCompatActivity implements NoticiaListener {

    private ProgressBar progressBar;
    private RecyclerView noticiaRecyclerView;
    private String search = "bitcoin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ArrayList<Noticia> noticiaArrayList = new ArrayList<>();

        noticiaRecyclerView = findViewById(R.id.noticia_recycler_view);
        progressBar = findViewById(R.id.progress_bar_noticia);
        NoticiaAdapter noticiaAdapter = new NoticiaAdapter(noticiaArrayList, this);


        noticiaRecyclerView.setAdapter(noticiaAdapter);
        noticiaRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        NoticiaViewModel noticiaViewModel = ViewModelProviders.of(this).get(NoticiaViewModel.class);

        noticiaViewModel.atualizarNoticias(search);


        noticiaViewModel.getNoticiaLiveData()
                .observe(this, noticiaList -> {
                    noticiaAdapter.adicionarNoticia(noticiaList);
                    progressBar.setVisibility(View.GONE);
                });

    }

    @Override
    public void onNoticiaClick(Noticia noticia) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("NOTICIA", noticia);

        Intent intent = new Intent(this, DetalheNoticiaActivity.class);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void compartilharNoticia(Noticia noticia) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, noticia.getTitle());
        intent.putExtra(Intent.EXTRA_SUBJECT, noticia.getUrl());
        startActivity(Intent.createChooser(intent, "Compartilhar"));

    }
}
