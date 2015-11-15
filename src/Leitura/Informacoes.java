/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leitura;

/**
 *
 * @author Karini
 */
public class Informacoes {
     private String classe;
    private String metodo;
    private int linha;

    public Informacoes(String classe, String metodo, int linha) {
        this.classe = classe;
        this.metodo = metodo;
        this.linha = linha;
    }
    
    
    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }
   
    
}
