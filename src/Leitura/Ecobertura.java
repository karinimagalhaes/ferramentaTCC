/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leitura;

import java.io.*;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Karini
 */
public class Ecobertura {

    private String classe;
    private String metodo = null;       // metodo do código chamado
    private ArrayList<String> metodoTeste = new ArrayList<String>();  // metodo de teste chamado
    private ArrayList<Integer> linhasCod = new ArrayList<Integer>();
    private ArrayList<Integer> excluiLinhas = new ArrayList<Integer>();
    private ArrayList<Informacoes> inf = new ArrayList<>();
    private int qtdeLinhas = 0;
    private int falharam = 0;
    private int passaram = 0;
    private Document document;
    Informacoes informacoes;
    private boolean linhaControl = false;

    public ArrayList<Informacoes> getInf() {
        return inf;
    }

    public boolean isLinhaControl() {
        return linhaControl;
    }

    public int getQtdeLinhas() {
        return qtdeLinhas;
    }

    public int getFalharam() {
        return falharam;
    }

    public double getPassaram() {
        return passaram;
    }

    public Ecobertura(Document document) {
        this.document = document;
    }

    public String getClasse() {
        return classe;
    }

    public boolean cobertura() {
        Elements elements = document.getElementsByAttribute("align");
        for (Element perc : elements) {
            if (!perc.text().equals("0%") && !perc.text().equals("N/A")) {
                return true;
            }
        }
        return false;
    }

    public void escreveTxt() throws IOException {   //método para pegar os nomes dos métodos declarados
        String auxLinha = null;
        char aux[] = null;
        StringBuffer sbClasse = new StringBuffer();
        StringBuffer sbLinha = new StringBuffer();
        StringBuffer sbMetodo = new StringBuffer();
        String metodoTemp;
        boolean controleClasse = false;

        // Pega somente os elementos com tag "tr"
        Elements elements = document.getElementsByTag("tr");
        for (Element children : elements) {
            if (StringUtils.isBlank(children.text())) {
                continue;
            }
            children.getElementsByClass("comment").remove();
                // System.out.println(children.text());
            //----------------- Dispensa Comentários -----------------
            //auxLinha = children.getElementsByTag("span").eq(0).text();
            /*if (auxLinha.contains("/*")) {
             comentario = true;
             } else if(auxLinha.contains("//")){
             comentario = true;
             controle = true;            // controla comentário com //
             }
                
             if (auxLinha.contains("*//*")) {
             comentario = false;
             }else if(auxLinha.contains("\n") && controle == true){
             comentario = false;
             controle = false;
             }*/

            //------------------ Fim dispensa comentários --------------

            // if (comentario == false) {
            //--------------------- verifica as linhas do código -------------------
            if (StringUtils.isNotBlank(children.getElementsByClass("numLine").text())) {
                aux = children.getElementsByClass("numLine").text().toCharArray();

                for (int i = 0; i < aux.length; i++) {
                    //System.out.println("["+aux[i]+"]");
                    if (aux[i] >= 48 && aux[i] <= 57) { // pega o número da linha
                        sbLinha.append(aux[i]);
                    }
                }
                auxLinha = sbLinha.toString();
                if (StringUtils.isNotBlank(auxLinha)) {     // transforma a linha para inteiro
                    qtdeLinhas = Integer.parseInt(auxLinha);
                }

                sbLinha.delete(0, sbLinha.length());
            }

            // ------------------- Fim linhas  ---------------------------------
            Elements pre = children.getElementsByTag("pre");
            for (Element element : pre) {
                String tagMetodo = element.getElementsByTag("span").eq(0).text();

                //------------------------- Verifica classe -------------------------
                if (element.getElementsByTag("span").text().contains("class")) {
                    element.select("span.keyword").remove();
                    if (controleClasse == false) {
                        classe = element.text().trim();
                        aux = classe.toCharArray();

                        for (int j = 0; j < aux.length; j++) {
                            if ((65 <= aux[j]) && (aux[j] <= 90) || (aux[j] >= 97) && (aux[j] <= 122) || (aux[j] == 95)) {
                                sbClasse.append(aux[j]);
                                //System.out.println(j + ", " + sbClasse);
                                if (j < aux.length - 1) {
                                    // System.out.println("size: "+aux.length+" j: "+j);
                                    if ((aux[j + 1] == ' ') || (aux[j + 1] == '{')|| (aux[j + 1] == '<')) {
                                        // System.out.println("entrei");
                                        if ((j + 1) < aux.length - 1) {
                                            for (int k = j++; k < aux.length; k++) {
                                                aux[k] = ' ';
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        excluiLinhas.add(qtdeLinhas);
                        classe = sbClasse.toString().replaceAll("\r", "").replaceAll("\t", "").replaceAll("\n", "");
                         
                        controleClasse = true;
                    }
                   //  System.out.println("Classe: " + classe);
                } //------------------------------- Fim verifica classe------------------------------
                //------------------------------ Verifica método ----------------------------------
                //else if (tagMetodo.equals("privtate") || tagMetodo.equals("public") || tagMetodo.equals("protected")) {
                else if (element.getElementsByTag("span").text().contains("privtate")
                        || element.getElementsByTag("span").text().contains("public")
                        || element.getElementsByTag("span").text().contains("protected")
                        || element.getElementsByTag("span").text().contains("static")
                        || element.getElementsByTag("span").text().contains("final")
                        || element.getElementsByTag("span").text().contains("native")
                        || element.getElementsByTag("span").text().contains("synchronized")
                        || element.getElementsByTag("span").text().contains("abstract")
                        || element.getElementsByTag("span").text().contains("threadsafe")
                        || element.getElementsByTag("span").text().contains("transient")) {
                    element.select("span.keyword").remove();
                    if(!element.text().contains("=") && !element.text().contains(".") && !element.text().contains("@")){
                        String[] s = element.text().split(" ");
                        for (int i = 0; i < s.length; i++) {
                            if (s[i].contains("(")) {
                                aux = s[i].toCharArray();
                                for (int j = 0; j < aux.length; j++) {
                                    if (aux[j] == '(') {
                                        for (int k = j; k < aux.length; k++) {
                                            aux[k] = ' ';
                                        }
                                        break;
                                    }
                                    sbMetodo.append(aux[j]);
                                }
                                metodoTemp = sbMetodo.toString();
                                if(!metodoTemp.isEmpty()){
                                    metodo = metodoTemp.replaceAll("\r", "").replaceAll("\t", "").replaceAll("\n", "");
                                    sbMetodo.delete(0, aux.length);
                                    informacoes = new Informacoes(classe, metodo, Integer.parseInt(auxLinha));
                                    inf.add(informacoes);
                                }
                            }
                        }
                    }
                }

                // --------------------------- Fim Verifica Método ------------------------------------
            }
           
            

            // }
        }
       /* for(int i=0; i<inf.size(); i++){
        System.out.println("Classe:"+inf.get(i).getClasse()+" Metodo:"+inf.get(i).getMetodo()+" Linha: "+inf.get(i).getLinha());
        }
        //

        /* for(Map.Entry<String,Informacoes> entry : inf.entrySet()) {
         String key = entry.getKey();
         int value = entry.getValue().getLinha();
         String metodov = entry.getValue().getMetodo();
         String classev = entry.getValue().getClasse();

         System.out.println(key + " => " + classev+ " => " +metodov+ " => " +value);
         }*/
    }

    public void falharam(String classeMetodo, int linha, ArrayList<Informacoes> linhaMetodo, ArrayList<DadosTeste> dadosTeste, Junit junit) {
       // System.out.println(classeMetodo);
        passaram = 0;
        falharam = 0;
        String metodoCodigo = null;
        List<Integer> arrayLinhas = new ArrayList<>();
        HashMap<Integer, Informacoes> informacoes = new HashMap<>();
            /*
             ordenar as linhas onde os metodos começam e pegar somente as linhas da classe
             */
       
            for (int i = 0; i < linhaMetodo.size(); i++) {
                if(linhaMetodo.get(i).getClasse().equals(classeMetodo)){
                    arrayLinhas.add(linhaMetodo.get(i).getLinha());
                    informacoes.put(linhaMetodo.get(i).getLinha(), linhaMetodo.get(i));
                }
            }
          // System.out.println("Classe: "+ classeMetodo+arrayLinhas);
            for(int i=0; i<arrayLinhas.size(); i++){
                
                int j = i + 1;
                if (j < arrayLinhas.size()) {
                    if (linha > arrayLinhas.get(i) && linha < arrayLinhas.get(j)) {
                        metodoCodigo = informacoes.get(arrayLinhas.get(i)).getMetodo();
                        //System.out.println(linha + " "+informacoes.get(arrayLinhas.get(i)).getMetodo());
                        verificaMetodo(classeMetodo, metodoCodigo, dadosTeste);
                        //System.out.println("linha: " + linha + " metodo: " + metodoCodigo+" classe: "+classeMetodo);
                       
                    }
                    //else if ((linha > arrayLinhas.get(i)) && (linha < qtdeLinhas)) {
                       // metodoCodigo = informacoes.get(arrayLinhas.get(i)).getMetodo();
                   // }
                } else if (linha > arrayLinhas.get(arrayLinhas.size() - 1)) {
                  
                        metodoCodigo = informacoes.get(arrayLinhas.get(i)).getMetodo();
                        //System.out.println(linha + " "+informacoes.get(arrayLinhas.get(i)).getMetodo());
                    
                    verificaMetodo(classeMetodo, metodoCodigo, dadosTeste);
                   // System.out.println("linha: " + linha + " metodo: " + metodoCodigo+" classe: "+classeMetodo);
                    
                }
                
            }
           // System.out.println("Classe: "+classe+arrayLinhas);

        
             //.out.println("Classe:"+classeMetodo+" linha: " + linha + " metodo: " + metodoTeste);
        /*
         verifica se o teste passou ou falhou
         */
        if(metodoTeste.isEmpty()){
            linhaControl = true;
        }
        else{
            for (int i = 0; i < metodoTeste.size(); i++) {
               //System.out.println(junit.getTestesFalhos() + "->" + metodoTeste.get(i));
                if (junit.getTestesFalhos().contains(metodoTeste.get(i))) {
                    falharam++;
                }
            }
            passaram = abs(metodoTeste.size() - falharam);
           /* System.out.println("Classe: ["+classe+"]"+"linha: [" +linha +"]" + " Metodo: [" + metodo + "]" + " metodo de teste[" + metodoTeste + "]" +
             " Falharam: [" + falharam + "]" + " Passaram: [" + passaram + "]");*/
            metodoTeste.clear();
        }
        linhaControl = false;

    }

    public void verificaMetodo(String classeMetodo, String metodoCodigo, ArrayList<DadosTeste> dadosTeste) {
        /*
        Verificar quais métodos de teste chamam o método do código
        */
        
        ArrayList<String> mChamado = new ArrayList<>();
         
        //pega todos os metodos de código
        for(int i=0; i<dadosTeste.size(); i++){
           // System.out.println(dadosTeste.get(i).getClasse() +"->"+classeMetodo);
            if(dadosTeste.get(i).getClasse().equals(classeMetodo)){
                mChamado = dadosTeste.get(i).getmChamado();
                for(int j=0; j<mChamado.size(); j++){
                   // System.out.println("Metodo chamado: "+mChamado.get(j)+" Metodo Codigo: "+metodoCodigo);
                    if(mChamado.get(j).equals(metodoCodigo)){
                        metodoTeste.add(dadosTeste.get(i).getMetodoTeste());
                        break;
                    }
                }
             
            }
        }
      //  System.out.println("FINAL -----> Classe metodo:"+classeMetodo+" Metodo teste:"+metodoTeste);
        
    }

//----------------------------- Retorna as linhas cobertas pelos testes ----------------------------------------
    public ArrayList<Integer> qtdeLinhasCod() {
        char[] aux;
        StringBuffer sbLinha = new StringBuffer();
        Elements elements = document.getElementsByTag("tr");
        for (Element children : elements) {
            if (StringUtils.isNotBlank(children.getElementsByClass("numLineCover").text())) {
                aux = children.getElementsByClass("numLineCover").text().toCharArray();
                for (int i = 0; i < aux.length; i++) {
                    //System.out.println("["+aux[i]+"]");
                    if (aux[i] >= 48 && aux[i] <= 57) {
                        sbLinha.append(aux[i]);         // exclui espaços na string
                    }
                }
                if (StringUtils.isNotBlank(sbLinha.toString())) {
                    linhasCod.add(Integer.parseInt(sbLinha.toString()));
                    sbLinha.delete(0, sbLinha.length());
                }
            }
        }
        return linhasCod;
    }

}
