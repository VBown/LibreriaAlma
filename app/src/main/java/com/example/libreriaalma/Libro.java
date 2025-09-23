package com.example.libreriaalma;

public class Libro {
    private String titulo;
    private String descripcion;
    private int imagenResId;
    private String autor;
    private int anio;
    private double precio;



    public Libro(String titulo, String descripcion, int imagenResId, String autor, int anio, double precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagenResId = imagenResId;
        this.autor = autor;
        this.anio = anio;
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImagenResId() {
        return imagenResId;
    }
    public String getAutor() {
        return autor;
    }
    public int getAnio() {
        return anio;
    }
    public double getPrecio() {
        return precio;
    }
}