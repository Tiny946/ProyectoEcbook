package com.ecbook.proyectoecbook.proyectoecbook.Explorar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import com.ecbook.proyectoecbook.R;
import com.ecbook.proyectoecbook.proyectoecbook.InfoBooks;

import java.util.List;

/**
 * Created by edu__ on 28/11/2017.
 */

public class AdapterExplorar extends RecyclerView.Adapter<AdapterExplorar.LibrosviewHolder>{
    List<InfoBooks> libros;
    private RecyclerViewOnClickListener mRecyclerViewOnClickListener;

    public AdapterExplorar(List<InfoBooks> libros) {
        this.libros = libros;
    }


    @Override
    public LibrosviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_explorar, parent, false);
        LibrosviewHolder holder = new LibrosviewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(LibrosviewHolder holder, int position) {
        InfoBooks books = libros.get(position);
        holder.tvNombreLibro.setText("Titulo: "+books.getNombreLibro());
        holder.tvAutor.setText("Autor: "+books.getAutorLibro());
        holder.tvGenero.setText("Genero: "+books.getGenero());
        holder.tvPrecio.setText("Precio: "+String.valueOf(books.getPrecio())+" €");
        holder.tvHoraQuedada.setText("Hora: "+books.getHoraQuedada());
        holder.tvLugarQuedada.setText("Lugar: "+books.getLugarQuedada());
        holder.tvEnvio.setText("Envio: "+books.getEnvio());
        holder.tvInterVenta.setText(books.getIntercVenta());
    }

    @Override
    public int getItemCount() {
        return libros.size();
    }

    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener r){
        mRecyclerViewOnClickListener = r;
    }

    public class LibrosviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvNombreLibro, tvAutor, tvGenero, tvPrecio, tvEnvio, tvLugarQuedada, tvHoraQuedada, tvInterVenta;

        public LibrosviewHolder(View itemView) {
            super(itemView);
            tvNombreLibro = (TextView)itemView.findViewById(R.id.tvNombreLibro);
            tvAutor = (TextView)itemView.findViewById(R.id.tvAutor);
            tvPrecio = (TextView)itemView.findViewById(R.id.tvPrecio);
            tvEnvio = (TextView)itemView.findViewById(R.id.tvEnvio);
            tvGenero = (TextView)itemView.findViewById(R.id.tvGenero);
            tvLugarQuedada = (TextView)itemView.findViewById(R.id.tvLugarQuedada);
            tvHoraQuedada = (TextView)itemView.findViewById(R.id.tvHoraQuedada);
            tvInterVenta = (TextView)itemView.findViewById(R.id.tvInterVenta);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mRecyclerViewOnClickListener != null){
                mRecyclerViewOnClickListener.onClickListener(view, getPosition());
            }
        }
    }
}
