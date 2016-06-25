/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MarcosPaulo
 */
public class Main {
    
    public static void main (String[] args){
        
        EQCloud eqcloud = new EQCloud();
        String ans = eqcloud.createController("Jaiminho, o Carteiro", "jaiminho", 
                "tangamandapio", 4);
        
        System.out.println(ans);    
        
    }
}
