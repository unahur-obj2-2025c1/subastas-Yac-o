package ar.edu.unahur.obj2.observer.observadores;

import ar.edu.unahur.obj2.observer.Oferta;
import ar.edu.unahur.obj2.observer.observables.ProductoSubastado;

public class Subastador implements Observer {
    private String nombre;
    private Oferta ultimaOferta = null;
    private ProductoSubastado subasta = null;
    
    public Subastador(String nombre){
        this.nombre = nombre;
    }

    @Override
    public void actualizar(Oferta oferta) {
        ultimaOferta = oferta;
    }

    @Override
    public void publicarOferta() {
        Integer valor = 10;
        if (ultimaOferta != null){
            valor = ultimaOferta.getValor() + 10;
        }
        subasta.agregarOferta(new Oferta(this, valor));
    }

    public void reiniciarUltimaOferta(){
        ultimaOferta = null;
    }

    public Oferta getUltimaOferta() {
        return ultimaOferta;
    }

    @Override
    public void establecerSubasta(ProductoSubastado subasta){
        this.subasta = subasta;
    }

}
