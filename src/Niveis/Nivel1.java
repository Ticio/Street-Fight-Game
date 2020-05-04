/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Niveis;

import InterfacesEClassesAbstractas.Nivel;
import Controlo.Constantes;
import Entidades.Inimigo;
import Entidades.ObjectoSobDesce;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Tício
 */
public class Nivel1 extends Nivel{
    
    int xSobeDesce, ySobeDesce;
    private ArrayList<Integer> arrayYSobEDesce;
    private ArrayList<ObjectoSobDesce> objectosSobEDesce ;
    private int dx;
    
    int levelDoJogo;
    
    public Nivel1(int largura_do_nivel, ArrayList<Shape> shapes, ArrayList<String> designacaoDaShape) {
        super(largura_do_nivel, shapes, designacaoDaShape);
        setDX(46);
        setLevelDoJogo(1);
    }
    
    public Nivel1() {
        setLargura_do_nivel(Constantes.LARGURA);
    }
    
    //////////////////////////////////////////////////////////////////
    //Vector muito importante para controle dos arrays sob e desce
    public void setVectorYSobEDesce (ArrayList<Integer> lista)
    {
        arrayYSobEDesce = lista;
    }
    
    public ArrayList<Integer> getVectorYSobEDesce ()
    {
        return arrayYSobEDesce;
    }

    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////
    public int getLevelDoJogo() {
        return levelDoJogo;
    }

    public void setLevelDoJogo(int levelDoJogo) {
        this.levelDoJogo = levelDoJogo;
    }

    
    
    public int getxSobeDesce()   {
        return xSobeDesce;
    }

    public void setxSobeDesce(int xSobeDesce) {
        this.xSobeDesce = xSobeDesce;
    }

    public int getySobeDesce() {
        return ySobeDesce;
    }

    public void setySobeDesce(int ySobeDesce) {
        this.ySobeDesce = ySobeDesce;
    }

    public ArrayList<ObjectoSobDesce> getObjectosSobEDesce() {
        return objectosSobEDesce;
    }

    public void setObjectosSobEDesce(ArrayList<ObjectoSobDesce> objectosSobEDesce) {
        this.objectosSobEDesce = objectosSobEDesce;
    }

    
    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }
    
    
    
    @Override
    public void criarBases ()
    {
        //Atribuição das shapes naqual o movimento do individuo pode estar em dependencia delas 
        //Ex: Barreiras, Buracos, Outras coisas
        ArrayList<Shape> shapes = new ArrayList();
        ArrayList<String> designacaoDaShape = new ArrayList();
        
        shapes.add(new Rectangle2D.Float(0 - getDX(), -10, 25, (int)(Constantes.ALTURA * 0.60) + 12+10));
        designacaoDaShape.add("Base Solo");
        //Desenho da Base
        //Y = 160 , Altura = 160
        shapes.add(new Rectangle2D.Float(0 - getDX(), (int) (Constantes.ALTURA -  Constantes.ALTURA * 0.40) - 2, 100, (int)(Constantes.ALTURA * 0.40) + 2+10));
        designacaoDaShape.add("Base Solo");
        
        shapes.add(new Rectangle2D.Float(90 - getDX(), (int) (Constantes.ALTURA -  Constantes.ALTURA * 0.25) + 8, 265, (int)(Constantes.ALTURA * 0.25) - 8+10));
        designacaoDaShape.add("Base Solo");
        
        shapes.add(new Rectangle2D.Float(350 - getDX(), (int) (Constantes.ALTURA -  Constantes.ALTURA * 0.30) , 55, (float) (Constantes.ALTURA * 0.30)+10));
        designacaoDaShape.add("Base Solo");
        
        shapes.add(new Rectangle2D.Float(400 - getDX(), (int) (Constantes.ALTURA -  Constantes.ALTURA * 0.40) , 200, (float) (Constantes.ALTURA * 0.40)+10));
        designacaoDaShape.add("Base Solo");
        
        //Base de Baixo
        shapes.add(new Rectangle2D.Float(595 - getDX(), (int) (Constantes.ALTURA -  Constantes.ALTURA * 0.30) , 1005, (float) (Constantes.ALTURA * 0.30)+10));
        designacaoDaShape.add("Base Solo");
        
        shapes.add(new Rectangle2D.Float(1410 - getDX(), -10, 100, (int)(Constantes.ALTURA) + 12+10));
        designacaoDaShape.add("Barreira Final");
        
        //Base de Cima
        shapes.add(new Rectangle2D.Float(700 - getDX(), (int) (Constantes.ALTURA -  Constantes.ALTURA * 0.50) , 55, (float) (Constantes.ALTURA * 0.05)));
        designacaoDaShape.add("Base de Cima");
        
        //Base de Cima
        shapes.add(new Rectangle2D.Float(750 - getDX(), (int) (Constantes.ALTURA -  Constantes.ALTURA * 0.60) , 70, (float) (Constantes.ALTURA * 0.15)));
        designacaoDaShape.add("Base de Cima");
        
        //Base de Cima
        shapes.add(new Rectangle2D.Float(800 - getDX(), (int) (Constantes.ALTURA -  Constantes.ALTURA * 0.65) - 5, 20, (float) (Constantes.ALTURA * 0.05) + 10));
        designacaoDaShape.add("Base de Cima");
        
        //Base de Cima
        shapes.add(new Rectangle2D.Float(800 - getDX(), (int) (Constantes.ALTURA -  Constantes.ALTURA * 0.70) , 250, (float) (Constantes.ALTURA * 0.050) + 5));
        designacaoDaShape.add("Base de Cima");
        
        this.setShapes(shapes);
        this.setDesignacaoDaShape(designacaoDaShape);
    }
    
    public void criarObjectosSobEDesce ()
    {
        ArrayList<ObjectoSobDesce> sobeEDesce = new ArrayList<>();
        
        sobeEDesce.add(0, new ObjectoSobDesce(185 - getDX(), arrayYSobEDesce.get(0),90, 90, 0, (int) (Constantes.ALTURA -  Constantes.ALTURA * 0.25)));
        
        sobeEDesce.add(1, new ObjectoSobDesce(1150- getDX(), arrayYSobEDesce.get(1),90, 90, 0, (int) (Constantes.ALTURA -  Constantes.ALTURA * 0.35)));
        
        if (levelDoJogo > 1)
            sobeEDesce.add(2, new ObjectoSobDesce(450 - getDX(), arrayYSobEDesce.get(2),90, 70, 0, (int) (Constantes.ALTURA -  Constantes.ALTURA * 0.25)));
            setObjectosSobEDesce(sobeEDesce);
        if (levelDoJogo > 2)
            sobeEDesce.add(new ObjectoSobDesce(600 - getDX(), arrayYSobEDesce.get(3),90, 70,
            0, (int) (Constantes.ALTURA -  Constantes.ALTURA * 0.25)));
        if (levelDoJogo > 3)
            sobeEDesce.add(new ObjectoSobDesce(1300 - getDX(), arrayYSobEDesce.get(4),110, 110,
            0, (int) (Constantes.ALTURA -  Constantes.ALTURA * 0.25)));
        setObjectosSobEDesce(sobeEDesce);
    }
    
    @Override
    public void draw (Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        
        Area area = new Area ();
        
        criarBases ();
        criarObjectosSobEDesce();
        
        ArrayList<Shape> shapes = getShapes();
        ArrayList<ObjectoSobDesce> objectosSobeDesce = getObjectosSobEDesce();
        
        BufferedImage bImage = null;
        
        try {
            bImage = ImageIO.read(new File("C:\\Users\\Tício\\Documents\\Ucan 2016\\1 Semestre\\Computação Gráfica\\Computacao Grafica Projectos\\1\\Defesas EI3A frequência\\Garcia Roberto paulo, Nº 12103, Defesa frequencia\\Garcia Roberto paulo, Nº 12103, Defesa frequencia\\ProjetoCGrafica\\tijolo.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
        }
        //g2d.setColor(new Color(10, 10, 10));
        g2d.setColor(new Color(70, 70, 70));
        //g2d.setColor(new Color(220, 220, 220));
        for(int i = 0; i < shapes.size(); ++i)
            area.add(new Area(shapes.get(i)));
        
        g2d.draw(area);
        g2d.setColor(new Color(10, 10, 10));
        for(int i = 0; i < objectosSobEDesce.size(); ++i)
            objectosSobEDesce.get(i).draw(g);
    }
    
    @Override
    public ArrayList<Inimigo> disponibilizarInimigosDoNivel ()
    {
        ArrayList inimigosDoNivel = new ArrayList ();
        
        if (levelDoJogo == 1)
        {
            inimigosDoNivel.add(new Inimigo(this, 550, 400, 1, 500, 172, levelDoJogo*5));
            inimigosDoNivel.add(new Inimigo(this, 800, 595, 5, 600, 212, levelDoJogo*5));
            inimigosDoNivel.add(new Inimigo(this, 1250, 800, 5, 900, 212, levelDoJogo*5));
            inimigosDoNivel.add(new Inimigo(this, 1000, 800, 5, 900, 52, levelDoJogo*5));
        }
        if (levelDoJogo >= 2)
        {
            inimigosDoNivel.add(new Inimigo(this, 800, 595, 6, 700, 212, levelDoJogo*5));
            inimigosDoNivel.add(new Inimigo(this, 1250, 800, 6, 950, 212, levelDoJogo*5));
            inimigosDoNivel.add(new Inimigo(this, 1000, 800, 6, 943, 52, levelDoJogo*5));
        }
        if (levelDoJogo >= 3)
        {
            inimigosDoNivel.add(new Inimigo(this, 1050, 800, 7, 1000, 212, levelDoJogo*8));
            inimigosDoNivel.add(new Inimigo(this, 1250, 800, 7, 800, 212, levelDoJogo*8));
            inimigosDoNivel.add(new Inimigo(this, 1250, 800, 7, 950, 212, levelDoJogo*5));
        }
        if (levelDoJogo >= 4)
        {
            inimigosDoNivel.add(new Inimigo(this, 1050, 800, 9, 953, 212, levelDoJogo*8));
            inimigosDoNivel.add(new Inimigo(this, 1250, 800, 9, 750, 212, levelDoJogo*8));
        }
        if (levelDoJogo >= 5)
        {
            inimigosDoNivel.add(new Inimigo(this, 1250, 800, 10, 941, 212, levelDoJogo*9));
            inimigosDoNivel.add(new Inimigo(this, 1050, 800, 10, 1100, 212, levelDoJogo*9));
        }
        return inimigosDoNivel;
    }
    
    
    class AnimacaoMovimentarFundoAEsquerda extends Thread
    {
        
        @Override
        public void run ()
        {
            int xActual = getDX();
            while (true)
            {
                setDX(getDX() + 1);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (xActual + 100 == getDX())
                    try {
                        this.join();
               } catch (InterruptedException ex) {
                   Logger.getLogger(Nivel1.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
        }
    }
    
    public ObjectoSobDesce getObjectoSobDesce (int index)
    {
        return objectosSobEDesce.get(index);
    }
    
    public ArrayList getObjectoSobDesce ()
    {
        return objectosSobEDesce;
    }
    
    public void limparArray ()
    {
        objectosSobEDesce.clear();
        getShapes().clear();
    }
}