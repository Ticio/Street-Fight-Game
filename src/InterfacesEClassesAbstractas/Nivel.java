/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfacesEClassesAbstractas;

import Entidades.Inimigo;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 *
 * @author Tício
 */
public abstract class Nivel {
    private int x;
    private int largura_do_nivel;
    private ArrayList<Shape> shapes;
    private ArrayList<String> designacaoDaShape;
    
    public Nivel (){}
    
    public Nivel(int largura_do_nivel, ArrayList<Shape> shapes, ArrayList<String> designacaoDaShape) {
        this.largura_do_nivel = largura_do_nivel;
        this.shapes = shapes;
        this.designacaoDaShape = designacaoDaShape;
    }
    
    public int getLargura_do_nivel() {
        return largura_do_nivel;
    }

    public void setLargura_do_nivel(int largura_do_nivel) {
        this.largura_do_nivel = largura_do_nivel;
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }
    
    public Shape getShape(int index) {
        return shapes.get(index);
    }
    
    public ArrayList<String> getDesignaçãoDaShape() {
        return designacaoDaShape;
    }

    public void setDesignacaoDaShape(ArrayList<String> designacaoDaShape) {
        this.designacaoDaShape = designacaoDaShape;
    }
    
    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }
    
    public boolean verificaInstanciaRectangle (int index)
    {
        return (getShapes().get(index) instanceof Rectangle2D);
    }
    
    public boolean verificaInstanciaEllipse (int index)
    {
        return (getShapes().get(index) instanceof Ellipse2D);
    }

    public int getDX() {
        return x;
    }

    public void setDX(int x) {
        this.x = x;
    }
    
    public abstract void criarBases ();
    
    public abstract void draw (Graphics g); 
    
    public abstract ArrayList<Inimigo> disponibilizarInimigosDoNivel ();
}