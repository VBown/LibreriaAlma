package com.example.libreriaalma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LibroAdapter extends RecyclerView.Adapter<LibroAdapter.LibroViewHolder> {

    private List<Libro> libros;
    private Context context;

    public interface OnLibroClickListener {
        void onLibroClick(Libro libro);
    }

    private OnLibroClickListener listener;

    public LibroAdapter(List<Libro> libros, Context context, OnLibroClickListener listener) {
        this.libros = libros;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LibroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_libro, parent, false);
        return new LibroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibroViewHolder holder, int position) {
        Libro libro = libros.get(position);
        holder.tvTitulo.setText(libro.getTitulo());
        holder.tvDescripcion.setText(libro.getDescripcion());
        holder.imgPortada.setImageResource(libro.getImagenResId());

        holder.btnVerMas.setOnClickListener(v -> {
            if (listener != null) {
                listener.onLibroClick(libro);
            }
        });
    }

    @Override
    public int getItemCount() {
        return libros.size();
    }

    public static class LibroViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPortada;
        TextView tvTitulo, tvDescripcion;
        Button btnVerMas;

        public LibroViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPortada = itemView.findViewById(R.id.imgPortada);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            btnVerMas = itemView.findViewById(R.id.btnVerMas);
        }
    }
}