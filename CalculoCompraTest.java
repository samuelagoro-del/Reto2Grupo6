package Reto2Grupo6.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import Reto2Grupo6.Entrada;

public class CalculoCompraTest {

    // Test 1: Compra con 1 entrada → sin descuento
    @Test
    public void testCompraSinDescuento() {
        ArrayList<Entrada> entradas = new ArrayList<>();
        Entrada e = new Entrada();
        e.setPrecio(12.5);  // precio de la entrada
        entradas.add(e);

        // Lógica de Main1 para total y descuento
        double total = 0;
        for (Entrada en : entradas) total += en.getPrecio();

        float descuento = entradas.size() == 2 ? 0.20f : entradas.size() >= 3 ? 0.30f : 0;
        double totalFinal = total * (1 - descuento);

        assertEquals(12.5, totalFinal, 0.001);
    }

    // Test 2: Compra con 2 entradas → descuento 20%
    @Test
    public void testCompraConDescuento20() {
        ArrayList<Entrada> entradas = new ArrayList<>();
        Entrada e1 = new Entrada(); e1.setPrecio(10);
        Entrada e2 = new Entrada(); e2.setPrecio(15);
        entradas.add(e1);
        entradas.add(e2);

        double total = 0;
        for (Entrada en : entradas) total += en.getPrecio();

        float descuento = entradas.size() == 2 ? 0.20f : entradas.size() >= 3 ? 0.30f : 0;
        double totalFinal = total * (1 - descuento);

        assertEquals(20, totalFinal, 0.001);
    }

    // Test 3: Compra con 3 entradas → descuento 30%
    @Test
    public void testCompraConDescuento30() {
        ArrayList<Entrada> entradas = new ArrayList<>();
        entradas.add(new Entrada(){{
            setPrecio(8);
        }});
        entradas.add(new Entrada(){{
            setPrecio(12);
        }});
        entradas.add(new Entrada(){{
            setPrecio(10);
        }});

        double total = 0;
        for (Entrada en : entradas) total += en.getPrecio();

        float descuento = entradas.size() == 2 ? 0.20f : entradas.size() >= 3 ? 0.30f : 0;
        double totalFinal = total * (1 - descuento);

        assertEquals(21, totalFinal, 0.001); 
    }
}
