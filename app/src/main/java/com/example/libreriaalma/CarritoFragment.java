package com.example.libreriaalma;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CarritoFragment extends Fragment {

    private CartViewModel vm;
    private CarritoAdapter adapter;
    private TextView tvResumen;
    private Button btnConfirmar;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_carrito, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(requireActivity())
                .get(CartViewModel.class);

        RecyclerView rv = view.findViewById(R.id.rvCarrito);
        tvResumen    = view.findViewById(R.id.tvResumenCompra);
        btnConfirmar = view.findViewById(R.id.btnConfirmar);

        adapter = new CarritoAdapter(
                new ArrayList<>(),
                new CarritoAdapter.OnCartAction() {
                    @Override
                    public void onIncrease(CartItem item) {
                        vm.increaseItem(item);
                    }
                    @Override
                    public void onDecrease(CartItem item) {
                        vm.decreaseItem(item);
                    }
                    @Override
                    public void onRemove(CartItem item) {
                        vm.removeItem(item);
                    }
                }
        );

        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv.setAdapter(adapter);

        vm.getItems().observe(getViewLifecycleOwner(), lista -> {
            adapter.updateItems(lista);
            actualizarResumen(lista);
        });

        btnConfirmar.setOnClickListener(v -> vm.clear());
    }

    private void actualizarResumen(List<CartItem> lista) {
        int totalLibros = 0;
        double totalPrecio = 0.0;
        for (CartItem ci : lista) {
            totalLibros += ci.getCantidad();
            totalPrecio += ci.getLibro().getPrecio() * ci.getCantidad();
        }
        tvResumen.setText(
                String.format("Total: %d libro(s) · $%.2f", totalLibros, totalPrecio)
        );
    }
}