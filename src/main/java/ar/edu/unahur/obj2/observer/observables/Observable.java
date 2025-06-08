package ar.edu.unahur.obj2.observer.observables;

import ar.edu.unahur.obj2.observer.observadores.Observer;

public interface Observable {    
    public void adscribir(Observer observador);
    public void quitar(Observer observador);
    public void notificar();
    
}
