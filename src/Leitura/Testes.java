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
public class Testes {
    private String classeTeste;
    private int totalTeste;
    private int totalFalha;
    private int totalSucesso;
    
    public Testes(String classeTeste, int totalTeste, int totalFalha, int totalSucesso){
        this.classeTeste = classeTeste;
        this.totalTeste = totalTeste;
        this.totalFalha = totalFalha;
        this.totalSucesso = totalSucesso;
    }

    public String getClasseTeste() {
        return classeTeste;
    }

    public int getTotalTeste() {
        return totalTeste;
    }

    public int getTotalFalha() {
        return totalFalha;
    }

    public int getTotalSucesso() {
        return totalSucesso;
    }

    public void setClasseTeste(String classeTeste) {
        this.classeTeste = classeTeste;
    }

    public void setTotalTeste(int totalTeste) {
        this.totalTeste = totalTeste;
    }

    public void setTotalFalha(int totalFalha) {
        this.totalFalha = totalFalha;
    }

    public void setTotalSucesso(int totalSucesso) {
        this.totalSucesso = totalSucesso;
    }
    
    
}
