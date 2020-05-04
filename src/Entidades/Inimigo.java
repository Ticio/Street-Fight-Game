/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import InterfacesEClassesAbstractas.Personagem;
import Controlo.Arrays;
import Controlo.Constantes;
import InterfacesEClassesAbstractas.Nivel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Tício
 */
public class Inimigo extends Personagem{

    private Nivel nivelDoJogo;
    
    private int posicaoXArtista;
    
    private boolean right;
    private boolean jump;
    private boolean left;
    private boolean ataque;
    private boolean controlarArtista;
    private boolean boraco;
    private boolean activarProcura;
    private boolean golpeado;
    private boolean recebeuGolpeNaEsquerda;
    
    private boolean atacarADireita;
    private boolean atacarAEsquerda;
    
    private boolean mostrarAEsquerdaOuDireita;
    
    private ArrayList<ImageIcon> imgAndarDireita;
    private ArrayList<ImageIcon> imgAndarEsquerda;
    private ArrayList<ImageIcon> imgAtacarDireita;
    private ArrayList<ImageIcon> imgAtacarEsquerda;
    
    private int posicaoImagemMovimentoDireita;
    private int posicaoImagemMovimentoEsquerda;
    private int posicaoImagemAtacarDireita;
    private int posicaoImagemAtacarEsquerda;
    
    private boolean activarEsmagamento;
    
    private boolean serRemovido;
    private boolean detectouArtista;
    
    private int limiteDeDeslocamentoADireita;
    private int limiteDeDeslocamentoAEsquerda;
    
    private int limiteSuperior;
    private int limiteInferior;
    
    private boolean estatico;
    
    public Inimigo(Nivel nivelDoJogo, int limiteDeDeslocamentoADireita, int limiteDeDeslocamentoAEsquerda, 
            int vidas, int xInicial, int yInicial, int numeroDeGolpesPorVida) {
        super(vidas, xInicial, yInicial, 0, 46, 68, numeroDeGolpesPorVida);
        this.nivelDoJogo = nivelDoJogo;
        this.mostrarAEsquerdaOuDireita = false;
        
        this.imgAndarDireita = Controlo.Arrays.preencherGalsia_AndarDireita();
        this.imgAndarEsquerda = Controlo.Arrays.preencherGalsia_AndarEsquerda();
        
        this.imgAtacarDireita = Arrays.preencherGalsia_AtaqueDireita();
        this.imgAtacarEsquerda = Arrays.preencherGalsia_AtaqueEsquerda();
                
        this.setLeft(true);
        this.limiteDeDeslocamentoADireita = limiteDeDeslocamentoADireita;
        this.limiteDeDeslocamentoAEsquerda = limiteDeDeslocamentoAEsquerda;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        update ();
        
        ImageIcon iIcon = obterImagem();
        
        g2d.drawImage(iIcon.getImage(), (this.getX() - posicaoXArtista), this.getY(), null);
    }
    
    public void update ()
    {
        int deslocamento = 2;
        
        if (this.getY() > Constantes.ALTURA)
            setSerRemovido(true);
        
        if (getNumeroDeGolpesPorVida() <= getGolpesRecebidos())
        {
            setVidas(getVidas() - 1);
            setGolpesRecebidos(0);
        }
        
        actualizarLimitesDoInimigo ();
        
        if (isDetectouArtista())
            if(isRight())
                if (barreiraAFrente (2))
                    deslocamento = 0;
                else;
            else if (isLeft()) 
                    if (barreiraAtras(2))
                         deslocamento = 0;
                    else;
            else 
                deslocamento = 2;
        
        //Traduz po efeito de gravidade ao encontrar uma base num nivel mais para baixo
        if (limiteInferior > getY())
               new CairParaOutraBase().start();
        
        if (isGolpeado())
            setGolpesRecebidos(getGolpesRecebidos() + 1);
        
        //Deteta o artista 
        if (this.getPosicaoXArtista() >= this.getX() - this.getPosicaoXArtista() - 100)
            setDetectouArtista(true);
        
        //Faz com que o inimigo ataque o artista
        //46 representa a lagura do artista
        if (isDetectouArtista() && this.getX() - this.getPosicaoXArtista() - 50 >= this.getPosicaoXArtista())
            setAtacarAEsquerda(true);
        
        
        if (isDetectouArtista() &&  this.getX() - this.getPosicaoXArtista() + 50 <= this.getPosicaoXArtista())
            setAtacarADireita(true);
        
        if (isDetectouArtista() && this.getX() - this.getPosicaoXArtista() - this.getPosicaoXArtista() > 100 || isDetectouArtista() && this.getX() - this.getPosicaoXArtista() - this.getPosicaoXArtista() > 100)
        {
            setAtacarAEsquerda(false);
            setAtacarADireita(false);
        }
        
        //Faz com que o inimigo se movimente 
            if (!isDetectouArtista())
                if (getX() - posicaoXArtista>= limiteDeDeslocamentoADireita - getPosicaoXArtista())
                {
                    setLeft(true);
                    setRight(false);
                }
                else if (getX() - posicaoXArtista <= limiteDeDeslocamentoAEsquerda - getPosicaoXArtista())
                     {
                         setLeft(false);
                         setRight(true);
                     }
                     else ;
            else if (this.getPosicaoXArtista() > this.getX() - this.getPosicaoXArtista())
                 {
                    setLeft(false);
                    setRight(true);
                 }
                 else if (this.getPosicaoXArtista() < this.getX() - this.getPosicaoXArtista()) 
                      {
                          setLeft(true);
                          setRight(false);
                      }
        
        if (getVidas() <= 0)
            setSerRemovido(true);
        
        if (!isEstatico())
            if (isRight())
                movimentarADireita(deslocamento);
            else if (isLeft())
                    movimentarAEsquerda(deslocamento);
    }

    //////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////SETS and GETS//////////////////////////
    //////////////////////////////////////////////////////////////////////////
    public Nivel getNivelDoJogo() {
        return nivelDoJogo;
    }

    public boolean isEstatico() {
        return estatico;
    }

    public void setEstatico(boolean estatico) {
        this.estatico = estatico;
    }
    
    public int getPosicaoXArtista() {
        return posicaoXArtista;
    }

    public void setPosicaoXArtista(int posicaoXArtista) {
        this.posicaoXArtista = posicaoXArtista;
    }
    
    public void setNivelDoJogo(Nivel nivelDoJogo) {
        this.nivelDoJogo = nivelDoJogo;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDetectouArtista() {
        return detectouArtista;
    }

    public void setDetectouArtista(boolean detectouArtista) {
        this.detectouArtista = detectouArtista;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isAtaque() {
        return ataque;
    }

    public void setAtaque(boolean ataque) {
        this.ataque = ataque;
    }

    public boolean isControlarArtista() {
        return controlarArtista;
    }

    public void setControlarArtista(boolean controlarArtista) {
        this.controlarArtista = controlarArtista;
    }

    public boolean isAtacarADireita() {
        return atacarADireita;
    }

    public void setAtacarADireita(boolean atacarADireita) {
        this.atacarADireita = atacarADireita;
    }

    public boolean isAtacarAEsquerda() {
        return atacarAEsquerda;
    }

    public void setAtacarAEsquerda(boolean atacarAEsquerda) {
        this.atacarAEsquerda = atacarAEsquerda;
    }

    public boolean isBoraco() {
        return boraco;
    }

    public void setBoraco(boolean boraco) {
        this.boraco = boraco;
    }

    public boolean isRecebeuGolpeNaEsquerda() {
        return recebeuGolpeNaEsquerda;
    }

    public void setRecebeuGolpeNaEsquerda(boolean recebeuGolpeNaEsquerda) {
        this.recebeuGolpeNaEsquerda = recebeuGolpeNaEsquerda;
    }
    
    public boolean isGolpeado() {
        return golpeado;
    }

    public void setGolpeado(boolean golpeado) {
        this.golpeado = golpeado;
    }
    
    public boolean isActivarProcura() {
        return activarProcura;
    }

    public boolean isSerRemovido() {
        return serRemovido;
    }
    
    public void setSerRemovido(boolean serRemovido) {
        this.serRemovido = serRemovido;
    }

    public void setActivarProcura(boolean activarProcura) {
        this.activarProcura = activarProcura;
    }

    public boolean isMostrarAEsquerdaOuDireita() {
        return mostrarAEsquerdaOuDireita;
    }

    public void setMostrarAEsquerdaOuDireita(boolean mostrarAEsquerdaOuDireita) {
        this.mostrarAEsquerdaOuDireita = mostrarAEsquerdaOuDireita;
    }

    public ArrayList<ImageIcon> getImgAndarDireita() {
        return imgAndarDireita;
    }

    public void setImgAndarDireita(ArrayList<ImageIcon> imgAndarDireita) {
        this.imgAndarDireita = imgAndarDireita;
    }

    public int getPosicaoImagemMovimentoDireita() {
        return posicaoImagemMovimentoDireita;
    }

    public void setPosicaoImagemMovimentoDireita(int posicaoImagemMovimentoDireita) {
        this.posicaoImagemMovimentoDireita = posicaoImagemMovimentoDireita;
    }

    public int getPosicaoImagemMovimentoEsquerda() {
        return posicaoImagemMovimentoEsquerda;
    }

    public void setPosicaoImagemMovimentoEsquerda(int posicaoImagemMovimentoEsquerda) {
        this.posicaoImagemMovimentoEsquerda = posicaoImagemMovimentoEsquerda;
    }
    
 
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean barreiraAFrente (int deslocamento)
    {
       ArrayList<Shape> shapes = nivelDoJogo.getShapes();
       ArrayList<String> designacoes = nivelDoJogo.getDesignaçãoDaShape();
       int indiceDaBaseDoInimigo = getIndiceDaseDoInimigo();
       
       if (indiceDaBaseDoInimigo >= 0 && indiceDaBaseDoInimigo + 1 < shapes.size())       
           if (this.getX() - getPosicaoXArtista() + this.getLargura() >= ((Rectangle2D)shapes.get(indiceDaBaseDoInimigo + 1)).getX())
               if (this.getY() + this.getAltura() > ((Rectangle2D)shapes.get(indiceDaBaseDoInimigo + 1)).getY())
                   if (!(designacoes.get(indiceDaBaseDoInimigo).equalsIgnoreCase("Base Solo") 
                           && 
                       designacoes.get(indiceDaBaseDoInimigo + 1).equalsIgnoreCase("Base de Cima")))
                   return true;
       return false;
    }
    
    
    public boolean barreiraAtras(int deslocamento)
    {
       ArrayList<Shape> shapes = nivelDoJogo.getShapes();
       ArrayList<String> designacoes = nivelDoJogo.getDesignaçãoDaShape();
       int indiceDaBaseDoInimigo = getIndiceDaseDoInimigo();
       
      
       if (indiceDaBaseDoInimigo -1 >= 0)
           if ((int)((Rectangle2D)shapes.get(indiceDaBaseDoInimigo - 1)).getX()  + ((Rectangle2D)shapes.get(indiceDaBaseDoInimigo - 1)).getWidth()
           >= this.getX() - getPosicaoXArtista() - deslocamento)
               if (this.getY() + this.getAltura() > ((Rectangle2D)shapes.get(indiceDaBaseDoInimigo - 1)).getY())
                   if ((designacoes.get(indiceDaBaseDoInimigo - 1)).equalsIgnoreCase("Base Solo"))
                        return true;
       return false;
    }
    
    public void movimentarADireita (int deslocamento)
    {
        setX(getX() + deslocamento);
        setPosicaoImagemMovimentoEsquerda(0);
        setPosicaoImagemMovimentoDireita((getPosicaoImagemMovimentoDireita()+ 1) % 3);
    }
    
    public void movimentarAEsquerda (int deslocamento)
    {
        setX(getX() - deslocamento);
        setPosicaoImagemMovimentoDireita(0);
        setPosicaoImagemMovimentoEsquerda((getPosicaoImagemMovimentoEsquerda()+ 1) % 3);
    }
   
    public int getIndiceDaseDoInimigo()
    {
        ArrayList<Shape> shapes = nivelDoJogo.getShapes();
        Rectangle2D base = baseDoInimigo();
        
        if (base != null)
            for (int i = 0; i < shapes.size(); ++i)
            {
                Rectangle2D rect = (Rectangle2D)shapes.get(i);
                
                if (base.getX() == rect.getX() && base.getY()== rect.getY())
                    return i;
            }
        return -1;    
    }
        
    public Rectangle2D baseDoInimigo()
    {
        ArrayList<Shape> shapes = nivelDoJogo.getShapes();
        for (int i = 0; i < shapes.size(); ++i)
        {
            Rectangle2D rect = (Rectangle2D)shapes.get(i);
            if ((this.getX() - this.getPosicaoXArtista()) >= rect.getX() - 20 && (this.getX() - this.getPosicaoXArtista()) <= rect.getX() + rect.getWidth() - 10)
                if (this.getY() + this.getAltura() == rect.getY())
                    return rect;
        }
        return null;    
    }
    
    public void actualizarLimitesDoInimigo ()
    {
        if (baseDoInimigo() == null)
        {
            this.limiteSuperior = getY();
            this.limiteInferior = Constantes.ALTURA + 5;
        }
        else
        {
            this.limiteSuperior = (int) (baseDoInimigo().getY() - 100 - getAltura());
            this.limiteInferior = (int) (baseDoInimigo().getY() - getAltura());
        }
    }
    
    class CairParaOutraBase extends Thread{
           
        @Override
        public void run ()
        {
            while (baseDoInimigo() == null)
            {
                setY(getY() + 1);
                
                if (baseDoInimigo() != null)
                {
                    try {
                        this.join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }
    }
    
    
    class AnimacaoInimigoEsmagado extends Thread{
        @Override
        public void run ()
        {
            do {  
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (true);
        }
    }
    
    public ImageIcon obterImagem ()
    {
        if (isGolpeado())
        {
            setGolpeado(false);
            if (isLeft())
                return new ImageIcon("Imagens Do Projecto\\Galsia_Left_Golpeado\\galsia_pain.png");
            return new ImageIcon("Imagens Do Projecto\\Galsia_Right_Golpeado\\galsia_pain.png");
        }
        if (!isEstatico())
            if (isRight() && isAtacarADireita())
                return imgAtacarDireita.get((posicaoImagemAtacarDireita++) % 3);
        if (!isEstatico())
            if (isLeft() && isAtacarAEsquerda())
                return imgAtacarEsquerda.get((posicaoImagemAtacarEsquerda++) % 3);
        if (isRight())
            return imgAndarDireita.get(posicaoImagemMovimentoDireita);
        return imgAndarEsquerda.get(posicaoImagemMovimentoEsquerda);       
    }
}