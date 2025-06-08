package ar.edu.unahur.obj2.observer.observables;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.observer.excepciones.OfertaSubastadorException;
import ar.edu.unahur.obj2.observer.observadores.Subastador;

public class ProductoSubastadoTest {
    final Subastador martomau = new Subastador("martomau");
    final Subastador gonzager = new Subastador("gonzager");
    final Subastador diazdan = new Subastador("diazdan");

    final ProductoSubastado producto1 = new ProductoSubastado();

    @Test
    void escenario1(){
        producto1.reiniciar();
        martomau.reiniciarUltimaOferta();
        gonzager.reiniciarUltimaOferta();
        diazdan.reiniciarUltimaOferta();

        producto1.adscribir(martomau);
        producto1.adscribir(gonzager);

        martomau.publicarOferta();
        gonzager.publicarOferta();
        martomau.publicarOferta();
    }

    @Test
    void ambosSubastadoresRecibenLaUltimaOfertaRealizada(){
        escenario1();

        assertEquals(30, gonzager.getUltimaOferta().getValor());
        assertEquals(30, martomau.getUltimaOferta().getValor());

    }

    @Test
    void ultimaOfertaPerteneceAMartomau(){
        escenario1();

        assertEquals(martomau, producto1.ultimOferta().getSubastador());
    }

    @Test
    void valorUltimaOfertaPorgresivamente(){
        producto1.reiniciar();
        martomau.reiniciarUltimaOferta();
        gonzager.reiniciarUltimaOferta();
        diazdan.reiniciarUltimaOferta();

        producto1.adscribir(martomau);
        producto1.adscribir(gonzager);

        martomau.publicarOferta();
        assertEquals(10, producto1.ultimOferta().getValor());
        gonzager.publicarOferta();
        assertEquals(20, producto1.ultimOferta().getValor());
        martomau.publicarOferta();
        assertEquals(30, producto1.ultimOferta().getValor());
    }

    @Test
    void cantOfertas3(){
        escenario1();

        assertEquals(3, producto1.getOfertasRecibibas().size());
    }

    @Test
    void exceptionAlRecibirOfertadeNoParticipante(){
        escenario1();
        final OfertaSubastadorException e = new OfertaSubastadorException("El subastador no participa en la subasta.");
        
        assertThrows(OfertaSubastadorException e, martomau.publicarOferta());
    }
}
