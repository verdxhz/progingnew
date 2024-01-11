package so.ken;



import java.io.Serializable;
import java.util.LinkedList;

public class Blocco implements Serializable
{
    private static final long serialVersionUID=1L;
    protected LinkedList<Casella> caselleBlocco= new LinkedList<>();
    final protected int risultato;
    final protected Character operazione;

    public Blocco(LinkedList<Casella> caselle, int ris, Character op)
    {
        risultato=ris;
        operazione=op;
        for(Casella c: caselle)
            caselleBlocco.add(c);

    }

}
