/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Jogo;

import Controlo.Arrays;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Tício
 */
public class Main extends JFrame{
    
    private Jogo jogo;
    
    public Main ()
    {
        super("Luta de Rua");
        jogo = new Jogo();
        
        addKeyListener(jogo);
        addMouseMotionListener(jogo);
        add(jogo);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); 
    }
    
    public static void main (String a[])
    {
        new Main();
    }
}
