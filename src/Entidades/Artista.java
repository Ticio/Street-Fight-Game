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
import Niveis.Nivel1;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Tício
 */
public class Artista extends Personagem{
    
    private Nivel nivelDoJogo;
    
    private boolean morrer;
    
    private boolean guardouBase;
    private Rectangle2D baseAntesDeSaltar;
    
    private boolean left;
    private boolean down;
    private boolean up;
    private boolean right;
    private boolean jump;
    private boolean soco;
    private boolean pontape;
    
    private boolean golpeado;
    
    private int posicaoImagemMovimentoDireita;
    private int posicaoImagemMovimentoEsquerda;
    private int posicaoImagemArtistaEsmagado;
    private int posicaoImagemJump;
    private int posicaoImagemSoco;
    private int posicaoImagemPontape;
    
    //Combo Braço
    private boolean teclaS;
    private boolean teclaD;
    
    private boolean podeMovimentarDireita;
    private boolean podeMovimentarEsquerda;
    private boolean podeMovimentarCima;
    private boolean podeMovimentarBaixo;
    
    private boolean activarEsmagamento;
    private boolean controladorDeSalto;
    
    private ArrayList<ImageIcon> imgAndarDireita;
    private ArrayList<ImageIcon> imgEsmagadoDireita;
    private ArrayList<ImageIcon> imgJumpDireita;
    private ArrayList<ImageIcon> imgAtaqueBracoDireita;
    private ArrayList<ImageIcon> imgAtaquePernaDireita;
    private ArrayList<ImageIcon> imgAtaqueAmazingDireita;
    
    
    private ArrayList<ImageIcon> imgAndarEsquerda;
    private ArrayList<ImageIcon> imgEsmagadoEsquerda;
    private ArrayList<ImageIcon> imgJumpEsquerda;
    private ArrayList<ImageIcon> imgAtaqueBracoEsquerda;
    private ArrayList<ImageIcon> imgAtaquePernaEsquerda;
    private ArrayList<ImageIcon> imgAtaqueAmazingEsquerda;
    
    private int limiteSuperior;
    private int limiteInferior;
    
    private int deslocamento;
    
    //Variaveis de controle de imagem a ser mostrada quando nenhuma tecla é clicada
    private boolean mostrarEsquerda = false;
    
    public Artista(boolean podeMovimentarDireita, boolean podeMovimentarEsquerda, boolean podeMovimentarCima, 
            boolean podeMovimentarBaixo, Nivel nivelDoJogo) {
        super(5, Constantes.ARTISTA_X, Constantes.ARTISTA_Y, 0, 28, 78, 5);
        
        posicaoImagemMovimentoEsquerda = 0;
        posicaoImagemMovimentoDireita = 0;
        
        this.baseAntesDeSaltar = null;
        
        this.podeMovimentarBaixo = podeMovimentarBaixo;
        this.podeMovimentarCima = podeMovimentarCima;
        this.podeMovimentarDireita = podeMovimentarDireita;
        this.podeMovimentarEsquerda = podeMovimentarEsquerda;
        this.nivelDoJogo  = nivelDoJogo;
        
        this.imgAndarEsquerda = Arrays.preencherAxel_AndarEsquerda();
        this.imgEsmagadoEsquerda = Controlo.Arrays.preencherAxel_Esquerda_Esmagado();
        this.imgJumpEsquerda = Arrays.preencherAxel_Esquerda_Jump();
        this.imgAtaqueBracoEsquerda = Arrays.preencherAxel_Esquerda_AtaqueBraco();
        this.imgAtaquePernaEsquerda = Arrays.preencherAxel_Esquerda_AtaquePerna();
        this.imgAtaqueAmazingEsquerda =  Arrays.preencherAxel_Esquerda_AtaqueAmazing();
        
        this.imgAndarDireita = Arrays.preencherAxel_AndarDireita();
        this.imgEsmagadoDireita = Arrays.preencherAxel_Direita_Esmagado();
        this.imgJumpDireita = Arrays.preencherAxel_Direita_Jump();
        this.imgAtaqueBracoDireita = Arrays.preencherAxel_Direita_AtaqueBraco();
        this.imgAtaquePernaDireita = Arrays.preencherAxel_Direita_AtaquePerna();
        this.imgAtaqueAmazingDireita =  Arrays.preencherAxel_Direita_AtaqueAmazing();
        
        deslocamento = 6;
    }

    public boolean isGolpeado() {
        return golpeado;
    }

    public void setGolpeado(boolean golpeado) {
        this.golpeado = golpeado;
    }
    
    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
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

    public boolean isMorrer() {
        return morrer;
    }

    public void setMorrer(boolean morrer) {
        this.morrer = morrer;
    }

    public ArrayList<ImageIcon> getImgsAndarDireita() {
        return imgAndarDireita;
    }

    public void setImgsAndarDireita(ArrayList<ImageIcon> imgAndarDireita) {
        this.imgAndarDireita = imgAndarDireita;
    }

    public ArrayList<ImageIcon> getImgAndarDireita() {
        return imgAndarDireita;
    }

    public void setImgAndarDireita(ArrayList<ImageIcon> imgAndarDireita) {
        this.imgAndarDireita = imgAndarDireita;
    }

    public ArrayList<ImageIcon> getImgAndarEsquerda() {
        return imgAndarEsquerda;
    }

    public void setImgAndarEsquerda(ArrayList<ImageIcon> imgAndarEsquerda) {
        this.imgAndarEsquerda = imgAndarEsquerda;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isPodeMovimentarDireita() {
        return podeMovimentarDireita;
    }

    public void setPodeMovimentarDireita(boolean podeMovimentarDireita) {
        this.podeMovimentarDireita = podeMovimentarDireita;
    }

    public boolean isPodeMovimentarEsquerda() {
        return podeMovimentarEsquerda;
    }

    public void setPodeMovimentarEsquerda(boolean podeMovimentarEsquerda) {
        this.podeMovimentarEsquerda = podeMovimentarEsquerda;
    }

    public boolean isPodeMovimentarCima() {
        return podeMovimentarCima;
    }

    public void setPodeMovimentarCima(boolean podeMovimentarCima) {
        this.podeMovimentarCima = podeMovimentarCima;
    }

    public boolean isPodeMovimentarBaixo() {
        return podeMovimentarBaixo;
    }

    public void setPodeMovimentarBaixo(boolean podeMovimentarBaixo) {
        this.podeMovimentarBaixo = podeMovimentarBaixo;
    }

    public boolean isSoco() {
        return soco;
    }

    public void setSoco(boolean soco) {
        this.soco = soco;
    }

    public boolean isPontape() {
        return pontape;
    }

    public void setPontape(boolean pontape) {
        this.pontape = pontape;
    }
    
    public boolean barreiraAFrente (int deslocamento)
    {
       ArrayList<Shape> shapes = nivelDoJogo.getShapes();
       ArrayList<String> designacoes = nivelDoJogo.getDesignaçãoDaShape();
       int indiceDaBaseDoArtista = getIndiceDaseDoArtista();
       
       if (indiceDaBaseDoArtista >= 0 && indiceDaBaseDoArtista + 1 < shapes.size())       
           if (this.getX() + this.getLargura() + deslocamento>= ((Rectangle2D)shapes.get(indiceDaBaseDoArtista + 1)).getX())
               if (this.getY() + this.getAltura() > ((Rectangle2D)shapes.get(indiceDaBaseDoArtista + 1)).getY())
                   if (!(designacoes.get(indiceDaBaseDoArtista).equalsIgnoreCase("Base Solo") 
                           && 
                       designacoes.get(indiceDaBaseDoArtista + 1).equalsIgnoreCase("Base de Cima")))
                   return true;
       return false;
    }
    
    
    public boolean barreiraAtras(int deslocamento)
    {
       ArrayList<Shape> shapes = nivelDoJogo.getShapes();
       ArrayList<String> designacoes = nivelDoJogo.getDesignaçãoDaShape();
       int indiceDaBaseDoArtista = getIndiceDaseDoArtista();
       
       if (indiceDaBaseDoArtista -1 >= 0)
           if ((int)((Rectangle2D)shapes.get(indiceDaBaseDoArtista - 1)).getX()  + ((Rectangle2D)shapes.get(indiceDaBaseDoArtista - 1)).getWidth()
           >= this.getX() - 2 - deslocamento)
               if (this.getY() + this.getAltura() > ((Rectangle2D)shapes.get(indiceDaBaseDoArtista - 1)).getY())
                   if ((designacoes.get(indiceDaBaseDoArtista - 1)).equalsIgnoreCase("Base Solo"))
                        return true;
       return false;
    }

    public Nivel getNivelDoJogo() {
        return nivelDoJogo;
    }

    public void setNivelDoJogo(Nivel nivelDoJogo) {
        this.nivelDoJogo = nivelDoJogo;
    }
    
    public int getLimiteSuperior() {
        actualizarLimitesArtista();
        return limiteSuperior;
    }

    public boolean isMostrarEsquerda() {
        return mostrarEsquerda;
    }
    
    public void setLimiteSuperior(int limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public int getLimiteInferior() {
        actualizarLimitesArtista();
        return limiteInferior;
    }

    public void setLimiteInferior(int limiteInferior) {
        this.limiteInferior = limiteInferior;
    }
    
    public void movimentar ()
    {
        if (activarEsmagamento == false)
            if (artistaFoiEsmagadoSobeDesce())
            {
                activarEsmagamento = true;
                setMorrer(true);
            }
        
        if (controladorDeSalto ==  false)
            if (limiteInferior > getY())
               new CairParaOutraBase().start();
        
        if (isRight() && isPodeMovimentarDireita() && !barreiraAFrente(0))
        {
            if (!controladorDeSalto)
                actualizarLimitesArtista();
            movimentarADireita(deslocamento);
        }    
        
        if (isLeft() &&  isPodeMovimentarEsquerda() && !barreiraAtras(0))
        {
            if (!controladorDeSalto)
                actualizarLimitesArtista ();
            movimentarAEsquerda(deslocamento);
        }
        
        //Por enquanto fica aqui 
        if (activarEsmagamento == true)
        {    
            this.setVidas(0);
            this.setPodeMovimentarDireita(false);
            this.setPodeMovimentarEsquerda(false);
            
            if (getX() >= 51 && getX() <= 161)
                if (posicaoImagemArtistaEsmagado == 2)
                    this.setY(280);
                else this.setY(255);
            else if (getX() >= 188 && getX() <= 297)
                    if (posicaoImagemArtistaEsmagado == 2)
                       this.setY(215);
                    else this.setY(190);
                 else if (getX() >= 301 && getX() <= 701)
                         if (posicaoImagemArtistaEsmagado == 2)
                            this.setY(250);
                         else this.setY(225);
        }
        if (isMorrer())
            new AnimacaoArtistaEsmagado().start();
        
        if (isJump())
        {
            deslocamento = 10;
            saltar();
        }
        else 
            deslocamento = 6;
    }

    public boolean isGuardouBase() {
        return guardouBase;
    }

    public void setGuardouBase(boolean guardouBase) {
        this.guardouBase = guardouBase;
    }
    
    public void movimentarADireita (int deslocamento)
    {
        setX(getX() + deslocamento);
        mostrarEsquerda = false;
        setPosicaoImagemMovimentoDireita((getPosicaoImagemMovimentoDireita()+ 1) % 6);
        setPosicaoImagemMovimentoEsquerda(0);
    }
    
    public void movimentarAEsquerda (int deslocamento)
    {
        setX(getX() - deslocamento);
        mostrarEsquerda = true;
        setPosicaoImagemMovimentoDireita(0);
        setPosicaoImagemMovimentoEsquerda((getPosicaoImagemMovimentoEsquerda()+ 1) % 6);
    }
    
    public void saltar ()
    {
        Saltar salto = new Saltar();
        salto.start();
    }
    
    public int getIndiceDaseDoArtista()
    {
        ArrayList<Shape> shapes = nivelDoJogo.getShapes();
        Rectangle2D base = baseDoArtista();
        
        if (base != null)
            for (int i = 0; i < shapes.size(); ++i)
            {
                Rectangle2D rect = (Rectangle2D)shapes.get(i);

                if (base.getX() == rect.getX() && base.getY()== rect.getY())
                    return i;
            }
        return -1;    
    }
    
    //ESSES METODOS TÊM QUE SER ORGANIZADOS NOVAMENTE NO PANEL JOGO
    public boolean artistaFoiEsmagadoSobeDesce ()
    {
        Nivel1 nivel1 = (Nivel1) nivelDoJogo;
       ArrayList<ObjectoSobDesce> objectos = nivel1.getObjectoSobDesce();
       
       for (int i = 0; i < objectos.size(); ++i)
       {
           ObjectoSobDesce sobDesce = objectos.get(i);
           if (this.getX() + this.getLargura() >= sobDesce.getX() && this.getX() <= sobDesce.getX() + sobDesce.getLargura())
               if (sobDesce.getY() + sobDesce.getLargura() >= this.getY())
                   return true;
       }
       return false;
    }
    
    public Rectangle2D baseDoArtista()
    {
        ArrayList<Shape> shapes = nivelDoJogo.getShapes();
        for (int i = 0; i < shapes.size(); ++i)
        {
            Rectangle2D rect = (Rectangle2D)shapes.get(i);
            
            if (this.getX() >= rect.getX() - 20 && this.getX() <= rect.getX() + rect.getWidth() - 10)
                if (this.getY() + this.getAltura() == rect.getY())
                    return rect;
        }
        return null;    
    }
    
    /**
     *
     * @param g
     */
    @Override
    public void draw (Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        update ();
        ImageIcon iIcon = obterImagem();
       
        g2d.drawImage(iIcon.getImage(), this.getX(), this.getY(), null);
        desenharVidasNaTela (g2d);
    }
    
    public void update ()
    {
        movimentar();
        if (getNumeroDeGolpesPorVida() <= getGolpesRecebidos())
        {
            setVidas(getVidas() - 1);
            setGolpesRecebidos(0);
        }
        
        if (isGolpeado())
            setGolpesRecebidos(getGolpesRecebidos() + 1);
        
        if (getY() + getAltura() >= Constantes.ALTURA)
            setVidas(0);
        if (getVidas() <= 0)
            setMorrer(true);
    }
    
    public void desenharVidasNaTela (Graphics2D g2d)
    {
        for (int i = 0; i < getVidas(); ++i)
        {    
            g2d.setColor(Color.red);
            g2d.fill(new Ellipse2D.Float(15+(i+1)*21, 15, 18, 18));
        }
    }
    
    public void actualizarLimitesArtista ()
    {
        if (baseDoArtista() == null)
        {
            this.limiteSuperior = getY();
            this.limiteInferior = Constantes.ALTURA + 5;
        }
        else
        {
            this.limiteSuperior = (int) (baseDoArtista().getY() - 100 - getAltura());
            this.limiteInferior = (int) (baseDoArtista().getY() - getAltura());
        }
    }
    
    class CairParaOutraBase extends Thread{
           
        @Override
        public void run ()
        {
            while (baseDoArtista() == null)
            {
                setY(getY() + 1);
                
                if (baseDoArtista() != null)
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
    
    
    class AnimacaoArtistaEsmagado extends Thread{
           
        @Override
        public void run ()
        {
            do {  
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
                }
                posicaoImagemArtistaEsmagado++;
                if (posicaoImagemArtistaEsmagado > 2)
                    try {
                        posicaoImagemArtistaEsmagado = 2;
                        activarEsmagamento = true;
                        this.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (true);
        }
    }
    
    class Saltar extends Thread{
           
        @Override
        public void run ()
        {
            controladorDeSalto = true;
            int valorDoY = getY();
            boolean alternar = false;
            do
            {
                setJump(false);
                
                if (activarEsmagamento)
                {
                        controladorDeSalto = false;
                        setGuardouBase(false);
                        try {
                            this.join();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
                
                if (alternar == false)
                {
                    setY(getY()  - 1);
                    posicaoImagemJump = 1;
                }
                else
                {
                    setY(getY() + 1);
                    posicaoImagemJump = 0;
                }
                if (getY() == valorDoY - 115)
                    alternar = true;
                
                
                if (alternar)
                    if (baseDoArtista() != null)
                    {
                        setGuardouBase(false);
                        controladorDeSalto = false;
                        try {
                            this.join();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                
                try {
                Thread.sleep((long) 3.33333333333333333333333333);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
            while(true);
        }
    }
    
    public ImageIcon obterImagem ()
    {
        //QUANDO FALSO É A DIREITA QUE DEVE MOSTRAR
        
        if (mostrarEsquerda == false)
        {
            if (isMorrer())
                return imgEsmagadoDireita.get(posicaoImagemArtistaEsmagado);
            if (isGolpeado())
            {
                setGolpeado(false);
                return new ImageIcon("Imagens Do Projecto\\Axel_Golpeado_Direita\\axel_thrown.png");
            }
            if (isSoco())
                return imgAtaqueBracoDireita.get((posicaoImagemSoco++) % 3);
            if (isPontape())
                return imgAtaquePernaDireita.get((posicaoImagemPontape++) % 2);
            if (controladorDeSalto == true)
                return imgJumpDireita.get(posicaoImagemJump);
            return imgAndarDireita.get(posicaoImagemMovimentoDireita);
        }
        else
        {
            if (isMorrer())
                return imgEsmagadoEsquerda.get(posicaoImagemArtistaEsmagado);
            if (isGolpeado())
            {
                setGolpeado(false);
                return new ImageIcon("Imagens Do Projecto\\Axel_Golpeado_Esquerda\\axel_thrown.png");
            }
            if (isSoco())
                return imgAtaqueBracoEsquerda.get((posicaoImagemSoco++) % 3);
            if (isPontape())
                return imgAtaquePernaEsquerda.get((posicaoImagemPontape++) % 2);
            if (controladorDeSalto == true)
                return imgJumpEsquerda.get(posicaoImagemJump);
            return imgAndarEsquerda.get(posicaoImagemMovimentoEsquerda);
        }
    }
}