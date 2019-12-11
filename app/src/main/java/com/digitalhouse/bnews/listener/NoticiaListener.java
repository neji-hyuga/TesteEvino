package com.digitalhouse.bnews.listener;

import com.digitalhouse.bnews.model.Noticia;

public interface NoticiaListener {

    public void onNoticiaClick(Noticia noticia);
    public void compartilharNoticia(Noticia noticia);

}
