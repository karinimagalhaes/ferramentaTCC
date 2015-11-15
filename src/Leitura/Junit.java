/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leitura;

import java.io.*;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Karini
 */
public class Junit {
    
    private int qtdeTestes;
    private int qtdeFalhas;
    private int qtdeSucesso;
    private ArrayList<String> falhas;
    private Document document;
    
    
    public Junit(Document document){
        this.document = document;
    }
    

    public int getQtdeTestes() {
        return qtdeTestes;
    }

    public int getQtdeFalhas() {
        return qtdeFalhas;
    }

    public int getQtdeSucesso() {
        return qtdeSucesso;
    }

    public ArrayList<String> getTestesFalhos() {
        return falhas;
    }
    
    public void totais(){   //método para pegas as informações referentes a quantidade de testes
        Elements children = null;
        Elements elements = document.getElementsByClass("b");
        //System.out.println(elements.size());
        for(Element element : elements.eq(0)){
            //String table = element.getElementsByClass("b").text();
            children = element.getElementsByTag("td");
        }
        qtdeTestes = Integer.parseInt(children.get(0).text());   //Primeira Coluna do Relatório Surefire (Tests)
       // Soma da 2ª e 3ª coluna do Surefire (Error e failures)
        qtdeFalhas = Integer.parseInt(children.get(1).text()) + Integer.parseInt(children.get(2).text()); 
        qtdeSucesso = qtdeTestes - qtdeFalhas;
       //System.out.println(qtdeTestes + " " + qtdeFalhas + " " + qtdeSucesso);
    }
    
    public void testesFalhos(){
        //Elements testFailure;
        falhas = new ArrayList<String> ();    // Armazena o nome dos testes que tiveram falhas
        Elements cabecalhos = document.getElementsByClass("section");   // pega os elementos da classe section
        
        /* Identifica os casos de teste que falharam
           Reconhecendo o padrão do relatório chegou-se a conclusão que nome dos testes que falharam é dado dentro da
           última div com classe = section.
           Dentro desta div existe uma lista de tags "td", os nomes dos testes podem ser encontrados dentro das tags
           que possuem o atributo nome diferente de nulo.
        */
        for(Element element : cabecalhos){
            //System.out.println("a> " + element.getElementsByTag("a").attr("name").toString());
            if(element.getElementsByTag("a").attr("name").equals("Failure_Details")){  // Identifica o espaço com as informações de falhas
                Elements children = element.getElementsByTag("td"); // Pega todos os elementos "td"
                //System.out.println(children.text());
                for(Element td : children){ //Para cada elemento verifica se o atributo "nome" é nulo
                    if(!td.getElementsByTag("a").attr("name").equals("")){
                        falhas.add(td.getElementsByTag("td").text());   // se não for nulo armazena no ArrayList de falhas
                    }
                }
            }
            
            if(element.getElementsByTag("a").attr("name").equals( "Errors_Details")){   // Identifica o espaço com as informações de erros
                Elements children = element.getElementsByTag("td");
                for(Element td : children){
                    if(!td.getElementsByTag("a").attr("name").equals("")){
                        falhas.add(td.getElementsByTag("td").text());
                    }
                }
            }
            
        }
        //System.out.println("Testes falhos: " +falhas);
       
    }
}
