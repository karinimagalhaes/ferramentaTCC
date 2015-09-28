/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.io.*;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private int qtdeLinhas = 0;
    private int falharam = 0;
    private int passaram = 0;
    private Hashtable infJxr = new Hashtable();
    private Document document;
    private TreeMap<Integer, String> inf = new TreeMap();

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

    public void escreveTxt() throws IOException {   //método para pegar os nomes dos métodos declarados
        String auxLinha;
        int linhaInicial;
        char aux[] = null;
        StringBuffer sbClasse = new StringBuffer();
        StringBuffer sbMetodo = new StringBuffer();
        StringBuffer sbLinha = new StringBuffer();
        boolean controle = false;
        int temp = 0;
        Elements elements = document.getElementsByTag("tr");
        for (Element children : elements) {
            if (children.text().equals("")) {
                continue;
            }
            aux = children.getElementsByClass("numLine").text().toCharArray();

            for (int i = 0; i < aux.length; i++) {
                //System.out.println("["+aux[i]+"]");
                if (aux[i] > 48 && aux[i] < 57) {
                    sbLinha.append(aux[i]);
                }
            }
            auxLinha = sbLinha.toString();
            if (!auxLinha.equals("")) {
                qtdeLinhas = Integer.parseInt(auxLinha);
            }
            //System.out.println("["+auxLinha+"]");
            sbLinha.delete(0, sbLinha.length());
            Elements pre = children.getElementsByTag("pre");
            for (Element element : pre) {

                String tagClasse = element.getElementsByTag("span").eq(1).text();
                String tagMetodo = element.getElementsByTag("span").eq(0).text();
                //System.out.println(teste2);
                if (tagClasse.equals("class")) {        // verifica se é uma classe
                    element.select("span.keyword").remove();
                    classe = element.text().trim();
                    //System.out.println(classe.replaceAll(" ", ""));
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
                    //System.out.println(classe);
                } // verifica se é um método
                else if (tagMetodo.equals("privtate") || tagMetodo.equals("public") || tagMetodo.equals("protected")) {

                    element.select("span.keyword").remove();
                    aux = element.text().toCharArray();
                    for (int j = 0; j < aux.length; j++) {
                        if ((65 <= aux[j]) && (aux[j] <= 90) || (aux[j] >= 97) && (aux[j] <= 122) || (aux[j] == 95)) {
                            sbMetodo.append(aux[j]);
                            if ((aux[j + 1] == ' ') || (aux[j + 1] == '(')) {
                                // System.out.println("entrei");
                                for (int k = j++; k < aux.length; k++) {
                                    aux[k] = ' ';
                                }
                            }
                        }

                    }
                    linhaInicial = Integer.parseInt(auxLinha);
                    inf.put(linhaInicial, sbMetodo.toString().replaceAll("\r", "").replaceAll("\t", "").replaceAll("\n", ""));
                    sbMetodo.delete(0, sbMetodo.length());

                }
            }

        }

    }

    public void falharam(int linha, Jxr jxr, Junit junit) {
        passaram = 0;
        falharam = 0;
        ArrayList<Integer> arrayLinhas = new ArrayList<Integer>();
        DadosTeste dadosTeste;
        // verifica em qual método do codigo a linha é chamada
        if (inf.containsKey(linha)) {
            metodo = inf.get(linha);       // retorna o metodo da linha
        } else {
            Set<Integer> linhas = inf.keySet();
            Iterator<Integer> linhasIterator = linhas.iterator();
            while (linhasIterator.hasNext()) {
                arrayLinhas.add(linhasIterator.next());
            }
            for (int i = 0; i < arrayLinhas.size() - 1; i++) {
                // System.out.println(linha +"->"+arrayLinhas.get(i));
                int j = i + 1;
                if (linha > arrayLinhas.get(i) && linha < arrayLinhas.get(j)) {
                    metodo = inf.get(arrayLinhas.get(i));
                    break;
                }
                if ((linha > arrayLinhas.get(i)) && (linha < qtdeLinhas) && (i == arrayLinhas.size() - 1)) {
                    metodo = inf.get(arrayLinhas.get(i));
                }
            }
            // System.out.println("metodo: " + metodo);
        }

        // verifica qual/quais são os casos de teste que chamam este método
        /*verificar se a classe do objeto é a mesma do código
         se sim -> verificar quais métodos de teste o metodo co código eh chamado
         */
        infJxr = jxr.getInf();
        for (Enumeration n = infJxr.keys(); n.hasMoreElements();) {
            dadosTeste = (DadosTeste) infJxr.get(n.nextElement());
            if (classe.equals(dadosTeste.getClasse())) { // verifica a classe do objeto
                //System.out.println(metodo);
                if (dadosTeste.getmChamados().contains(metodo)) {
                    metodoTeste.add(dadosTeste.getMetodoTeste());  // armazena metodo de teste que chama o metodo do codigo
                    
                }
            }
        }
        
        /*
         verifica se o teste passou ou falhou
         */
        for (int i = 0; i < metodoTeste.size(); i++) {
            //System.out.println(junit.getTestesFalhos().get(i));
            //System.out.println(metodoTeste.get(i));
            if (junit.getTestesFalhos().contains(metodoTeste.get(i))) {
                falharam++;
            }
        }
        
        passaram = abs(metodoTeste.size() - falharam);
        metodoTeste.clear();
        
        //System.out.println("[" +qtdeLinhas +"]" + "[" + metodo + "]" + "[" + metodoTeste + "]" + "[" + falharam + "]" + "[" + passaram + "]");
    }

    public ArrayList<Integer> qtdeLinhasCod() {
        char[] aux;
        StringBuffer sbLinha = new StringBuffer();
        Elements elements = document.getElementsByTag("tr");

        for (Element children : elements) {
            System.out.println();
            if (!children.select("span.keyword").eq(1).text().equals("class")) {
                aux = children.getElementsByClass("numLineCover").text().toCharArray();

                for (int i = 0; i < aux.length; i++) {
                    //System.out.println("["+aux[i]+"]");
                    if (aux[i] > 48 && aux[i] < 57) {
                        sbLinha.append(aux[i]);
                    }
                }
                if (!sbLinha.toString().equals("")) {
                    linhasCod.add(Integer.parseInt(sbLinha.toString()));
                }
                sbLinha.delete(0, sbLinha.length());
            }

        }
       // System.out.println(linhasCod);

        return linhasCod;
    }
}
