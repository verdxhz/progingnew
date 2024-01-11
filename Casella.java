package so.ken;

import java.io.Serializable;

public class Casella implements Serializable
{
    private static final long serialVersionUID=1L;
    protected int i,j;

    public Casella(int i, int j)
    {
        this.i=i;//riga
        this.j=j;//colonna
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casella casella = (Casella) o;
        return i == casella.i && j == casella.j;
    }

}
