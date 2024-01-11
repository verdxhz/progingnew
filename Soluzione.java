package so.ken;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Soluzione extends SoluzioneAbstract<Casella,Integer> implements Serializable {
    private static final long serialVersionUID=1L;
    private KenKen schema; //griglia su cui sviluppare il gioco
    private int ordine; //numero righe e colonne
    protected int[][] soluzione;
    protected LinkedList<Blocco> blocchi=new LinkedList<>();
    private int numSoluzioni=1;

    public Soluzione(KenKen k)
    {
        schema=k;
        ordine= schema.ordine;
        for (int i =0; i<schema.blocchischema.size();i++)
            blocchi.add(schema.blocchischema.get(i));
        soluzione=new int[ordine][ordine];
    }

    @Override
    protected boolean assegnabile(Casella c, Integer nn) {
        for(int jj=0; jj<ordine;jj++)
        {
            if(soluzione[c.i][jj]==nn)// controlla che non vi sia il numero sulla stessa riga
                return false;
            if(soluzione[jj][c.j]==nn)// controlla che non vi sia il numero sulla stessa colonna
                return false;
        }
        return controllaoperazione(c,nn);
    }
    private boolean controllaoperazione(Casella c, int nn)
    {
        assegna(c,nn);
        for(Blocco b: blocchi)
        {
            for (Casella cc: b.caselleBlocco)
            {
                if (cc.equals(c))
                {
                    int i=0;
                    switch (b.operazione) {
                        case '+':
                            for (Casella x: b.caselleBlocco)
                            {
                                if (soluzione[x.i][x.j] != 0)
                                    i += soluzione[x.i][x.j];
                                else
                                    return true;
                            }
                            if(i == b.risultato)
                                return true;
                        case '-':
                            for (Casella x: b.caselleBlocco)
                            {
                                if (soluzione[x.i][x.j] == 0)
                                    return true;
                            }
                            if (Math.abs(soluzione[b.caselleBlocco.get(0).i][b.caselleBlocco.get(0).j] - soluzione[b.caselleBlocco.get(1).i][b.caselleBlocco.get(1).j])==b.risultato)
                                return true;
                        //le caselle meno non sono pi� di due
                        case '*':
                            i = 1;
                            for (Casella x: b.caselleBlocco)
                            {
                                if (soluzione[x.i][x.j] != 0)
                                    i *= soluzione[x.i][x.j];
                                else
                                    return true;
                            }
                            if(i == b.risultato)
                                return true;
                        case '/':
                            for (Casella x: b.caselleBlocco)
                                if (soluzione[x.i][x.j] == 0)
                                    return true;
                            if(soluzione[b.caselleBlocco.get(0).i][b.caselleBlocco.get(0).j] / soluzione[b.caselleBlocco.get(1).i][b.caselleBlocco.get(1).j] == b.risultato || soluzione[b.caselleBlocco.get(1).i][b.caselleBlocco.get(1).j] / soluzione[b.caselleBlocco.get(0).i][b.caselleBlocco.get(0).j] == b.risultato)
                                return true;
                        //perch� le caselle con il diviso sono sempre doppie, non pi� grandi;

                        default:
                            if(soluzione[b.caselleBlocco.get(0).i][b.caselleBlocco.get(0).j]==b.risultato)
                                return true;
                    }
                }
            }
        }
        deassegna(c);
        return false;
    }
    @Override
    protected void assegna(Casella ps, Integer n)
    {
        soluzione[ps.i][ps.j]=n;
    }

    @Override
    protected void deassegna(Casella ps) {
        soluzione[ps.i][ps.j]=0;
    }

    @Override
    protected void scriviSoluzione() {
        for( int i=0; i<ordine; i++ )
        {	for(int j=0; j<ordine; j++)
            System.out.print(soluzione[i][j]+" ");
            System.out.println();
        }
    }

    @Override
    protected boolean esisteSoluzione(Casella casella) {
        for( int i=0; i<ordine; i++ )
            for(int j=0; j<ordine; j++)
                if(soluzione[i][j]==0)
                    return false;
        numSoluzioni--;
        return true;
    }

    @Override
    protected boolean ultimaSoluzione(Casella casella) {
        return numSoluzioni<0;
    }

    @Override
    protected List<Casella> puntiDiScelta() {
        List<Casella> l= new LinkedList<>();
        for ( int i= 0; i<ordine ;i++)
            for(int j=0;j<ordine;j++)
                l.add(new Casella(i,j));
        return l;
    }

    @Override
    protected Collection<Integer> scelte(Casella casella) {
        Collection<Integer> caselle=new ArrayList<>();
        for (int i =0; i<ordine;i++)
            caselle.add(i+1);
        return caselle;
    }
    @Override
    protected void risolvi() {
        tentativo(puntiDiScelta(),new Casella(0,0));
    }
}

