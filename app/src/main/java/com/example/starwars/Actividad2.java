package com.example.starwars;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Actividad2 extends AppCompatActivity {

    private RecyclerView recyclerPeliculas;
    private AdaptadorPeliculas adaptadorPeliculas;
    private List<Pelicula> listaPeliculas = new ArrayList<>();

    private static  String UrlPeliculas = "https://swapi.dev/api/films/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2); // Cambiado para asociar el layout correcto

        recyclerPeliculas = findViewById(R.id.recyclerPeliculas);
        recyclerPeliculas.setLayoutManager(new LinearLayoutManager(this));
        adaptadorPeliculas = new AdaptadorPeliculas(listaPeliculas, new AdaptadorPeliculas.OnPeliculaClickListener() {
            @Override
            public void onPeliculaClick(Pelicula pelicula) {
                // Al hacer clic, se abre la Activity de detalles
                Intent intent = new Intent(Actividad2.this, DetallePeliculaActivity.class);
                intent.putExtra("titulo", pelicula.getTitulo());
                intent.putExtra("apertura", pelicula.getApertura());
                startActivity(intent);
            }
        });
        recyclerPeliculas.setAdapter(adaptadorPeliculas);

        cargarPeliculas();
    }

    private void cargarPeliculas() {
        RequestQueue cola = Volley.newRequestQueue(this);

        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.GET, UrlPeliculas, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject respuesta) {
                        try {
                            JSONArray resultados = respuesta.getJSONArray("results");
                            for (int i = 0; i < resultados.length(); i++) {
                                JSONObject peliculaJSON = resultados.getJSONObject(i);
                                Pelicula pelicula = new Pelicula(
                                        peliculaJSON.getString("title"),
                                        peliculaJSON.getString("release_date"),
                                        peliculaJSON.getString("director"),
                                        peliculaJSON.getString("opening_crawl")
                                );

                                listaPeliculas.add(pelicula);
                            }
                            adaptadorPeliculas.notifyDataSetChanged();
                        } catch (Exception e) {
                            Log.e("SWAPI_ERROR", "Error al parsear la respuesta: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                     Log.e("SWAPI_ERROR", "Error en volley: " + error);

                    }
                }

        );

        cola.add(solicitud);
    }
}
