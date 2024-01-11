package so.ken;

import org.junit.Test;


import java.util.LinkedList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class TestRisoluzione {
    @Test
    public void testRisoluzioneKenKen() {
        KenKen kenKen = new KenKen();
        kenKen.setOrdine(3); // Imposta un ordine per il KenKen

        LinkedList<Blocco> blocchii=new LinkedList<>();
        Casella c1i=new Casella(0,0);
        Casella c2i=new Casella(0,1);
        Casella c3i=new Casella(0,2);
        Casella c4i=new Casella(1,0);
        Casella c5i=new Casella(1,1);
        Casella c6i=new Casella(1,2);
        Casella c7i=new Casella(2,0);
        Casella c8i=new Casella(2,1);
        Casella c9i=new Casella(2,2);
        LinkedList<Casella> cc1i= new LinkedList<>();
        cc1i.add(c1i);
        LinkedList<Casella> cc2i= new LinkedList<>();
        cc2i.add(c2i);
        cc2i.add(c3i);
        LinkedList<Casella> cc3i= new LinkedList<>();
        cc3i.add(c4i);
        cc3i.add(c5i);
        cc3i.add(c7i);
        LinkedList<Casella> cc4i= new LinkedList<>();
        cc4i.add(c8i);
        LinkedList<Casella> cc5i= new LinkedList<>();
        cc5i.add(c6i);
        cc5i.add(c9i);
        Blocco b1i= new Blocco(cc1i,3,' ');
        Blocco b2i= new Blocco(cc2i,2,'/');
        Blocco b3i= new Blocco(cc3i,4,'*');
        Blocco b4i= new Blocco(cc4i,3,' ');
        Blocco b5i= new Blocco(cc5i,2,'-');
        blocchii.add(b1i);
        blocchii.add(b2i);
        blocchii.add(b3i);
        blocchii.add(b4i);
        blocchii.add(b5i);

        kenKen.setBlocchischema(blocchii);

        Soluzione solver = kenKen.soluzione;
        kenKen.inserisci(0,0,1);
        solver.risolvi();

        assertTrue(solver.esisteSoluzione(c1i));
        assertFalse(kenKen.correggi(0, 0));


    }

    @Test
    public void testRisoluzioneErroreKenKen() {
        KenKen kenKen = new KenKen();
        kenKen.setOrdine(3); // Imposta un ordine per il KenKen

        LinkedList<Blocco> blocchii=new LinkedList<>();
        Casella c1i=new Casella(0,0);
        Casella c2i=new Casella(0,1);
        Casella c3i=new Casella(0,2);
        Casella c4i=new Casella(1,0);
        Casella c5i=new Casella(1,1);
        Casella c6i=new Casella(1,2);
        Casella c7i=new Casella(2,0);
        Casella c8i=new Casella(2,1);
        Casella c9i=new Casella(2,2);
        LinkedList<Casella> cc1i= new LinkedList<>();
        cc1i.add(c1i);
        LinkedList<Casella> cc2i= new LinkedList<>();
        cc2i.add(c2i);
        cc2i.add(c3i);
        cc2i.add(c6i);
        LinkedList<Casella> cc3i= new LinkedList<>();
        cc3i.add(c4i);
        cc3i.add(c5i);
        LinkedList<Casella> cc4i= new LinkedList<>();
        cc4i.add(c7i);
        LinkedList<Casella> cc5i= new LinkedList<>();
        cc5i.add(c8i);
        cc5i.add(c9i);
        Blocco b1i= new Blocco(cc1i,1,' ');
        Blocco b2i= new Blocco(cc2i,8,'+');
        Blocco b3i= new Blocco(cc3i,1,'-');
        Blocco b4i= new Blocco(cc4i,2,' ');
        Blocco b5i= new Blocco(cc5i,3,'*');
        blocchii.add(b1i);
        blocchii.add(b2i);
        blocchii.add(b3i);
        blocchii.add(b4i);
        blocchii.add(b5i);

        kenKen.setBlocchischema(blocchii);

        Soluzione solver = kenKen.soluzione;
        solver.risolvi();

        assertFalse(solver.esisteSoluzione(c1i));

    }

}
