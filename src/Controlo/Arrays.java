/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlo;

import java.util.ArrayList;
import javax.swing.ImageIcon;
/**
 *
 * @author Tício
 */
public class Arrays {

    ////////////////////////////////////////////////////////////
    ////////// ARRAYS DE IMGS DIRECIONADAS A DIREITA ///////////
    ////////////////////////////////////////////////////////////
    public static ArrayList preencherAxel_AndarDireita ()
    {
        ArrayList axel = new ArrayList();
        for (int i = 0; i < 6; ++i)
            axel.add(i, new ImageIcon("Imagens Do Projecto\\Walk_Right\\axel_walk"+ (i+1)+".png"));
        return axel;
    }
    
    public static ArrayList preencherAxel_Direita_Esmagado()
    { 
        ArrayList axel = new ArrayList();
        
        axel.add(0, new ImageIcon("Imagens Do Projecto\\Esmago_Right\\axel_throw3.png"));
        axel.add(1, new ImageIcon("Imagens Do Projecto\\Esmago_Right\\axel_throw4.png"));
        axel.add(2, new ImageIcon("Imagens Do Projecto\\Esmago_Right\\axel_get_up1.png"));
        return axel;
    }
    
    public static ArrayList preencherAxel_Direita_AtaqueBraco()
    {
        ArrayList axel = new ArrayList();
        axel.add(0, new ImageIcon("Imagens Do Projecto\\Ataque_Right_Braco\\axel_punch.png"));
        axel.add(1, new ImageIcon("Imagens Do Projecto\\Ataque_Right_Braco\\axel_punch_combo1.png"));
        axel.add(2, new ImageIcon("Imagens Do Projecto\\Ataque_Right_Braco\\axel_punch_combo2.png"));
        return axel;
    }
    
    public static ArrayList preencherAxel_Direita_AtaquePerna()
    { 
        ArrayList axel = new ArrayList();
        axel.add(0, new ImageIcon("Imagens Do Projecto\\Ataque_Right_Perna\\axel_kick3.png"));
        axel.add(1, new ImageIcon("Imagens Do Projecto\\Ataque_Right_Perna\\axel_kick4.png"));
        return axel;
    }
    
    public static ArrayList preencherAxel_Direita_Jump()
    { 
        ArrayList axel = new ArrayList();
        axel.add(0, new ImageIcon("Imagens Do Projecto\\Axel_Jump_Right\\axel_jump3.png"));
        axel.add(1, new ImageIcon("Imagens Do Projecto\\Axel_Jump_Right\\axel_jump2.png"));
        return axel;
    }
    
    public static ArrayList preencherAxel_Direita_AtaqueAmazing()
    { 
        ArrayList axel = new ArrayList();
        for (int i = 0; i < 6; ++i)
            axel.add(i, new ImageIcon("Imagens Do Projecto\\Ataque_Amazing\\axel_rush"+ (i+1)+".png"));
        return axel;
    }
    
    ////////////////////////////////////////////////////////////
    ////////// ARRAYS DE IMGS DIRECIONADAS A ESQUERDA //////////
    ////////////////////////////////////////////////////////////
    public static ArrayList preencherAxel_AndarEsquerda()
    {
        ArrayList axel = new ArrayList();
        for (int i = 0; i < 6; ++i)
            axel.add(i, new ImageIcon("Imagens Do Projecto\\Walk_Left\\axel_walk"+ (i+1)+"_Left.png"));
        return axel;
    }
    
    public static ArrayList preencherAxel_Esquerda_Esmagado()
    { 
        ArrayList axel = new ArrayList();
        
        axel.add(0, new ImageIcon("Imagens Do Projecto\\Esmago_Left\\axel_throw3.png"));
        axel.add(1, new ImageIcon("Imagens Do Projecto\\Esmago_Left\\axel_throw4.png"));
        axel.add(2, new ImageIcon("Imagens Do Projecto\\Esmago_Left\\axel_get_up1.png"));
        return axel;
    }
    
    public static ArrayList preencherAxel_Esquerda_AtaqueBraco()
    {
        ArrayList axel = new ArrayList();
        axel.add(0, new ImageIcon("Imagens Do Projecto\\Ataque_Left_Braco\\axel_punch.png"));
        axel.add(1, new ImageIcon("Imagens Do Projecto\\Ataque_Left_Braco\\axel_punch_combo1.png"));
        axel.add(2, new ImageIcon("Imagens Do Projecto\\Ataque_Left_Braco\\axel_punch_combo2.png"));
        return axel;
    }
    
    public static ArrayList preencherAxel_Esquerda_AtaquePerna()
    { 
        ArrayList axel = new ArrayList();
        axel.add(0, new ImageIcon("Imagens Do Projecto\\Ataque_Left_Perna\\axel_kick3.png"));
        axel.add(1, new ImageIcon("Imagens Do Projecto\\Ataque_Left_Perna\\axel_kick4.png"));
        return axel;
    }
    
    
    public static ArrayList preencherAxel_Esquerda_Jump()
    { 
        ArrayList axel = new ArrayList();
        axel.add(0, new ImageIcon("Imagens Do Projecto\\Axel_Jump_Left\\axel_jump3.png"));
        axel.add(1, new ImageIcon("Imagens Do Projecto\\Axel_Jump_Left\\axel_jump2.png"));
        return axel;
    }
    
    public static ArrayList preencherAxel_Esquerda_AtaqueAmazing()
    { 
        ArrayList axel = new ArrayList();
        for (int i = 0; i < 6; ++i)
            axel.add(i, new ImageIcon("Imagens Do Projecto\\Ataque_Amazing\\axel_rush"+ (i+1)+".png"));
        return axel;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////PARTE DAS IMAGENS DO GALSIA- INIMIGO //////////////////////
    public static ArrayList preencherGalsia_AndarEsquerda ()
    {
        ArrayList axel = new ArrayList();
        for (int i = 0; i < 3; ++i)
            axel.add(i, new ImageIcon("Imagens Do Projecto\\Walk_Left_Galsia\\galsia_walk"+ (i+1) +".png"));
        return axel;
    }
    
    public static ArrayList preencherGalsia_AndarDireita()
    {
        ArrayList axel = new ArrayList();
        for (int i = 0; i < 3; ++i)
            axel.add(i, new ImageIcon("Imagens Do Projecto\\Walk_Right_Galsia\\galsia_walk"+ (i+1) +".png"));
        return axel;
    }
    
    public static ArrayList preencherGalsia_Esmagado()
    {
        ArrayList axel = new ArrayList();
        
        axel.add(0, new ImageIcon("Imagens Do Projecto\\Esmago_Galsia\\galsia_fall.png"));
        axel.add(1, new ImageIcon("Imagens Do Projecto\\Esmago_Galsia\\galsia_getup1.png"));
        axel.add(2, new ImageIcon("Imagens Do Projecto\\Esmago_Galsia\\galsia_pain.png"));
        return axel;
    }
    
    public static ArrayList preencherGalsia_AtaqueDireita()
    {
        ArrayList axel = new ArrayList();
        
        for (int i = 0; i < 3; ++i)
            axel.add(i, new ImageIcon("Imagens Do Projecto\\Ataque_Galsia_Right\\galsia_knife"+ (i+1) +".png"));
        return axel;
    }
    
    public static ArrayList preencherGalsia_AtaqueEsquerda()
    {
        ArrayList axel = new ArrayList();
        
        for (int i = 0; i < 3; ++i)
            axel.add(i, new ImageIcon("Imagens Do Projecto\\Ataque_Galsia_Left\\galsia_knife"+ (i+1) +".png"));
        return axel;
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////// ARRAYS PARA OS OBJECTOS SOB E DESCE /////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
    
    public static ArrayList<Integer> yObjectosSobDesceNivel1 (int level)
    {
        ArrayList indiceDosSobeEDesce = new ArrayList();
        indiceDosSobeEDesce.add(1);
        indiceDosSobeEDesce.add(1);
        
        System.out.println("Sob e desce Array: " + level);
        
        if (level > 1)
            indiceDosSobeEDesce.add(1);
        if (level > 2)
            indiceDosSobeEDesce.add(1);
        if (level > 3)
            indiceDosSobeEDesce.add(1);
        return indiceDosSobeEDesce;
    }
    
    public static ArrayList<Boolean> alternarObjectosSobDesceNivel1 (int level)
    {
        ArrayList alternarSobeEDesce = new ArrayList();
        alternarSobeEDesce.add(false);
        alternarSobeEDesce.add(false);
        
        if (level > 1)
            alternarSobeEDesce.add(false);
        if (level > 2)
            alternarSobeEDesce.add(false);
        if (level > 3)
            alternarSobeEDesce.add(false);
        return alternarSobeEDesce;
    }
    
    public static ArrayList<Integer> limitInfObjectosSobDesceNivel1 (int level)
    {
        ArrayList limitesInferiores = new ArrayList();
        //Primeiro
        limitesInferiores.add(310);
        
        //Penultimo
        limitesInferiores.add(290);
        
        if (level > 1)
            limitesInferiores.add(270);
        if (level > 2)
            limitesInferiores.add(300);
        if (level > 3)
            limitesInferiores.add(270);
        
        return limitesInferiores;
    }
    
    public static ArrayList<Integer> limitSuperObjectosSobDesceNivel1 (int level)
    {
        ArrayList limitesSuperiores = new ArrayList();
        limitesSuperiores.add(0);
        limitesSuperiores.add(0);
        
        if (level > 1)
            limitesSuperiores.add(0);
        if (level > 2)
            limitesSuperiores.add(0);
        if (level > 3)
            limitesSuperiores.add(0);
        return limitesSuperiores;
    }
}