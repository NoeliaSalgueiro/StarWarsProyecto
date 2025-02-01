package com.example.starwars;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;
import android.widget.TextView;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;

public class DetallePeliculaActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);

        String titulo = getIntent().getStringExtra("titulo");
        String apertura = getIntent().getStringExtra("apertura");

        TextView txtTituloDetalle = findViewById(R.id.txtTituloDetalle);
        TextView txtApertura = findViewById(R.id.txtApertura);

        txtTituloDetalle.setText(titulo);


        String aperturaConSaltos = apertura.replace("\n", "<br><br>");


        txtApertura.setText(Html.fromHtml(aperturaConSaltos));

        ScrollView scrollDetalle = findViewById(R.id.scrollDetalle);
        scrollDetalle.post(new Runnable() {
            @Override
            public void run() {
                int altura = scrollDetalle.getHeight();
                TranslateAnimation animacion = new TranslateAnimation(
                        0, 0, altura, -altura
                );
                animacion.setDuration(19000);
                animacion.setFillAfter(true);

                scrollDetalle.getChildAt(0).startAnimation(animacion);
            }
        });


        mediaPlayer = MediaPlayer.create(this, R.raw.bandasonora);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
