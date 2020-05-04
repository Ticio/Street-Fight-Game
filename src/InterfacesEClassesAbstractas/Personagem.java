/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfacesEClassesAbstractas;

import java.awt.Graphics;

/**
 *
 * @author Tício
 */
public abstract class Personagem {
    private int largura;
    private int altura;
    private int vidas;
    private int x, y;
    private int golpesRecebidos;
    private int numeroDeGolpesPorVida;
    int power;

    public Personagem(int vidas, int x, int y, int power, int largura, int altura, int numeroDeGolpesPorVida) {
        this.vidas = vidas;
        this.x = x;
        this.y = y;
        this.power = power;
        this.altura = altura;
        this.largura = largura;
        this.golpesRecebidos = 0;
        this.numeroDeGolpesPorVida = numeroDeGolpesPorVida;
    }

    public int getGolpesRecebidos() {
        return golpesRecebidos;
    }

    public void setGolpesRecebidos(int golpesRecebidos) {
        this.golpesRecebidos = golpesRecebidos;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getNumeroDeGolpesPorVida() {
        return numeroDeGolpesPorVida;
    }

    public void setNumeroDeGolpesPorVida(int numeroDeGolpesPorVida) {
        this.numeroDeGolpesPorVida = numeroDeGolpesPorVida;
    }
    
    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
    
    public abstract void draw (Graphics g);    
}