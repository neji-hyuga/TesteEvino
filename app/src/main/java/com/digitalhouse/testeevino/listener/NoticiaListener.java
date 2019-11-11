package com.digitalhouse.testeevino.listener;

import com.digitalhouse.testeevino.model.Noticia;

public interface NoticiaListener {

    public void onNoticiaClick(Noticia noticia);
    public void compartilharNoticia(Noticia noticia);

}
