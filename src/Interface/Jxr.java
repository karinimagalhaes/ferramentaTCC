/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
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
        ArrayList <String> object = new ArrayList<String>();
        ArrayList<String> mChamado = new ArrayList<String>();
        ArrayList<DadosTeste> dados = new ArrayList<>();
        String metodoTeste = null;
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
            if(corrente == '{'){
                bloco++;
            }
            
            if(corrente == '}'){
                bloco--;
            }
            
            if(corrente != '='){
                auxiliar = sbaux.toString();                // guarda o nome do objeto
            }
            
            //ler os caracteres até formar uma palavra reservada do java
            if ((sbaux.toString().equals("public")) || (sbaux.toString().equals("protected")) || (sbaux.toString().equals("private"))
                    || (sbaux.toString().equals("static")) || (sbaux.toString().equals("final"))|| (sbaux.toString().equals("native"))
                    || (sbaux.toString().equals("synchronized"))|| (sbaux.toString().equals("abstract")) || (sbaux.toString().equals("threadsafe"))
                    || (sbaux.toString().equals("transient"))) {
                met = true;   // encontrei palavra reservada
                sbaux.delete(0, sbaux.length());
            }
            if(sbaux.toString().equals("class")){
                met = false;
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
            
            
            if(sbaux.toString().equals(classe)){
                classeObj = classe;
                obj = true;                                 // encontrou um objeto
                sbaux.delete(0, sbaux.length());
            }
            if((corrente == '(')){
                obj = false;                                // torna obj falso para não pegar a instanciação do objeto ex: Calculadora calc = new Calculadora()
            }
            if((obj == true) && ((corrente == '=') || (corrente == ';'))){
                objeto = auxiliar.trim();
                object.add(objeto);
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
                    
                    sbaux.deleteCharAt(sbaux.length()-1);
                    
                    mChamado.add(sbaux.toString());    // metodo do codigo encontrado
                    cod = false;
                    ponto = false;
                   
                }
            }
           
            if(metodoTeste != null && classeObj != null && object != null && !mChamado.isEmpty() && bloco == 1){
                dadosTeste = new DadosTeste(classeObj, object, mChamado, metodoTeste);
                //infJxr.put(metodoTeste + "_" + classeObj, dadosTeste);
                dados.add(dadosTeste);
                classeObj = null;
                object = new ArrayList<>();
                mChamado = new ArrayList<>();
                metodoTeste = null;
            }
            if ((corrente == '\n') || (corrente == ' ') || (corrente == ',') || auxiliar.equals("ï»¿")){
                sbaux.delete(0, sbaux.length());
            }
        }
        return dados;
    }
    
}