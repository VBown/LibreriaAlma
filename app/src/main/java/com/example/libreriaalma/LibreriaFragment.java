package com.example.libreriaalma;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class LibreriaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_libreria, container, false);


        // 1) RecyclerView
        RecyclerView rvLibros = view.findViewById(R.id.rvLibros);

        // 2) Lista de Libros
        List<Libro> listaLibros = Arrays.asList(
                new Libro("50 Técnicas de Mindfulness",
                        "Mindfulness como terapia para la ansiedad, depresión, estrés y dolor",
                        R.drawable.libro1,
                        "Donald Altman", 2019, 17990.0),
                new Libro("La Neurociencia del Mindfulness",
                        "Explora los beneficios de un enfoque consciente de la vida",
                        R.drawable.libro2,
                        "Stan Rodski", 2021, 24990.0),
                new Libro("Juegos Mindfulness",
                "Fomenta en los niños la capacidad de concentración y regulación de las emociones. Se incluyen 60 juegos de fácil aplicación",
                R.drawable.libro3,
                "Susan Kaiser", 2017, 21990.0),
                new Libro("Mindfulness para Principiantes",
                "Respuestas apropiadas para lograr conectar de un modo más claro, duradero y amoroso con nosotros y el mundo",
                R.drawable.libro4,
                "Jon Kabat-Zinn", 2023, 18990.0),
                new Libro("Mente Calma",
                "Llegar a una mente serena Pensar menos, pensar mejor",
                R.drawable.libro5,
                "Vanessa Benjumea", 2025, 18990.0),
                new Libro("Memorias del Alma",
                "Descubre el sentido de la vida y tu misión en ella",
                R.drawable.libro6,
                "Jazmin González", 2020, 28990.0),
                new Libro("El poder del ahora",
                "Uno de los libros más vendidos de la historia. Tiene la capacidad una nueva experiencia de vida",
                R.drawable.libro7,
                "Eckart Tolle", 2022, 7990.0),
                new Libro("El libro divino de los Chakras",
                "Los chakras como fuente de bienestar",
                R.drawable.libro8,
                "Jay Tatsay", 2018, 13990.0),
                new Libro("El despertar del tercer ojo",
                "Accede al conocimiento, la iluminació y la intuición ",
                R.drawable.libro9,
                "Susan Shumsky", 2020, 16990.0),
                new Libro("Muladhara",
                "Todos somos luz de sanación y llevamos un terapeuta interno que está esperando ser descubierto",
                R.drawable.libro10,
                "José María Sánchéz Navarro", 2025, 9990.0)
        );

        // 3) Instanciar el Adapter PASÁNDOLE EL LISTENER
        LibroAdapter adapter = new LibroAdapter(
                listaLibros,
                requireContext(), // Fragment context
                libro -> {
                    // a) Verificación que el clic llega
                    Toast.makeText(
                            requireContext(),
                            "Estas en: " + libro.getTitulo(),
                            Toast.LENGTH_SHORT
                    ).show();


                    // b) Lanza DetalleFragment
                    Bundle bundle = new Bundle();
                    bundle.putString("titulo",      libro.getTitulo());
                    bundle.putString("descripcion", libro.getDescripcion());
                    bundle.putInt(   "imagenResId", libro.getImagenResId());
                    bundle.putString("autor",       libro.getAutor());
                    bundle.putInt(   "anio",        libro.getAnio());
                    bundle.putFloat( "precio",      (float) libro.getPrecio());
                    NavHostFragment.findNavController(this)
                            .navigate(R.id.action_libreria_to_detalle, bundle);

                }
        );

        // 4) Configura RecyclerView con LayoutManager y Adapter
        rvLibros.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvLibros.setAdapter(adapter);

        return view;
    }
}

