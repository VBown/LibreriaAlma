package com.example.libreriaalma;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.libreriaalma.databinding.ActivityMainBinding;
import androidx.navigation.NavOptions;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.navHostFragment, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupNavigation();
    }

    private void setupNavigation() {
        Fragment host = getSupportFragmentManager()
                .findFragmentById(binding.navHostFragment.getId()); // usa el id del FragmentContainerView del XML

        if (host instanceof NavHostFragment) {
            navController = ((NavHostFragment) host).getNavController();

            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.homeFragment, R.id.libreriaFragment, R.id.carritoFragment, R.id.exit_fragment)
                    .build();

            // Configura la barra inferior con NavController y AppBarConfiguration
            NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

            // Control manual para limpiar pila al seleccionar un ítem (evitar "Detalle" reapareciendo)
            binding.bottomNavigationView.setOnItemSelectedListener(item -> {
                NavOptions navOptions = new NavOptions.Builder()
                        .setLaunchSingleTop(true)
                        .setRestoreState(true)
                        // Limpia pila hasta el destino inicial para evitar pantallas intermedias
                        .setPopUpTo(navController.getGraph().getStartDestinationId(), false)
                        .build();

                navController.navigate(item.getItemId(), null, navOptions);
                return true;
            });

            // Opcional: mantener comportamiento popBackStack al reseleccionar ítem
            binding.bottomNavigationView.setOnItemReselectedListener(item -> {
                navController.popBackStack(item.getItemId(), false);
            });

        } else {
            throw new IllegalStateException("El Fragment con ese id no es NavHostFragment. Revisa el android:id.");
        }
    }
}