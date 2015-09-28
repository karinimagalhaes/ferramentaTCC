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
public class DadosEcobertura {
    private int linhaInicial;
    private int linhaFinal;
    private String metodo;

    public DadosEcobertura( int linhaFinal, String metodo) {
        //this.linhaInicial = linhaInicial;
        this.linhaFinal = linhaFinal;
        this.metodo = metodo;
    }

    public int getLinhaInicial() {
        return linhaInicial;
    }

    public int getLinhaFinal() {
        return linhaFinal;
    }

    public String getMetodo() {
        return metodo;
    }
}
