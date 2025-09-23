package com.example.libreriaalma;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

public class DetalleFragment extends Fragment {

    private CartViewModel cartViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflamos el layout del fragmento
        return inflater.inflate(R.layout.fragment_detalle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1) Leer argumentos del NavGraph
        Bundle args = getArguments();
        if (args == null) return;

        String titulo      = args.getString("titulo");
        String descripcion = args.getString("descripcion");
        int    imagenResId = args.getInt("imagenResId");
        String autor       = args.getString("autor");
        int    anio        = args.getInt("anio");
        float  precio      = args.getFloat("precio");

        // 2) Referencias a vistas
        TextView tvTitulo       = view.findViewById(R.id.tvDetalleTitulo);
        TextView tvDescripcion  = view.findViewById(R.id.tvDetalleDescripcion);
        TextView tvAutor        = view.findViewById(R.id.tvAutor);
        TextView tvAnio         = view.findViewById(R.id.tvAnio);
        TextView tvPrecio       = view.findViewById(R.id.tvPrecio);
        ImageView imgPortada    = view.findViewById(R.id.imgDetallePortada);
        Button btnAgregarCarrito= view.findViewById(R.id.btnAgregarCarrito);
        Button btnVerCarrito    = view.findViewById(R.id.btnIrCarrito);

        // 3) Poblar UI
        tvTitulo.setText(titulo);
        tvDescripcion.setText(descripcion);
        tvAutor.setText("Autor: " + autor);
        tvAnio.setText("Año: " + anio);
        tvPrecio.setText("Precio: $" + precio);
        imgPortada.setImageResource(imagenResId);

        // 4) Obtener ViewModel compartido
        cartViewModel = new ViewModelProvider(requireActivity())
                .get(CartViewModel.class);

        // 5) Agregar libro al carrito
        btnAgregarCarrito.setOnClickListener(v ->
                cartViewModel.addItem(
                        new Libro(titulo, descripcion, imagenResId, autor, anio, precio)
                )
        );

        // 6) Navegar al CarritoFragment con limpieza de pila
        btnVerCarrito.setOnClickListener(v -> {
            NavOptions navOptions = new NavOptions.Builder()
                    .setPopUpTo(R.id.libreriaFragment, true)  // true para limpiar Librería y Detalle en pila
                    .setLaunchSingleTop(true)
                    .build();

            NavHostFragment.findNavController(DetalleFragment.this)
                    .navigate(R.id.carritoFragment, null, navOptions);
        });
    }
}


