package com.example.libreriaalma;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CartViewModel extends ViewModel {
    private final MutableLiveData<List<CartItem>> itemsLive =
            new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<CartItem>> getItems() {
        return itemsLive;
    }

    public void addItem(Libro libro) {
        List<CartItem> list = new ArrayList<>(itemsLive.getValue());
        for (CartItem ci : list) {
            if (ci.getLibro().equals(libro)) {
                ci.increment();
                itemsLive.setValue(list);
                return;
            }
        }
        list.add(new CartItem(libro));
        itemsLive.setValue(list);
    }

    public void increaseItem(CartItem item) {
        List<CartItem> list = new ArrayList<>(itemsLive.getValue());
        for (CartItem ci : list) {
            if (ci.getLibro().equals(item.getLibro())) {
                ci.increment();
                break;
            }
        }
        itemsLive.setValue(list);
    }

    public void decreaseItem(CartItem item) {
        List<CartItem> list = new ArrayList<>(itemsLive.getValue());
        Iterator<CartItem> it = list.iterator();
        while (it.hasNext()) {
            CartItem ci = it.next();
            if (ci.getLibro().equals(item.getLibro())) {
                ci.decrement();
                if (ci.getCantidad() == 0) it.remove();
                break;
            }
        }
        itemsLive.setValue(list);
    }

    /** Remueve completamente este ítem del carrito */
    public void removeItem(CartItem item) {
        List<CartItem> list = new ArrayList<>(itemsLive.getValue());
        list.removeIf(ci -> ci.getLibro().equals(item.getLibro()));
        itemsLive.setValue(list);
    }

    public void clear() {
        itemsLive.setValue(new ArrayList<>());
    }
}