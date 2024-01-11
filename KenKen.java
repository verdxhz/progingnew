package so.ken;


import java.io.Serializable;
import java.util.LinkedList;

public class KenKen implements Serializable {
    private static final long serialVersionUID=1L;
    protected int ordine;
    protected int[][] griglia;
    protected Soluzione soluzione;
    protected LinkedList<Blocco> blocchischema;
    public void setOrdine(int ordine)
    {
        this.ordine = ordine;
        griglia= new int[ordine][ordine];
    }

    public void setBlocchischema(LinkedList<Blocco> blocchischema)
    {
        this.blocchischema = blocchischema;
        soluzione=new Soluzione(this);
    }
    public boolean correggi(int i,int j)
    {
        if (griglia[i][j]!=0 && griglia[i][j]!=soluzione.soluzione[i][j])
            return false;
        return true;
    }

    public void inserisci(int riga, int colonna, int num)
    {
        griglia[riga][colonna]=num;
    }

    public void cancella(int riga, int colonna)
    {
        inserisci(riga,colonna,0);
    }

    public void pulisci()
    {
        for(int i=0;i<ordine;i++)
            for (int j=0;j<ordine;j++)
                cancella(i,j);
    }
}
