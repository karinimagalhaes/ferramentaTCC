/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faultlocalization;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Karini
 */
public class Resultado {

    private String classe;
    private int linha;
    private Double probabilidade;
    private String heuristica;
    
     public Resultado(String classe, int linha, double probabilidade, String heuristica) {
        this.classe = classe;
        this.linha = linha;
        this.probabilidade = probabilidade;
        this.heuristica = heuristica;
    }
    
    public String getClasse() {
        return classe;
    }

    public int getLinha() {
        return linha;
    }

    public double getProbabilidade() {
        return probabilidade;
    }
    
    public String getHeuristica(){
        return heuristica;
    }
     

    
}
