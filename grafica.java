package so.ken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class grafica extends JFrame
{
    protected GraficaMediator mediatore;
    protected JTextField[][] griglia;
    protected JTextField[][] grigliavincoli;

    public grafica()
    {
        setTitle("KenKen");
        setVisible(true);
        setResizable(false);
        setSize(900,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel p=new JPanel();
        p.setLayout(null);
        p.setVisible(true);
        add(p);
        JLabel scelta=new JLabel("seleziona la dimensione dello schema:");
        scelta.setBounds(340,200,400,80);
        p.add(scelta);
        JButton tre= new JButton("3X3");
        tre.setBounds(250,300,100,80);
        tre.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                mediatore.dimensioneClicked(3);
                secondaPagina();
            }
        });
        p.add(tre);
        JButton quattro= new JButton("4X4");
        quattro.setBounds(350,300,100,80);
        quattro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mediatore.dimensioneClicked(4);
                secondaPagina();
            }
        });
        p.add(quattro);
        JButton cinque= new JButton("5X5");
        cinque.setBounds(450,300,100,80);
        cinque.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mediatore.dimensioneClicked(5);
                secondaPagina();
            }
        });
        p.add(cinque);
        JButton sei= new JButton("6X6");
        sei.setBounds(550,300,100,80);
        sei.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mediatore.dimensioneClicked(6);
                secondaPagina();
            }
        });
        p.add(sei);
    }
    private void secondaPagina()
    {
        getContentPane().removeAll(); // Rimuovi tutto il contenuto attuale dalla finestra
        JPanel p = new JPanel(null);
        p.setVisible(true);


        grigliavincoli = new JTextField[mediatore.getDimensione()][mediatore.getDimensione()];
        for(int i=0;i<mediatore.getDimensione();i++) {
            for (int j = 0; j < mediatore.getDimensione(); j++) {
                JTextField casella = new JTextField();
                grigliavincoli[i][j] = casella;
                casella.setEditable(false);
                casella.setFont(new Font(casella.getFont().getName(), Font.PLAIN, 10));
                casella.setHorizontalAlignment(JTextField.CENTER);
                casella.setText("(" + i + "," + j + ")");
                casella.setBackground(Color.WHITE);
                casella.setBounds(100 - (mediatore.getDimensione() * 2) + j * 300/mediatore.getDimensione(), 160 - (mediatore.getDimensione() * 2) + i * 300/mediatore.getDimensione(), 300/mediatore.getDimensione(), 300/ mediatore.getDimensione());
                add(casella);

            }
        }



        JButton carica = new JButton("Carica da File");
        carica.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                File f= mediatore.nomeFile();
                caricaDaFile(f);
            }});
        carica.setBounds(490,100,120,50);
        add(carica);
        JButton stop= new JButton("start");
        stop.setBounds(500,500,100,50);
        stop.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                mediatore.inserisciblocchi();
                caricaSuFile(mediatore.nomeFile());
                terzaPagina();
            }
        });
        add(stop);

        JTextField caselle = new JTextField("inserisci le caselle e premi invio(es.(1,2)(1,1))");
        caselle.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (caselle.getText().equals("inserisci le caselle e premi invio(es.(1,2)(1,1))")) {
                    caselle.setText(""); // Rimuovi il testo predefinito quando il campo di testo ottiene il focus
                }
            }
        });
        caselle.setBounds(440, 200, 245, 50);
        add(caselle);

        JTextField operando = new JTextField("inserisci l'operatore e premi invio(+,-,/ or *)");
        operando.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (operando.getText().equals("inserisci l'operatore e premi invio(+,-,/ or *)")) {
                    operando.setText(""); // Rimuovi il testo predefinito quando il campo di testo ottiene il focus
                }

            }
        });
        operando.setBounds(440, 270, 245, 50);
        add(operando);

        JTextField risultato = new JTextField("inserisci il risultato del blocco e premi invio");
        risultato.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (risultato.getText().equals("inserisci il risultato del blocco e premi invio")) {
                    risultato.setText(""); // Rimuovi il testo predefinito quando il campo di testo ottiene il focus
                }
            }
        });
        risultato.setBounds(440, 340, 245, 50);
        add(risultato);

        caselle.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    e.consume(); // Consuma l'evento "Invio" per prevenire la sua inserzione nel campo di testo
                    caselle.setEditable(false);
                }
                creaBlocco(caselle,risultato, operando);
            }
        });
        operando.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    e.consume(); // Consuma l'evento "Invio" per prevenire la sua inserzione nel campo di testo
                    operando.setEditable(false);
                }
                creaBlocco(caselle,risultato, operando);
            }
        });
        risultato.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    e.consume(); // Consuma l'evento "Invio" per prevenire la sua inserzione nel campo di testo
                    risultato.setEditable(false);
                }
                creaBlocco(caselle,risultato, operando);
            }
        });
        JLabel fine = new JLabel("dopo aver inserito tutti i blocchi usare 'stop'");
        fine.setBounds(430, 430, 300, 100);
        add(fine);
        add(p);
        revalidate();
        repaint();
    }
    private void caricaSuFile(File nomeFile) {
        try {
            mediatore.caricaSuFile(nomeFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void caricaDaFile(File nomeFile){
        mediatore.caricaDaFile(nomeFile);
        terzaPagina();
    }
    private void creaBlocco(JTextField caselle,JTextField risultato, JTextField operando)
    {
        if (!caselle.isEditable() && !operando.isEditable() && !risultato.isEditable()) {
            mediatore.creaBlocco(caselle.getText(), operando.getText(), risultato.getText());
            caselle.setText("inserisci le caselle e premi invio(es.(1,2)(1,1))");
            caselle.setEditable(true);
            risultato.setText("inserisci il risultato del blocco e premi invio");
            risultato.setEditable(true);
            operando.setText("inserisci l'operatore e premi invio(+,-,/ or *)");
            operando.setEditable(true);
        }
    }
    private void terzaPagina()
    {
        getContentPane().removeAll(); // Rimuovi tutto il contenuto attuale dalla finestra
        JLayeredPane p = new JLayeredPane();
        p.setVisible(true);
        griglia = new JTextField[mediatore.getDimensione()][mediatore.getDimensione()];
        for(int i=0;i<mediatore.getDimensione();i++)
        {
            for (int j = 0; j < mediatore.getDimensione(); j++)
            {
                JTextField casella = new JTextField();
                griglia[i][j]=casella;
                casella.setFont(new Font(casella.getFont().getName(), Font.PLAIN, 20));
                casella.setHorizontalAlignment(JTextField.CENTER);
                int riga=i;
                int colonna=j;
                casella.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!Character.isDigit(c) || Character.getNumericValue(c)>mediatore.getDimensione()) {
                            e.consume(); // Consuma l'evento se il carattere non è una cifra
                        } else {
                            mediatore.aggiungi(c, riga, colonna);
                        }
                        if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)
                            mediatore.cancella(riga,colonna);
                    }});
                casella.setBounds(100 - (mediatore.getDimensione() * 2) + j * 300/mediatore.getDimensione(), 160 - (mediatore.getDimensione() * 2) + i * 300/mediatore.getDimensione(), 300/mediatore.getDimensione(), 300/ mediatore.getDimensione());                p.add(casella,JLayeredPane.DEFAULT_LAYER);
            }
        }
        int i=0;
        for (Blocco b : mediatore.getBlocchi()) {
            Casella prima = b.caselleBlocco.getFirst();
            JLabel vincolo= new JLabel(b.risultato+" "+b.operazione);
            JTextField casella= griglia[prima.i][prima.j];
            vincolo.setBounds(casella.getX()+3,casella.getY()-(40-(mediatore.getDimensione()-2)*6), casella.getWidth(), casella.getHeight());
            p.add(vincolo,JLayeredPane.PALETTE_LAYER);
            for(Casella c: b.caselleBlocco ){
                try {
                    griglia[c.i][c.j].setBackground(new Color(90+ i, 90+ i, 90 + i));
                }catch (IllegalArgumentException e){
                    i=0;
                    griglia[c.i][c.j].setBackground(new Color(90, 90, 90));
                }
            } i+=33;}
        JButton check= new JButton("check");
        check.setBounds(600,180,100,50);
        check.addMouseListener(new MouseAdapter() {
            Color[][] c=new Color[mediatore.getDimensione()][mediatore.getDimensione()];
            @Override
            public void mousePressed(MouseEvent e) {
                for (int i=0;i< mediatore.getDimensione();i++)
                    for (int j=0;j< mediatore.getDimensione();j++)
                    {
                        c[i][j]= griglia[i][j].getBackground();
                        if(!mediatore.casellaVuota(i,j))
                        {
                            if (!mediatore.controlla(i, j))
                                griglia[i][j].setBackground(Color.RED);
                            else
                                griglia[i][j].setBackground(Color.GREEN);
                        }
                    }
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
                for (int i=0;i< mediatore.getDimensione();i++)
                    for (int j=0;j< mediatore.getDimensione();j++)
                        griglia[i][j].setBackground(c[i][j]);
            }
        });
        JButton pulisci= new JButton("clear all");
        pulisci.setBounds(600,240,100,50);
        pulisci.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mediatore.pulisci();
            }
        });
        JButton soluzione=new JButton("risolvi");
        soluzione.setBounds(600,300,100,50);
        soluzione.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mediatore.risolvi();
            }
        });

      //  JButton indietro = new JButton(Character.toString('\u2190'));
       // indietro.addMouseListener(new MouseAdapter() {
         //   @Override
           // public void mouseClicked(MouseEvent e) {
             //   grafica.SecondaPagina();
      //      }
       // });
        add(check);
        add(pulisci);
        add(soluzione);
        add(p);
        revalidate();
        repaint();
    }
    public static void main(String[] args) {
        grafica g=new grafica();
        g.mediatore=new GraficaMediator(g,new KenKen());
    }

}
