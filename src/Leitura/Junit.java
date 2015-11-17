/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leitura;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Karini
 */
public class Junit {

    private ArrayList<String> falhas;
    private Document document;

    public Junit(Document document) {
        this.document = document;
    }

    public ArrayList<String> getTestesFalhos() {
        return falhas;
    }

    public Map<String, Testes> totais(ArrayList<DadosTeste> dadosTeste) {   //método para pegas as informações referentes a quantidade de testes
        Map<String, Testes> totaisTestes = new TreeMap();
        int totFalhas = 0;
        int totSucesso = 0;
        int total = 0;
        String classeTeste = null;
        Elements elements = document.getElementsByClass("section");

       //System.out.println(elements);
        for (Element element : elements) {
            
            if(element.getElementsByTag("a").attr("name").equals("Package_List")){  // Identifica o espaço com as informações de falhas
                //System.out.println(element.getElementsByTag("a").attr("name"));
                Elements children = element.getElementsByClass("bodyTable"); // Pega todos os elementos "td"
                int k=0;
                for(Element table : children){ //Para cada elemento verifica se o atributo "nome" é nulo
                    k++;
                    System.out.println("---------------------------- kkkkk----------------------------\n"+k+"\n");
                    if(k!=1){
                    for (int i = 0; i < table.getElementsByTag("td").size() / 8; i++) {
                        classeTeste = table.getElementsByTag("td").eq(8 * i + 1).text();
                        total = Integer.parseInt(table.getElementsByTag("td").eq(8 * i + 2).text().trim());
                        totFalhas = Integer.parseInt(table.getElementsByTag("td").eq(8 * i + 3).text().trim())
                                + Integer.parseInt(table.getElementsByTag("td").eq(8 * i + 4).text().trim());
                        totSucesso = total - totFalhas;
                        totaisTestes.put(classeTeste, new Testes(classeTeste, total, totFalhas, totSucesso));
                   System.out.println("ClasseTeste:"+classeTeste+" Total: "+total+" Total Falhas: "+totFalhas+" TotalSuceso: "+totSucesso);

            }
                    }
                }
            }
          
        }
        return totaisTestes;
        //System.out.println(qtdeTestes + " " + qtdeFalhas + " " + qtdeSucesso);
    }

    public void testesFalhos() {
        //Elements testFailure;
        falhas = new ArrayList<String>();    // Armazena o nome dos testes que tiveram falhas
        Elements cabecalhos = document.getElementsByClass("section");   // pega os elementos da classe section

        /* Identifica os casos de teste que falharam
         Reconhecendo o padrão do relatório chegou-se a conclusão que nome dos testes que falharam é dado dentro da
         última div com classe = section.
         Dentro desta div existe uma lista de tags "td", os nomes dos testes podem ser encontrados dentro das tags
         que possuem o atributo nome diferente de nulo.
         */
        for (Element element : cabecalhos) {
            //System.out.println("a> " + element.getElementsByTag("a").attr("name").toString());
            if (element.getElementsByTag("a").attr("name").equals("Failure_Details")) {  // Identifica o espaço com as informações de falhas
                Elements children = element.getElementsByTag("td"); // Pega todos os elementos "td"
                //System.out.println(children.text());
                for (Element td : children) { //Para cada elemento verifica se o atributo "nome" é nulo
                    if (!td.getElementsByTag("a").attr("name").equals("")) {
                        falhas.add(td.getElementsByTag("td").text());   // se não for nulo armazena no ArrayList de falhas
                    }
                }
            }

            if (element.getElementsByTag("a").attr("name").equals("Errors_Details")) {   // Identifica o espaço com as informações de erros
                Elements children = element.getElementsByTag("td");
                for (Element td : children) {
                    if (!td.getElementsByTag("a").attr("name").equals("")) {
                        falhas.add(td.getElementsByTag("td").text());
                    }
                }
            }

        }
        //System.out.println("Testes falhos: " +falhas);

    }
}
