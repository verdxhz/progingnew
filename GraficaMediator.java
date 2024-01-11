package so.ken;



import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class GraficaMediator
{
    private grafica grafica;
    private KenKen kenken;
    private LinkedList<Blocco> blocchi= new LinkedList<>();
    private File[] file= new File[]{new File("ing\\\\progetto\\\\Tre"),new File("ing\\progetto\\Quattro"),new File("ing\\progetto\\Cinque"),new File("ing\\progetto\\Sei")};

    public GraficaMediator(grafica g, KenKen k)
    {
        grafica=g;
        kenken=k;
        grafica.mediatore= this;

    }
    public void dimensioneClicked(int i)
    {
        kenken.setOrdine(i);
    }
    public int getDimensione()
    {
        return kenken.ordine;
    }
    public LinkedList<Blocco> getBlocchi()
    {
        return blocchi;
    }

    public void creaBlocco(String coordinate, String operazione, String risultato) {
        LinkedList<Casella> caselle= new LinkedList<>();
        StringTokenizer st= new StringTokenizer(coordinate,"()");
        while (st.hasMoreTokens())
        {
            StringTokenizer st1= new StringTokenizer(st.nextToken(),",");
            int i =Integer.parseInt(st1.nextToken());
            int j =Integer.parseInt(st1.nextToken());
            caselle.add(new Casella(i,j));
            grafica.grigliavincoli[i][j].setBackground(Color.LIGHT_GRAY);
        }
        blocchi.add(new Blocco(caselle,Integer.parseInt(risultato),operazione.charAt(0)));
    }
    public void inserisciblocchi()
    {
        kenken.setBlocchischema(blocchi);
        kenken.soluzione.risolvi();
    }
    public void aggiungi(Character text, int i, int j) {
        if(Character.getNumericValue(text)<=kenken.ordine)
            kenken.inserisci(i,j,Character.getNumericValue(text));
    }
    public void cancella(int riga, int colonna)
    {
        kenken.cancella(riga,colonna);
    }
    public void pulisci() {
        for (int i=0;i< kenken.ordine;i++)
            for (int j=0;j< kenken.ordine;j++)
            {
                grafica.griglia[i][j].setText("");
                grafica.griglia[i][j].setEditable(true);
            }
        kenken.pulisci();
    }
    public boolean controlla(int i, int j)
    {
        return kenken.correggi(i,j);
    }
    public void risolvi() {
        for (int i=0;i< kenken.ordine;i++)
            for (int j=0;j< kenken.ordine;j++)
            {
                int x=kenken.soluzione.soluzione[i][j];
                grafica.griglia[i][j].setText(Integer.toString(x));
                grafica.griglia[i][j].setEditable(false);
            }
    }
    public boolean casellaVuota(int i, int j) {
        return !(kenken.griglia[i][j] >0);
    }
    public File nomeFile() {
        File f= file[kenken.ordine-3];
        return f;
    }
    public void caricaSuFile(File nomeFile) throws IOException {
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(nomeFile));
        oos.writeObject(kenken);
        oos.close();
    }
    public void caricaDaFile(File nomeFile) {
        try {
            ObjectInputStream ois= new ObjectInputStream(new FileInputStream(nomeFile));
            this.kenken = (KenKen) ois.readObject();
            ois.close();
            } catch (IOException | ClassNotFoundException e) {
                return;
        }
        this.blocchi = kenken.blocchischema;
        kenken.soluzione.risolvi();
        }

}