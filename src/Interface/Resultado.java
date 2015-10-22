/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

/**
 *
 * @author Karini
 */
public class Resultado {

    private String classe;
    private int linha;
    private double probabilidade;
    
     public Resultado(String classe, int linha, double probabilidade) {
        this.classe = classe;
        this.linha = linha;
        this.probabilidade = probabilidade;
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
    

   
    
    
}
