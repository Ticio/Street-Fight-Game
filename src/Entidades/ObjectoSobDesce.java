/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Tício
 */
public class ObjectoSobDesce{
    
    private int x, y, limiteSuperior, limiteInferior;
    private int altura, largura;
    
    public ObjectoSobDesce() {
    }

    public ObjectoSobDesce(int x, int y, int altura, int largura, int limiteSuperior, int limiteInferior) {
        setX(x);
        setY(y);
        setLimiteInferior(limiteInferior);
        setLimiteSuperior(limiteSuperior);
        setAltura(altura);
        setLargura(largura);
    }
    
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        AffineTransform aff = g2d.getTransform();
        
        g2d.drawRoundRect(getX(), getY(), getAltura(), getLargura(), 15, 15);
    }
    
   
    public boolean alternar ()
    {
        if (getY() + 90 >= limiteInferior)
            return true;
        else if (getY() <= limiteSuperior)
            return true;
        return false;
    }        

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }
    
    public int getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(int limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public int getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(int limiteInferior) {
        this.limiteInferior = limiteInferior;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }    
}
