package com.example.starwars;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AdaptadorPeliculas extends RecyclerView.Adapter<AdaptadorPeliculas.ViewHolderPelicula> {

    public interface OnPeliculaClickListener {
        void onPeliculaClick(Pelicula pelicula);
    }

    private List<Pelicula> listaPeliculas;
    private OnPeliculaClickListener oyente;

    public AdaptadorPeliculas(List<Pelicula> listaPeliculas, OnPeliculaClickListener oyente) {
        this.listaPeliculas = listaPeliculas;
        this.oyente = oyente;
    }

    @Override
    public ViewHolderPelicula onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula, parent, false);
        return new ViewHolderPelicula(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolderPelicula holder, int posicion) {
        final Pelicula pelicula = listaPeliculas.get(posicion);
        holder.txtTitulo.setText(pelicula.getTitulo());
        holder.txtDirector.setText("Director: "+pelicula.getDirector());
        holder.txtFechaEstreno.setText("Estreno: " + pelicula.getFechaEstreno());
        holder.tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oyente.onPeliculaClick(pelicula);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }

    public static class ViewHolderPelicula extends RecyclerView.ViewHolder {
        CardView tarjeta;
        TextView txtTitulo;
        TextView txtFechaEstreno;
        TextView txtDirector;

        public ViewHolderPelicula(View itemView) {
            super(itemView);
            tarjeta = itemView.findViewById(R.id.tarjeta);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtFechaEstreno = itemView.findViewById(R.id.txtFechaEstreno);
            txtDirector = itemView.findViewById(R.id.txtDirector);
        }
    }
}
