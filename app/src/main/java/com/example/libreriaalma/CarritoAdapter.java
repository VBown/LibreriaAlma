package com.example.libreriaalma;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CarritoAdapter
        extends RecyclerView.Adapter<CarritoAdapter.CartViewHolder> {

    public interface OnCartAction {
        void onIncrease(CartItem item);
        void onDecrease(CartItem item);
        void onRemove(CartItem item);
    }

    final List<CartItem> items;
    private final OnCartAction callback;

    public CarritoAdapter(List<CartItem> items, OnCartAction callback) {
        this.items = items;
        this.callback = callback;
    }

    /** Llama esto desde el Fragment para refrescar la lista entera */
    public void updateItems(List<CartItem> nuevos) {
        items.clear();
        items.addAll(nuevos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_carrito, parent, false);
        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(
            @NonNull CartViewHolder holder, int position) {
        CartItem ci = items.get(position);
        Libro lib = ci.getLibro();

        holder.imgPortada.setImageResource(lib.getImagenResId());
        holder.tvTitulo.setText(lib.getTitulo());
        holder.tvPrecio.setText(String.format("$%.2f", lib.getPrecio()));
        holder.tvCantidad.setText(String.valueOf(ci.getCantidad()));

        holder.btnMas.setOnClickListener(v -> callback.onIncrease(ci));
        holder.btnMenos.setOnClickListener(v -> callback.onDecrease(ci));
        holder.btnEliminar.setOnClickListener(v -> callback.onRemove(ci));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPortada;
        TextView  tvTitulo, tvPrecio, tvCantidad;
        Button    btnMas, btnMenos, btnEliminar;

        CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPortada  = itemView.findViewById(R.id.imgPortada);
            tvTitulo    = itemView.findViewById(R.id.tvTitulo);
            tvPrecio    = itemView.findViewById(R.id.tvPrecio);
            tvCantidad  = itemView.findViewById(R.id.tvCantidad);
            btnMas      = itemView.findViewById(R.id.btnMas);
            btnMenos    = itemView.findViewById(R.id.btnMenos);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}