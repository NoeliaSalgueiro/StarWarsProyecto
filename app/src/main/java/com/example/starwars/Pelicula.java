package com.example.starwars;

public class Pelicula {
    private String titulo;
    private String fechaEstreno;
    private String director;
    private String apertura; // opening crawl

    public Pelicula (String titulo, String fechaEstreno, String director, String apertura) {
        this.titulo = titulo;
        this.fechaEstreno = fechaEstreno;
        this.director = director;
        this.apertura = apertura;
    }


    public String getTitulo() {
        return titulo;
    }

    public String getFechaEstreno() {
        return fechaEstreno;
    }

    public String getDirector() {
        return director;
    }

    public String getApertura() {
        return apertura;
    }
}
