package com.example.recyclerviewnewintent;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {
    RecyclerView lista;
    ArrayList<Estudiante> ListaEstudiante;
    EstudianteAdapter estudianteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista);

        // Configura las insets de ventana
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtiene la lista de estudiantes desde el intent
        ListaEstudiante = (ArrayList<Estudiante>) getIntent().getSerializableExtra("Lista");

        // Inicializa el adaptador con la lista de estudiantes
        estudianteAdapter = new EstudianteAdapter(ListaEstudiante);

        // Configura el RecyclerView
        lista = findViewById(R.id.rvLista);
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setAdapter(estudianteAdapter);

        // Si es necesario notificar una inserción en la lista, esto debería hacerse después de inicializar el adaptador
        if (!ListaEstudiante.isEmpty()) {
            estudianteAdapter.notifyItemInserted(ListaEstudiante.size() - 1);
        }
    }
}