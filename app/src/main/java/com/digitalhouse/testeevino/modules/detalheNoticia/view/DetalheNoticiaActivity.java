package com.digitalhouse.testeevino.modules.detalheNoticia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.digitalhouse.testeevino.R;
import com.digitalhouse.testeevino.model.Noticia;

public class DetalheNoticiaActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_noticia);

        webView = findViewById(R.id.detalhe_noticia_web_view_id);
        progressBar = findViewById(R.id.progress_bar_detalhe_noticias);
        
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Noticia noticiaDetalhe = (Noticia) bundle.getSerializable("NOTICIA");

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(noticiaDetalhe.getUrl());
    }
}
