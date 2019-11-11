package com.digitalhouse.testeevino.repository;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.digitalhouse.testeevino.R;
import com.digitalhouse.testeevino.listener.NoticiaListener;
import com.digitalhouse.testeevino.model.Noticia;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.ViewHolder> {

    public List<Noticia> noticiaList = new ArrayList<>();
    private NoticiaListener noticiaListener;

    public NoticiaAdapter(List<Noticia> noticiaList, NoticiaListener noticiaListener) {
        this.noticiaListener = noticiaListener;
        this.noticiaList = noticiaList;
    }

    public void atualizarNoticia(List<Noticia> noticiaList){
        this.noticiaList = noticiaList;
        notifyDataSetChanged();
    }

    public void adicionarNoticia(List<Noticia> noticiaList){
        this.noticiaList.addAll(noticiaList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoticiaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noticia_celula, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Noticia noticia = noticiaList.get(position);
        holder.bind(noticia);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noticiaListener.onNoticiaClick(noticia);
            }
        });

    }

    @Override
    public int getItemCount() {
        return noticiaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tituloTextView;
        private TextView textoTextView;
        private ImageView imagemImageView;
        private ImageView compartilharBotao;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tituloTextView = itemView.findViewById(R.id.noticia_titulo_text_view);
            textoTextView = itemView.findViewById(R.id.noticia_texto_text_view);
            imagemImageView = itemView.findViewById(R.id.noticia_imagem_image_view);
            compartilharBotao = itemView.findViewById(R.id.compartilhar_noticia_btn);
        }

        public void bind(Noticia noticia){
            tituloTextView.setText(noticia.getTitle());
            textoTextView.setText(noticia.getDescription());
            Picasso.get().load(noticia.getUrlToImage()).into(imagemImageView);

            compartilharBotao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    noticiaListener.compartilharNoticia(noticia);
                }
            });
        }
    }
}
