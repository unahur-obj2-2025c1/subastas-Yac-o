package ar.edu.unahur.obj2.observer.observables;

import ar.edu.unahur.obj2.observer.Oferta;
import ar.edu.unahur.obj2.observer.excepciones.OfertaSubastadorException;
import ar.edu.unahur.obj2.observer.observadores.Observer;
import java.util.ArrayList;

public class ProductoSubastado implements Observable{
    private ArrayList<Oferta> ofertasRecibidas;
    private ArrayList<Observer> observadores;


    public ProductoSubastado(){
        this.ofertasRecibidas = new ArrayList<Oferta>();
        this.observadores = new ArrayList<Observer>();
    }

    @Override
    public void adscribir(Observer observador) {
        observadores.add(observador);
        observador.establecerSubasta(this);
    }

    @Override
    public void quitar(Observer observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificar() {
        observadores.forEach(o -> o.actualizar(this.ultimOferta()));
    }

    public Oferta ultimOferta(){
        return ofertasRecibidas.getLast();
    }

    public void agregarOferta(Oferta oferta){
        if (!observadores.contains(oferta.getSubastador())) {
            throw new OfertaSubastadorException("El subastador no participa en la subasta.");
        }

        ofertasRecibidas.add(oferta);
        this.notificar();
    }

    public void reiniciar(){
        ofertasRecibidas.stream().forEach(o -> ofertasRecibidas.remove(o));
        observadores.forEach(o -> observadores.remove(o));
        //  Con Stream o no ? ? ?
    }

    public ArrayList<Oferta> getOfertasRecibibas(){
        return ofertasRecibidas;
    }
}
