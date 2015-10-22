/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.ArrayList;

/**
 *
 * @author Karini
 */
public class DadosTeste {
    private String classe;
    private ArrayList<String> objetos = new ArrayList< String>();
    private ArrayList<String> mChamado = new ArrayList< String>();
    private String metodoTeste;

    
    public DadosTeste(String classe, ArrayList<String> objeto, ArrayList<String> mChamado, String metodoTeste) {
        this.classe = classe;
        this.objetos = objeto;
        this.mChamado = mChamado;
        this.metodoTeste = metodoTeste;
    }

    public String getMetodoTeste() {
        return metodoTeste;
    }

    public void setMetodoTeste(String metodoTeste) {
        this.metodoTeste = metodoTeste;
    }
    
    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public ArrayList<String> getObjetos() {
        return objetos;
    }

    public void setObjetos(ArrayList<String> objetos) {
        //System.out.println(this.objetos);
        this.objetos = objetos;
    }
    
    public ArrayList<String> getmChamado() {
        return mChamado;
    }
}
