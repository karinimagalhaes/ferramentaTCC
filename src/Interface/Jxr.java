/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Karini
 */
public class Jxr {

    
    private ArrayList <String> object = new ArrayList<String>();
    private String metodoTeste;
    private String classeObj;
    private String objeto;
    private ArrayList<String> mChamado = new ArrayList<String>();
    private DadosTeste dadosTeste = null;
    private Document document;
    
    private ArrayList<DadosTeste> dados = new ArrayList<>();
  

    public Jxr(Document document) {
        this.document = document;
    }
    

    public String leituraJxr() throws IOException {   //método para pegar os nomes dos métodos declarados
        Elements elements = document.getElementsByTag("pre");
        elements.select("a.jxr_linenumber").remove();
       // elements.select("strong.jxr_keyword").remove();
        // elements.select("span.jxr_string").remove();
       // elements.select("em.jxr_comment").remove();
        for(Element children : elements){
            children.getElementsByClass("jxr_comment").remove();
        }
        return elements.text();     // retorna o código sem lixo
    }

    

    public ArrayList<DadosTeste> leTxt(String classe, String path, FileInputStream reader) throws IOException {
        StringBuffer sbaux = new StringBuffer();
        char corrente;
        int bloco = 0;
        char [] aux;
        boolean met = false;
        boolean obj = false;
        boolean cod = false;
        boolean ponto = false;
        String auxiliar = null;
        int contagem = 0;
       // System.out.println(reader.available()+"->"+classe);
        while (reader.available() > 0) {
            corrente = (char) reader.read();
            if((corrente == '\t') || (corrente == '\r')){
                continue;
            }
            
            
            sbaux.append(corrente);
            
            //ler os caracteres até formar uma palavra reservada do java
            if ((sbaux.toString().equals("public")) || (sbaux.toString().equals("protected")) || (sbaux.toString().equals("private"))) {
                met = true;   // encontrei palavra reservada
                sbaux.delete(0, sbaux.length());
            }
            //verificar se a palavra reservada eh de um método
            if ((met == true) && ((corrente == ' ') || (corrente == '('))) {
                aux = sbaux.toString().toCharArray();       // verifica se eh um metoo ou declaração de variável
                for(int i=0; i<aux.length; i++){
                    if(aux[i] == '('){
                        sbaux.deleteCharAt(i);
                        metodoTeste = sbaux.toString();     //encontrei metodo do teste
                        sbaux.delete(0, sbaux.length());
                        met = false;
                        obj=false;
                       // System.out.println("metodo ->" + metodoTeste);
                    }
                }
            }
            
            // verifica abertura de blocos
            if(corrente == '{'){
                bloco++;
            }
            
            if(corrente == '}'){
                bloco--;
            }
            //verificar objetos
            if(corrente != '='){
                auxiliar = sbaux.toString();    // guarda o nome do objeto
            }
            if(sbaux.toString().equals(classe)){
                classeObj = classe;
                obj = true; // encontrou um objeto
                sbaux.delete(0, sbaux.length());
            }
            if((corrente == '(')){
                obj = false;    // torna obj falso para não pegar a instanciação do objeto ex: Calculadora calc = new Calculadora()
            }
            if((obj == true) && ((corrente == '=') || (corrente == ';'))){
                objeto = auxiliar.trim();
                object.add(objeto);
                //System.out.println(object);
            }
            
            // verifica os métodos do código
            for(int i=0; i<object.size(); i++){
                if((sbaux.toString()).equals(object.get(i))){
                    cod = true;    // verifica se encontrou um objeto
                }
            }
            
            if((corrente == '.') && (cod == true)){
                ponto = true;   // verifica se o objeto vai chamar um metodo
                sbaux.delete(0, sbaux.length());
            }
            if((cod == true) && (ponto == true)){
                if((corrente == '(') || (corrente == ' ')){
                    sbaux.deleteCharAt(sbaux.length()-1);
                    
                    mChamado.add(sbaux.toString());    // metodo do codigo encontrado
                    cod = false;
                    ponto = false;
                   
                }
            }
           
            if(metodoTeste != null && classeObj != null && object != null && !mChamado.isEmpty() && bloco == 1){
                dadosTeste = new DadosTeste(classeObj, object, mChamado, metodoTeste);
               // System.out.println(dadosTeste.getClasse() + "->"+dadosTeste.getObjetos()+"->"+dadosTeste.getmChamado()+"->"+dadosTeste.getMetodoTeste());
                 
                    dados.add(dadosTeste);     // adiciona na hashtable
               //   
                    
                    //System.out.println("entrei");
                    /*System.out.println("Chave: "+metodoTeste+"_"+classe+ " Metodo Teste: "+metodoTeste + " Classe: " + dadosTeste.getClasse() + 
                        " Objetos: " + dadosTeste.getObjetos() +" metodosChamados" + dadosTeste.getmChamado());*/
                
                classeObj = null;
                //object.clear();
               // mChamado.clear();
                metodoTeste = null;
                // System.out.println(contagem+"->"+dados.get(contagem).getClasse()+"->"+dados.get(contagem).getMetodoTeste()+"->"+dados.get(contagem).getObjetos()+"->"+dados.get(contagem).getmChamado());
           // contagem++;
            }
            
            
            if ((corrente == '\n') || (corrente == ' ') || (corrente == ',') || auxiliar.equals("ï»¿")){
                sbaux.delete(0, sbaux.length());
            }
            
        }
        /*System.out.println("hashmap");
        Set values = inf.entrySet();
         Iterator myIterator = values.iterator();
         while (myIterator.hasNext()) {
         DadosTeste dados = (DadosTeste) ((Entry) myIterator.next()).getValue();
         System.out.println("Chave: "+ ((Entry) myIterator.next()).getKey()+" Classe: " + dados.getClasse() + " MetodoTeste: " + dados.getMetodoTeste()
         + " Objetos: " + dados.getObjetos().size() + " MChamados: " + dados.getmChamado().size());
         }*/
       //reader.reset();
        
         return dados;
    }
    
}
