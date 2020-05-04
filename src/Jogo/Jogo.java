/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jogo;

import Controlo.Arrays;
import Controlo.Constantes;
import Entidades.Artista;
import Entidades.Inimigo;
import InterfacesEClassesAbstractas.Nivel;
import Niveis.Nivel1;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Tício
 */
public class Jogo extends JPanel implements Runnable, KeyListener, MouseMotionListener{
    private boolean running;
    private long milis ;
    
    private int levelDoJogo;
    private int tempo;
    private boolean aindaNaoDesenhou;
    
    private ArrayList<Integer> vectorYSobeDesceNivel1;
    private ArrayList<Boolean> vectorAlternarSobDesce;
    private ArrayList<Integer> vectorLimitesSuperioresSobDesce;
    private ArrayList<Integer> vectorLimitesInferiresSobDesce;
    
    private Thread thread;
    private BufferedImage image;
    private Graphics2D g2d;
    
    
    private int score;
    
    //Depois esses atributos têm que ser passado no Construtor da classe Jogo
    private Artista artista;
    private ArrayList<Inimigo> inimigos;
    private Nivel nivelDoJogo;
    private int pontoChaveActual;
    /////////////////////////////////////////////////////////////////////////
    /////////////////PARTE DO MENU DO JOGO E Estado do Jogo//////////////////////////////////
    private ArrayList<Rectangle2D> arrayDeRectangulos;
    private ArrayList<Boolean> alternadorDeCores; 
    private int numeroDaTelaPraSeDesenhar;
    private boolean alternarDesenho;
    private boolean jogar;
    private boolean pausar;
    
    public Jogo ()
    {
        milis = 100;
        setPreferredSize(new Dimension(Constantes.LARGURA , Constantes.ALTURA));
        requestFocus();
        setBackground(Color.white);
        inicializar();
        inicializarArraysDoMenu ();
    }
    
    public void inicializarArraysDoMenu ()
    {
        arrayDeRectangulos = new ArrayList<>();
        rectangulosDoMenu (arrayDeRectangulos);
        
        alternadorDeCores = new ArrayList<>();
        
        for (int i = 0; i < arrayDeRectangulos.size(); ++i)
            alternadorDeCores.add(i, false);
    }
    
    public void inicializar ()
    {
        score = 0;
        levelDoJogo = 1;
        tempo = 0;
        aindaNaoDesenhou = true;
        
        nivelDoJogo = new Nivel1((int)((int) Constantes.LARGURA/2), null, null);
        artista = new Artista(true, true, false, false, nivelDoJogo);
        inimigos = nivelDoJogo.disponibilizarInimigosDoNivel();
        
        if (nivelDoJogo instanceof Nivel1)
        {    
            artista.setPodeMovimentarDireita(true);
            artista.setPodeMovimentarEsquerda(true);
            
            vectorYSobeDesceNivel1 = Arrays.yObjectosSobDesceNivel1(levelDoJogo);
            vectorAlternarSobDesce = Arrays.alternarObjectosSobDesceNivel1(levelDoJogo);
            vectorLimitesInferiresSobDesce = Arrays.limitInfObjectosSobDesceNivel1(levelDoJogo);
            vectorLimitesSuperioresSobDesce = Arrays.limitSuperObjectosSobDesceNivel1(levelDoJogo);
            Nivel1 nivel = (Nivel1) nivelDoJogo;
            nivel.setVectorYSobEDesce(vectorYSobeDesceNivel1);
        }
        
        pontoChaveActual= -1;
    }
    
    public boolean limiteAcima ()
    {
        boolean conscistencia = false;
       
        return true;
    }
    
    public boolean verificarLimites ()
    {
        boolean conscistencia = true;
        return conscistencia;
    }
    
    @Override
    public void addNotify ()
    {
        super.addNotify();
        if (thread == null)
        {
            thread = new Thread(this);
            thread.start();
        }    
    }
    
    @Override
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (alternarDesenho)
            switch(numeroDaTelaPraSeDesenhar)
            {
                case 0: formulacaoDoJogo(g2d);
                    break;
                case 1: desenharInformacoesNaJanela(g2d);
                    break;
                case 2: desenharExtrasNaJanela(g2d);
                    break;
                case 3: sairDoJogo();
                    break;
            }
        else
            desenharMenuNaTela(g2d);
    }
    
    public void formulacaoDoJogo (Graphics2D g2d)
    {
        if (!pausar)
        {
            nivelDoJogo.draw(g2d);
            artista.draw(g2d);

            for (int i = 0; i < inimigos.size(); ++i)
                ((Inimigo)inimigos.get(i)).draw(g2d);

            if (aindaNaoDesenhou && tempo < 25)
            {
                ++tempo;
                desenharLevelNaTela(g2d);
            }
            desenharScore(g2d);

            if (artista.isMorrer())
                desenharStringsNoCentro(g2d, new String ("Game Over"), new Font ("Castelar", Font.BOLD, 40));
            update (g2d);
        }
        else if (pausar)
                desenharStringsNoCentro(g2d, new String ("Pause"), new Font ("Castelar", Font.BOLD, 50));
    }
    
    public void desenharScore (Graphics2D g2d)
    {
        g2d.setColor(Color.red);
        g2d.setFont(new Font ("Times Roman", Font.BOLD + Font.ITALIC, 16));
        float larguraString = (float) g2d.getFontMetrics().getStringBounds(new String ("Score: " + score) , g2d).getWidth();
        g2d.drawString (new String ("Score: " + score), Constantes.LARGURA - larguraString - 10, 30);
    }
    
    public void desenharStringsNoCentro (Graphics2D g2d, String string, Font font)
    {
        g2d.setColor(Color.red);
        g2d.setFont(font);
        float larguraString = (float) g2d.getFontMetrics().getStringBounds(string , g2d).getWidth();
        g2d.drawString (string, Constantes.LARGURA/2 - larguraString/2, Constantes.ALTURA/2);
    }
    
    public void update (Graphics2D g2d)
    {   
        if (artista.isMorrer())
        {
            for (int i = 0; i < inimigos.size(); ++i)
                inimigos.get(i).setEstatico (true);
            
            artista.setPodeMovimentarDireita(false);
            artista.setPodeMovimentarEsquerda(false);
            artista.setSoco(false);
            artista.setPontape(false);
        }
        
        for (int i = 0; i < inimigos.size(); ++i)
            inimigos.get(i).setPosicaoXArtista(artista.getX());
        
        if (aindaNaoDesenhou == true && tempo >= 25)
        {
            tempo = 0;
            aindaNaoDesenhou = false;
        }
       
        if (inimigos.isEmpty())
        {
            System.out.println("Estou dentro");
            ++levelDoJogo;
            vectorYSobeDesceNivel1 = Arrays.yObjectosSobDesceNivel1(levelDoJogo);
            vectorAlternarSobDesce = Arrays.alternarObjectosSobDesceNivel1(levelDoJogo);
            vectorLimitesInferiresSobDesce = Arrays.limitInfObjectosSobDesceNivel1(levelDoJogo);
            vectorLimitesSuperioresSobDesce = Arrays.limitSuperObjectosSobDesceNivel1(levelDoJogo);
            Nivel1 nivel = (Nivel1) nivelDoJogo;
            nivel.setVectorYSobEDesce(vectorYSobeDesceNivel1);
            nivel.setLevelDoJogo(levelDoJogo);
            aindaNaoDesenhou = true;
            inimigos = nivel.disponibilizarInimigosDoNivel();
        }
        
        lutaComOInimigo (g2d);
        lutaComOArtista ();
        
        nivelDoJogo.setDX(artista.getX());
        if (nivelDoJogo instanceof Nivel1)
        {
            Nivel1 nivel1= (Nivel1) nivelDoJogo;
            nivel1.setxSobeDesce(170);
            nivel1.setVectorYSobEDesce(vectorYSobeDesceNivel1);
        }
        
        removerInimigosQueJaMorreram ();
    }
    
    public void desenharLevelNaTela (Graphics2D g2d)
    {
        g2d.setFont(new Font("Castellar", Font.BOLD, 26));
        float larguraLetra = (float) g2d.getFontMetrics().getStringBounds(new String("LEVEL " + levelDoJogo), g2d).getWidth();
        float alturaLetra = (float) g2d.getFontMetrics().getStringBounds(new String("LEVEL " + levelDoJogo), g2d).getHeight();
        
        g2d.setPaint(Color.GREEN);
        
        g2d.drawString(new String("LEVEL " + levelDoJogo), Constantes.LARGURA/2 - larguraLetra/2, Constantes.ALTURA/2 - alturaLetra/2);
    }
    
    public void removerInimigosQueJaMorreram ()
    {
        for (int i = 0; i < inimigos.size(); i++)
            if (inimigos.get(i).isSerRemovido())
            {
                score += inimigos.get(i).getNumeroDeGolpesPorVida() / 5;
                inimigos.remove(i);
            }
    }
    
    public void dectectarArtista ()
    {
        for (int i = 0; i < inimigos.size(); i++)
            if (inimigos.get(i).isSerRemovido())
                inimigos.remove(i);
    }
    
    public boolean esmagado ()
    {
        return true;
    }
    
    public void movimentarSobDesceNivel1 (int distanciaY)
    {
        for (int i= 0; i < vectorYSobeDesceNivel1.size(); ++i)
            if (vectorAlternarSobDesce.get(i) == false)
                vectorYSobeDesceNivel1.set(i,vectorYSobeDesceNivel1.get(i) + Math.abs(distanciaY));
            else 
                vectorYSobeDesceNivel1.set(i,vectorYSobeDesceNivel1.get(i) - Math.abs(distanciaY));
    }
    
    public void alternarSobDesceNivel1 ()
    {
        for (int i= 0; i < vectorYSobeDesceNivel1.size(); ++i)
        {
            if (vectorYSobeDesceNivel1.get(i) + 90 + 10 >= vectorLimitesInferiresSobDesce.get(i))
                vectorAlternarSobDesce.set(i, true);
            else if (vectorYSobeDesceNivel1.get(i) <= vectorLimitesSuperioresSobDesce.get(i))
                    vectorAlternarSobDesce.set(i, false);
        }
    }
        
    public void lutaComOInimigo (Graphics2D g2d)
    {
        if (artista.isPontape() || artista.isSoco())
                for (int i = 0; i < inimigos.size(); ++i)
                    if (artista.getX() <= (inimigos.get(i).getX() + inimigos.get(i).getLargura() - artista.getX()) && artista.getX() >= (inimigos.get(i).getX() - artista.getX()) )
                         if (artista.getY() + 15 >= inimigos.get(i).getY() && artista.getY() + 15 <= inimigos.get(i).getY() + inimigos.get(i).getAltura())   
                         {
                             g2d.setColor(Color.red);
                             g2d.fillRect(artista.getX(), artista.getY()+15, 15, 15);
                             inimigos.get(i).actualizarLimitesDoInimigo();
                             inimigos.get(i).setX(inimigos.get(i).getX() - 15);
                             inimigos.get(i).setGolpeado(true);
                         }
        
        if (artista.isPontape() || artista.isSoco())
                    for (int i = 0; i < inimigos.size(); ++i)
                        if (artista.getX() + 60 >= inimigos.get(i).getX() - artista.getX() && artista.getX() + 60 <= (inimigos.get(i).getX() + inimigos.get(i).getLargura() - artista.getX()))
                            if (artista.getY() + 15 >= inimigos.get(i).getY() && artista.getY() + 15 <= inimigos.get(i).getY() + inimigos.get(i).getAltura())
                            {
                                System.out.println("Ola");
                                g2d.setColor(Color.red);
                                g2d.fillRect(artista.getX()+60, artista.getY()+15, 15, 15);
                                inimigos.get(i).actualizarLimitesDoInimigo();
                                inimigos.get(i).setX(inimigos.get(i).getX() + 15);
                                inimigos.get(i).setGolpeado(true);
                            }
    }
    
    public void lutaComOArtista ()
    {   
        for (int i = 0; i < inimigos.size(); ++i){
            if (inimigos.get(i).isAtacarADireita())  
              if (!artista.isPontape()&& !artista.isSoco())
                     if ((inimigos.get(i).getX() + inimigos.get(i).getLargura() - artista.getX() >= artista.getX()))
                        if (!artista.isPontape()&& !artista.isSoco())
                            if (inimigos.get(i).getY() + 25 > artista.getY() && inimigos.get(i).getY() + 25 < artista.getY() + artista.getAltura())
                            {
                                artista.setGolpeado(true);
                                if (!artista.barreiraAFrente(10))
                                {
                                    artista.setX(artista.getX() + 10);
                                    artista.actualizarLimitesArtista();
                                }
                            }
                            else ;
                        else;
                     else;
               else;
            else if (inimigos.get(i).isAtacarAEsquerda())
                    if (!artista.isPontape()&& !artista.isSoco())
                        if ((inimigos.get(i).getX() - artista.getX() <= artista.getX() + artista.getLargura()) && ((inimigos.get(i).getX()- artista.getX()) >= (artista.getX())))
                            if (inimigos.get(i).getY() + 25 > artista.getY() && inimigos.get(i).getY() + 25 < artista.getY() + artista.getAltura())
                            {
                                artista.setGolpeado(true);
                                if (!artista.barreiraAtras(10))
                                {
                                    artista.setX(artista.getX() - 10);
                                    artista.actualizarLimitesArtista();
                                }
                            }
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////PARTE DO MENU DO JOGO ////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<String> stringsDoMenu ()
    {
        ArrayList<String> stringDoMenu = new ArrayList ();
        
        stringDoMenu.add(new String("Novo Jogo"));
        stringDoMenu.add(new String("Instruções"));
        stringDoMenu.add(new String("Extras"));
        stringDoMenu.add(new String("Sair"));
        
        return stringDoMenu;
    }
    
    public void rectangulosDoMenu ( ArrayList<Rectangle2D> listaDeShapes)
    {
        listaDeShapes.add(new Rectangle2D.Float(90, 70, 150, 28));
        listaDeShapes.add(new Rectangle2D.Float(90, 110, 150, 28));
        listaDeShapes.add(new Rectangle2D.Float(90, 150, 90, 28));
        listaDeShapes.add(new Rectangle2D.Float(90, 192, 65, 28));
    }
    
    public float getAlturaDaString (String string, Graphics2D g2d)
    {
        return (float) g2d.getFontMetrics().getStringBounds(string, g2d).getHeight();
    }
    
    public float getLarguraDaString (String string, Graphics2D g2d)
    {
        return (float) g2d.getFontMetrics().getStringBounds(string, g2d).getWidth();
    }
    
    public void desenharMenuNaTela (Graphics2D g2d)
    {
        desenharExtrasNaJanela (g2d);
        g2d.drawImage(new ImageIcon("Imagens Do Projecto\\ImagemFundoJogo.png").getImage(), 2, 6, this);

        g2d.setFont(new Font("Algerian", Font.CENTER_BASELINE, 25));
        g2d.setColor(Color.RED);

        float larguraLetra = (float) g2d.getFontMetrics().getStringBounds(new String("Missão de luta de Rua"), g2d).getWidth();
        float alturaLetra = (float) g2d.getFontMetrics().getStringBounds(new String("Missão de luta de Rua"), g2d).getHeight();

        g2d.drawString(new String("Missão De Luta De Rua"), Constantes.LARGURA / 2 - larguraLetra/2 , 25);

        g2d.setColor(Color.darkGray);
        g2d.setFont(new Font("Times Roman", Font.CENTER_BASELINE, 25));

        g2d.setStroke(new BasicStroke((float) 3.2));

        for (int i = 0; i < arrayDeRectangulos.size(); ++i)
        {
            if (alternadorDeCores.get(i) == true || i == numeroDaTelaPraSeDesenhar)
                g2d.setColor(Color.red);

            g2d.drawString(stringsDoMenu().get(i), 
            (float) (arrayDeRectangulos.get(i).getCenterX() - getLarguraDaString (stringsDoMenu().get(i), g2d)/2), 
            (float) (arrayDeRectangulos.get(i).getCenterY() + 10));

            g2d.drawRoundRect((int)arrayDeRectangulos.get(i).getX(), (int) arrayDeRectangulos.get(i).getY(), (int) arrayDeRectangulos.get(i).getWidth(), (int) arrayDeRectangulos.get(i).getHeight(), 15, 15);
            g2d.setColor(Color.darkGray);
        }

        g2d.drawImage(new ImageIcon("Imagens Do Projecto\\Axel_Golpeado_Direita\\axel_thrown.png").getImage(), 475, 210, this);
        g2d.drawImage(new ImageIcon("Imagens Do Projecto\\Ataque_Galsia_Left\\galsia_knife1.png").getImage(), 525, 210, this);
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////FIM DA PARTE DO MENU DO JOGO ///////////////////// 
    ///////////////////////////////////////////////////////////////////////////////////////////
    
    public void desenharExtrasNaJanela (Graphics2D g2d)
    {
        g2d.drawImage(new ImageIcon("Imagens Do Projecto\\Extras.png").getImage(), 0, 0, this);
    }
    
    public void desenharInformacoesNaJanela (Graphics2D g2d)
    {
        g2d.drawImage(new ImageIcon("Imagens Do Projecto\\Informacoes.png").getImage(), 0, 0, this);
    }
    
    public void sairDoJogo ()
    {
        System.exit(1);
    }
    
  /////////
    
    
    @Override
    public void run ()
    {
        running = true;
        while (running)
        {
            
            alternarSobDesceNivel1 ();
            movimentarSobDesceNivel1 (5);
            
            try {
                Thread.sleep(milis);
            } catch (InterruptedException ex) {
                Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
            }
            repaint();
        }
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {   
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if (!alternarDesenho)
        {
            if (e.getKeyCode() == KeyEvent.VK_DOWN)
            numeroDaTelaPraSeDesenhar = (numeroDaTelaPraSeDesenhar+1)%4;
            if (e.getKeyCode() == KeyEvent.VK_UP)
                numeroDaTelaPraSeDesenhar = (numeroDaTelaPraSeDesenhar-1)< 0 ? 3: (numeroDaTelaPraSeDesenhar-1)%4;
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
                alternarDesenho = true;
        }
        
        if (numeroDaTelaPraSeDesenhar == 0 && alternarDesenho)
            if (e.getKeyCode() == KeyEvent.VK_P) 
                if (pausar == false)
                    pausar = true;
                else if (pausar == true)
                         pausar = false;
        
        if (artista.isMorrer())
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                levelDoJogo = 1;
                numeroDaTelaPraSeDesenhar = 0;
                alternarDesenho = false;
                inicializar();
            }
        
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            alternarDesenho = false;
        
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            artista.setRight(true);
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            artista.setLeft(true);
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            artista.setDown(true);
        if (e.getKeyCode() == KeyEvent.VK_UP)
            artista.setUp(true);
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            artista.setJump(true);
        if (e.getKeyCode() == KeyEvent.VK_S)
            artista.setSoco(true);
        if (e.getKeyCode() == KeyEvent.VK_D)
            artista.setPontape(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            artista.setRight(false);
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            artista.setLeft(false);
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            artista.setDown(false);
        if (e.getKeyCode() == KeyEvent.VK_UP)
            artista.setUp(false);
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            artista.setJump(false);
        if (e.getKeyCode() == KeyEvent.VK_S)
            artista.setSoco(false);
        if (e.getKeyCode() == KeyEvent.VK_D)
            artista.setPontape(false);
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        if(!alternarDesenho)
            for (int i = 0; i < arrayDeRectangulos.size(); ++i)
                if (e.getX() - 10 >= arrayDeRectangulos.get(i).getX() && e.getX() - 10 <= arrayDeRectangulos.get(i).getX() + arrayDeRectangulos.get(i).getWidth())
                    if (e.getY() - 30 >= arrayDeRectangulos.get(i).getY() && e.getY() - 30 <= arrayDeRectangulos.get(i).getY() + arrayDeRectangulos.get(i).getHeight())
                    {
                        numeroDaTelaPraSeDesenhar = i;
                        alternadorDeCores.set(i, Boolean.TRUE);
                    }
                    else
                        alternadorDeCores.set(i, Boolean.FALSE);
                else
                    alternadorDeCores.set(i, Boolean.FALSE);
        repaint();
    }
}