/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faultlocalization;

import Interface.Resultado;
import java.util.Comparator;

/**
 *
 * @author Karini
 */
public class ComparatorResultado implements Comparator<Resultado>{
     @Override
     public int compare(Resultado r1,Resultado r2) {  
         if(r1.getProbabilidade() < r2.getProbabilidade())
             return -1;
         if(r1.getProbabilidade() > r2.getProbabilidade())
             return 1; 
         return 0;
     }  
}
