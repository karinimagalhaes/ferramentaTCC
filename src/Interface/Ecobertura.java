/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.io.*;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
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
    private Hashtable infJxr = new Hashtable();
    private Document document;
    Informacoes informacoes;

    public ArrayList<Informacoes> getInf() {
        return inf;
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

        // Pega somente os elementos com tag "tr"
        Elements elements = document.getElementsByTag("tr");
        for (Element children : elements) {
            if (StringUtils.isBlank(children.text())) {
                continue;
            }
            children.getElementsByClass("comment").remove();
               //  System.out.println(children.text());
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
                    classe = element.text().trim();
                    aux = classe.toCharArray();
                    //for (int i = 0; i < classe.length(); i++) {
                    for (int j = 0; j < aux.length; j++) {
                        if ((65 <= aux[j]) && (aux[j] <= 90) || (aux[j] >= 97) && (aux[j] <= 122) || (aux[j] == 95)) {
                            sbClasse.append(aux[j]);
                            //System.out.println(j + ", " + sbClasse);
                            if ((aux[j + 1] == ' ') || (aux[j + 1] == '{')) {
                                // System.out.println("entrei");
                                for (int k = j++; k < aux.length; k++) {
                                    aux[k] = ' ';
                                }
                            }
                        }
                    }

                    excluiLinhas.add(qtdeLinhas);
                    classe = sbClasse.toString().replaceAll("\r", "").replaceAll("\t", "").replaceAll("\n", "");
                    // System.out.println("Classe: " + classe);
                } //------------------------------- Fim verifica classe------------------------------
                //------------------------------ Verifica método ----------------------------------
                //else if (tagMetodo.equals("privtate") || tagMetodo.equals("public") || tagMetodo.equals("protected")) {
                else if (element.getElementsByTag("span").text().contains("privtate")
                        || element.getElementsByTag("span").text().contains("public")
                        || element.getElementsByTag("span").text().contains("protected")) {
                    element.select("span.keyword").remove();
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
                            metodo = metodoTemp.replaceAll("\r", "").replaceAll("\t", "").replaceAll("\n", "");
                            sbMetodo.delete(0, aux.length);
                            informacoes = new Informacoes(classe,
                                    metodo, Integer.parseInt(auxLinha));
                            inf.add(informacoes);
                        }
                    }
                }

                // --------------------------- Fim Verifica Método ------------------------------------
            }

            // }
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

    public void falharam(String classe, int linha, TreeMap<String, Informacoes> inf, Jxr jxr, Junit junit) {
        passaram = 0;
        falharam = 0;

        ArrayList<Integer> arrayLinhas = new ArrayList<>();
        String linhaTemp = Integer.toString(linha);

        /*
         Verifica o método que cada linha de código pertence
         1º caso: verifica se a linha e a classe é chave de algum método
         */
        if (inf.containsKey(linhaTemp + classe)) {
            metodo = inf.get(linhaTemp + classe).getMetodo();       // retorna o metodo da linha
            verificaMetodo(linha, classe, jxr.getInf());
            //  System.out.println(linha +"->"+ metodo);
        } /*
         2º caso: se não a linha não for chave da treeMap passar todos as as linhas para um array
         TreeMap não avança posições e a busca não é sequencial
         */ else {
            // --------- passa a chave (linhas) para um array -------------
            for (Map.Entry<String, Informacoes> entry : inf.entrySet()) {
                if (entry.getKey().contains(classe)) {
                    arrayLinhas.add(entry.getValue().getLinha());
                }
            }
            Collections.sort(arrayLinhas);
            //System.out.println(classe+"->"+arrayLinhas);
            // ------------- termina --------------------------------------

            /*
             verifica as linhas percorrendo o array
             arrayLinhas armazena as linhas onde os métodos foram instanciados
             */
            for (int i = 0; i < arrayLinhas.size(); i++) {
                int j = i + 1;
                if (j < arrayLinhas.size()) {
                    if (linha > arrayLinhas.get(i) && linha < arrayLinhas.get(j)) {
                        linhaTemp = Integer.toString(arrayLinhas.get(i));
                        metodo = inf.get(linhaTemp + classe).getMetodo();
                        verificaMetodo(linha, classe, jxr.getInf());
                        //System.out.println(classe+"->"+linha + "->" + metodo);
                    } else if ((linha > arrayLinhas.get(i)) && (linha < qtdeLinhas) && (i == arrayLinhas.size() - 1)) {
                        linhaTemp = Integer.toString(arrayLinhas.get(i));
                        metodo = inf.get(linhaTemp + classe).getMetodo();
                        verificaMetodo(linha, classe, jxr.getInf());
                        //System.out.println(classe+"->"+linha + "->" + metodo);
                    }
                } else if (linha > arrayLinhas.get(arrayLinhas.size() - 1)) {
                    linhaTemp = Integer.toString(arrayLinhas.get(arrayLinhas.size() - 1));
                    metodo = inf.get(linhaTemp + classe).getMetodo();
                    verificaMetodo(linha, classe, jxr.getInf());
                    //System.out.println(classe+"->"+linha + "->" + metodo);
                }
            }

        }
             //System.out.println("linha: " + linha + " metodo: " + metodo);

        
       

        /*
         verifica se o teste passou ou falhou
         */
        for (int i = 0; i < metodoTeste.size(); i++) {
            //System.out.println(junit.getTestesFalhos().get(i));
            if (junit.getTestesFalhos().contains(metodoTeste.get(i))) {
                falharam++;
            }
        }

        passaram = abs(metodoTeste.size() - falharam);
        metodoTeste.clear();

        //System.out.println("[" +qtdeLinhas +"]" + "[" + metodo + "]" + "[" + metodoTeste + "]" + "[" + falharam + "]" + "[" + passaram + "]");
    }
    
    public void verificaMetodo(int linha, String classe, Hashtable infJxr){
        // verifica qual/quais são os casos de teste que chamam este método
             /*verificar se a classe do objeto é a mesma do código
         se sim -> verificar quais métodos de teste o metodo do código eh chamado
         */
        
        for (Enumeration n = infJxr.keys(); n.hasMoreElements();) {
            DadosTeste dadosTeste = (DadosTeste) infJxr.get(n.nextElement());
            if (classe.equals(dadosTeste.getClasse())) { // verifica a classe do objeto
                //System.out.println(classe+"->"+linha+"->"+dadosTeste.getmChamados()+"->"+metodo);
                for(int i=0; i<dadosTeste.getmChamado().size(); i++){
                    if (dadosTeste.getmChamado().get(i).contains(metodo)) {
                        metodoTeste.add(dadosTeste.getMetodoTeste());  // armazena metodo de teste que chama o metodo do codigo
                        //System.out.println("ENTREI: ["+linha+"]"+"["+metodo+"_"+classe+"]"+dadosTeste.getMetodoTeste());
                        break;
                    }
                }
            }
        }
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
