package com.example.pandithagd.solucion6;

import java.util.ArrayList;

/**
 * Created by PandithaGD on 14/09/2016.
 */
public class CareTaker {
    private ArrayList<Memento> mementos = new ArrayList<Memento>();

    public void addMemento(Memento memento){
        mementos.add(memento);
    }

    public Memento getMemento(int index){
        return  mementos.get(index);
    }

    public ArrayList<Memento> getMementos() {
        return mementos;
    }
}
