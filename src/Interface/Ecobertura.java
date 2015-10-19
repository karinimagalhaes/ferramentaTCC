/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import com.sun.org.apache.bcel.internal.generic.GotoInstruction;
import java.io.*;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private int qtdeLinhas = 0;
    private int falharam = 0;
    private int passaram = 0;
    private Hashtable infJxr = new Hashtable();
    private Document document;
    private TreeMap<String, Informacoes> inf = new TreeMap();       // armazena a linha e o método chamado

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
                        System.out.println("Classe: " + classe);
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
                                for(int j=0; j<aux.length; j++){
                                    if(aux[j] == '('){
                                        for(int k=j; k<aux.length; k++)
                                            aux[k] = ' ';
                                        break;
                                    }
                                    sbMetodo.append(aux[j]);
                                }
                                String metodoTemp = sbMetodo.toString();
                                metodo = metodoTemp.replaceAll("\r", "").replaceAll("\t", "").replaceAll("\n", "");
                                sbMetodo.delete(0, aux.length);
                                Informacoes informacoes = new Informacoes(classe, 
                                        metodo, Integer.parseInt(auxLinha));
                                
                                inf.put(auxLinha+classe, informacoes);
                            }
                        }
                    }

                    // --------------------------- Fim Verifica Método ------------------------------------
                }

           // }
        }
       
       /* for(Map.Entry<String,Informacoes> entry : inf.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue().getLinha();
            String metodov = entry.getValue().getMetodo();
            String classev = entry.getValue().getClasse();

            System.out.println(key + " => " + classev+ " => " +metodov+ " => " +value);
}*/

    }

    public void falharam(String classe, int linha, Jxr jxr, Junit junit) {
        passaram = 0;
        falharam = 0;
         System.out.println(inf.keySet());
        ArrayList<String> arrayLinhas = new ArrayList<String>();
        DadosTeste dadosTeste;
        String linhaTemp = Integer.toString(linha);
        // verifica em qual método do codigo a linha é chamada
        
        /*if (inf.containsKey(linhaTemp+classe)) {
            metodo = inf.get(linhaTemp+classe).getMetodo();       // retorna o metodo da linha
           // System.out.println(linha +"->"+ metodo);
        } else {
           // --------- passa a chave (linhas) para um array -------------
            Set<String> linhas = inf.keySet();
            Iterator<String> linhasIterator = linhas.iterator();
            while (linhasIterator.hasNext()) {
                arrayLinhas.add(linhasIterator.next());
            }*/
            // ------------- termina --------------------------------------
            
            // --------- passa a chave (linhas) para um array -------------
            /*Set<String> linhas = inf.keySet();
            Iterator<String> linhasIterator = linhas.iterator();
            while (linhasIterator.hasNext()) {
                if(inf.containsKey(classe)){
                    
                    System.out.println(inf.keySet());
                    arrayLinhas.add(linhasIterator.next());
                }
            }
            
            
            
            // verifica as linhas percorrendo o array
           /* for (int i = 0; i < arrayLinhas.size() - 1; i++) {
                int j = i + 1;
                if (linha > arrayLinhas.get(i) && linha < arrayLinhas.get(j)) {
                    metodo = inf.get(arrayLinhas.get(i));
                    System.out.println(arrayLinhas.get(i) +"->"+ metodo);
                    break;
                }
                if ((linha > arrayLinhas.get(i)) && (linha < qtdeLinhas) && (i == arrayLinhas.size() - 1)) {
                    metodo = inf.get(arrayLinhas.get(i));
                 System.out.println(arrayLinhas.get(i) +"->"+ metodo);
                 break;
                }
            }
             
        }
        //System.out.println("linha: " + linha + " metodo: " + metodo);

        // verifica qual/quais são os casos de teste que chamam este método
        /*verificar se a classe do objeto é a mesma do código
         se sim -> verificar quais métodos de teste o metodo co código eh chamado
         */
       /* infJxr = jxr.getInf();
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
            if (junit.getTestesFalhos().contains(metodoTeste.get(i))) {
                falharam++;
            }
        }
        
        passaram = abs(metodoTeste.size() - falharam);
        metodoTeste.clear();
        
        //System.out.println("[" +qtdeLinhas +"]" + "[" + metodo + "]" + "[" + metodoTeste + "]" + "[" + falharam + "]" + "[" + passaram + "]");
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
