package ar.edu.unahur.obj2.observer.observadores;

import ar.edu.unahur.obj2.observer.Oferta;
import ar.edu.unahur.obj2.observer.observables.ProductoSubastado;

public interface Observer {
    public void actualizar(Oferta oferta);
    public void publicarOferta() throws Exception;
    public void establecerSubasta(ProductoSubastado subasta);
}
