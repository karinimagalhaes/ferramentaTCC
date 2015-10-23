/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faultlocalization;

import static java.lang.Math.sqrt;

/**
 *
 * @author Karini
 */
public class FaultLocalization {

    /**
     * @param args the command line arguments
     */
    
    
    /*
        sucesso é o numero de testes de sucesso que passaram na linha x
        falha é o numero de testes que falharam que passaram na linha x
        totFalha é o numero de testes que falharam
        totSucesso é o numero de testes que passaram
        prob é a probabilidade da linha x possuir o defeito
        */
    private double sucesso;
    private double falha;
    private double totSucesso;
    private double totFalha;
    
    public FaultLocalization(double sucesso, double falha, double totSucesso, double totFalha){
        this.sucesso = sucesso;
        this.falha = falha;
        this.totFalha = totFalha;
        this.totSucesso = totSucesso;
    }
  
    public double tarantula(){
        
        double prob = 0.0;
        if((falha==0 && totFalha==0) || (sucesso==0 && totSucesso==0)){
            prob=0.0;
        }
        else {
            prob = (falha/totFalha)/((sucesso/totSucesso)+(falha/totFalha));
        }
        return prob;
    }
    
    public double jaccard(){
        /*
        sucesso é o numero de testes de sucesso que passaram na linha x
        falha é o numero de testes que falharam que passaram na linha x
        totFalha é o numero de testes que falharam
        prob é a probabilidade da linha x possuir o defeito
        */
        
        double prob = 0.0;
        prob = falha/(totFalha + sucesso);
        return prob;
    }
    
    public double ochiai(){
        double prob = 0.0;
        if(totFalha==0){
            prob=0.0;
        }
        else{
            prob = falha/sqrt(totFalha * (falha + sucesso));
        }
        return prob;
    }
    
    public double sbi(){
        /*
        sucesso é o numero de testes de sucesso que passaram na linha x
        falha é o numero de testes que falharam que passaram na linha x
        prob é a probabilidade da linha x possuir o defeito
        */
        
        double prob = 0.0;
        prob = falha/(sucesso + falha);
        return prob;
    }
    
}
