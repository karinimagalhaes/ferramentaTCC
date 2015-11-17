/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leitura;

import Leitura.DadosTeste;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Karini
 */
public class Jxr {

    private Document document;
    
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
            children.getElementsByClass("jxr_javadoccomment").remove();
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
        boolean bClasse = false;
        boolean achei = false;
        boolean statment = false;
        boolean clas = false;
        ArrayList <String> object = new ArrayList<String>();
        ArrayList<String> mChamado = new ArrayList<String>();
        ArrayList<DadosTeste> dados = new ArrayList<>();
        String metodoTeste = null;
        String classeTeste = null;
        String classeObj = null;
        String objeto = null;
        String auxiliar = null;
        DadosTeste dadosTeste = null;
        
       // System.out.println(reader.available()+"->"+classe);
        while (reader.available() > 0) {
            corrente = (char) reader.read();
            if((corrente == '\t') || (corrente == '\r')){
                continue;
            }
            
            sbaux.append(corrente);
            // verifica abertura de blocos
            
            
            if(corrente != '='){
                auxiliar = sbaux.toString();                // guarda o nome do objeto
            }
            
            if((corrente == ' ') && (auxiliar.equals("do") || auxiliar.equals("if") || auxiliar.equals("else") || auxiliar.equals("for") 
                    ||auxiliar.equals("while") || auxiliar.equals("switch") || auxiliar.equals("try")|| auxiliar.equals("catch"))){
                statment = true;
                
            }
            
            if((corrente == '{') && (statment == false)){
                bloco++;
            }
            
            if((corrente == '}') && (statment == false)){
                bloco--;
            } else if((corrente == '}') && (statment == true)){
                statment = false;
            }
            
            //ler os caracteres até formar uma palavra reservada do java
            if ((sbaux.toString().equals("public")) || (sbaux.toString().equals("protected")) || (sbaux.toString().equals("private"))
                    || (sbaux.toString().equals("static")) || (sbaux.toString().equals("final"))|| (sbaux.toString().equals("native"))
                    || (sbaux.toString().equals("synchronized"))|| (sbaux.toString().equals("abstract")) || (sbaux.toString().equals("threadsafe"))
                    || (sbaux.toString().equals("transient"))) {
                met = true;   // encontrei palavra reservada
                sbaux.delete(0, sbaux.length());
            }
            
            if(sbaux.toString().equals("class ") && classeTeste == null){
                met = false;
                clas = true;
                sbaux.delete(0, sbaux.length());
            }
            if(clas == true &&  !auxiliar.toString().equals("class ") && corrente == ' '){
                classeTeste = sbaux.toString();
                clas = false;
                sbaux.delete(0, sbaux.length());
            }
            
            
            //verificar se a palavra reservada eh de um método
            if(met == true && corrente=='.'){
                met = false;
                
                sbaux.delete(0, sbaux.length());
            }
            if(auxiliar.equals("new")){
                met=false;
            }
            if(met == true && ((corrente == ')') || (corrente == '='))){
                met = false;
            }
            if ((met == true) && ((corrente == ' ') || (corrente == '('))) {
              
                
                aux = sbaux.toString().toCharArray();       // verifica se eh um metoo ou declaração de variável
                for(int i=0; i<aux.length; i++){
                    if(aux[i] == '('){
                        sbaux.deleteCharAt(i);
                        metodoTeste = sbaux.toString();     //encontrei metodo do teste
                        sbaux.delete(0, sbaux.length());
                        met = false;
                        //System.out.println("metodo ->" + metodoTeste);
                        break;
                    }
                }
            }
         if(sbaux.toString().equals(classe+" ")){
                bClasse = true;
         }
         
        if((corrente == '{') && (bClasse == true)){
            bClasse = false;
            obj = false;
        }
           
            if((bClasse == true) && (corrente == ' ')){
                classeObj = classe;
                //System.out.println(classeObj);
                bClasse = false;
                obj = true;                                 // encontrou um objeto
                sbaux.delete(0, sbaux.length());
            }
            if((corrente == '(') || (corrente == '.') || (corrente == ')')){
                obj = false;                                // torna obj falso para não pegar a instanciação do objeto ex: Calculadora calc = new Calculadora()
            }
           
            if((obj == true) && ((corrente == '=') || (corrente == ';'))){
                objeto = auxiliar.replace(';', ' ').trim();
                object.add(objeto);
                obj = false;
                //System.out.println(object);
            }
            
            // verifica os métodos do código
            for(int i=0; i<object.size(); i++){
                if((sbaux.toString()).equals(object.get(i))){
                    cod = true;                          // verifica se encontrou um objeto
                }
            }
            
            if((corrente == '.') && (cod == true)){
                ponto = true;                           // verifica se o objeto vai chamar um metodo
                sbaux.delete(0, sbaux.length());
            }
            if((cod == true) && (ponto == true)){
                if((corrente == '(') || (corrente == ' ')){
                    if(sbaux.length() != 0){
                        sbaux.deleteCharAt(sbaux.length()-1);
                        for(int i=0; i<mChamado.size(); i++){
                            if(mChamado.get(i).equals(sbaux.toString()))
                                achei = true;
                        }
                        if(achei != true){
                            mChamado.add(sbaux.toString());    // metodo do codigo encontrado
                            achei = false;
                        }
                    }
                    cod = false;
                    ponto = false;
                   
                }
            }
            
            if(StringUtils.isNotBlank(metodoTeste) && StringUtils.isNotBlank(classeObj) && !object.isEmpty() && !mChamado.isEmpty() && (bloco%2!=0)){
               // System.out.println("ClasseOBj: "+classeObj+" Objeto: "+object+" MChamado: "+mChamado+" Metodo Teste:"+metodoTeste+" ClasseTeste: "+classeTeste);
                dadosTeste = new DadosTeste(classeObj, object, mChamado, metodoTeste, classeTeste.trim());
                //infJxr.put(metodoTeste + "_" + classeObj, dadosTeste);
                dados.add(dadosTeste);
                classeObj = null;
                object = new ArrayList<>();
                mChamado = new ArrayList<>();
                metodoTeste = null;
                objeto = null;
                
                met = false;
                obj = false;
                cod = false;
                ponto = false;
                bClasse = false;
                achei = false;
            }
            if ((corrente == '\n') || (corrente == ' ') || (corrente == ',') || auxiliar.equals("ï»¿") || (corrente == '(')){
                sbaux.delete(0, sbaux.length());
            }
        }

        clas = false;
        classeTeste = null;
        return dados;
    }
    
}