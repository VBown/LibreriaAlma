package com.example.libreriaalma;

public class CartItem {
    private final Libro libro;
    private int cantidad;

    public CartItem(Libro libro) {
        this.libro = libro;
        this.cantidad = 1;
    }

    public Libro getLibro() { return libro; }
    public int getCantidad() { return cantidad; }
    public void increment() { cantidad++; }
    public void decrement() { if (cantidad > 0) cantidad--; }
}