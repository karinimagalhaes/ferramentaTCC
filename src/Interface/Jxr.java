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
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Karini
 */
public class Jxr {

    private String path = "C:\\Users\\Karini\\workspace\\myProject\\target\\site\\teste.txt";
    private ArrayList <String> object = new ArrayList<String>();
    private String metodoTeste;
    private String classeObj;
    private String objeto;
    private String mChamado;
    private DadosTeste dadosTeste = null;
    private Document document;
    private FileInputStream reader;
    private Hashtable inf = new Hashtable();
    private File file;
    private FileWriter fw;
    private BufferedWriter bw;

    public Jxr(Document document) {
        this.document = document;
    }
    
    public Hashtable getInf(){
        return inf;
    }

    public String leituraJxr() throws IOException {   //método para pegar os nomes dos métodos declarados
        Elements elements = document.getElementsByTag("pre");
        elements.select("a.jxr_linenumber").remove();
       // elements.select("strong.jxr_keyword").remove();
        // elements.select("span.jxr_string").remove();
        elements.select("em.jxr_comment").remove();
        //System.out.println(elements.text());
        return elements.text();     // retorna o código sem lixo
    }

    public void escreveTxt(String elementos) throws IOException {       //escreve código do teste no arquivo txt
        file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }else{
            file.deleteOnExit();
            file.createNewFile();
        }
        fw = new FileWriter(file, true);
        bw = new BufferedWriter(fw);
        bw.write(elementos);
        bw.flush();
       
    }

    public void leTxt(String classe) throws IOException {
        StringBuffer sbaux = new StringBuffer();
        char corrente;
        char [] aux;
        boolean met = false;
        boolean obj = false;
        boolean cod = false;
        boolean ponto = false;
        String auxiliar = null;
        reader = new FileInputStream(path);
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
                        //System.out.println("metodo ->" + metodoTeste);
                    }
                }
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
            if(corrente == '('){
                obj = false;    // torna obj falso para não pegar a instanciação do pbjeto ex: Calculadora calc = new Calculadora()
            }
            if((obj == true) && ((corrente == '=') || (corrente == ';'))){
                //sbaux.deleteCharAt(sbaux.length());
                objeto = auxiliar.trim();
                object.add(objeto);
            }
            
            // verifica os métodos do código
            for(int i=0; i<object.size(); i++){
               // System.out.println("["+sbaux.toString()+"]" + " " + "["+objetos.get(i)+"]");
                if(sbaux.toString().equals(object.get(i))){
                    cod = true;    // verifica se encontrou um objeto
                }
            }
            
            if((corrente == '.') && (cod == true)){
                ponto = true;   // verifica se o objeto vai chamar um metodo
                sbaux.delete(0, sbaux.length());
            }
            if(cod == true && ponto == true){
                if((corrente == '(') || (corrente == ' ')){
                    sbaux.deleteCharAt(sbaux.length()-1);
                    mChamado = sbaux.toString();    // metodo do codigo encontrado
                    cod = false;
                    ponto = false;
                    
                }
            }
            
            if(metodoTeste != null && classeObj != null && objeto != null && mChamado != null){
                dadosTeste = new DadosTeste(classeObj, objeto, mChamado, metodoTeste);
                if(!inf.containsKey(metodoTeste+"_"+classe)){
                    inf.put(metodoTeste+"_"+classe, dadosTeste);       // adiciona na hashtable
                    //System.out.println("Chave: "+metodoTeste+"_"+classe+ "Metodo Teste: "+metodoTeste + " Classe: " + dadosTeste.getClasse() + 
                      // "Objetos: " + dadosTeste.getObjetos() +"metodosChamados" + dadosTeste.getmChamados());
                }
                classeObj = null;
                objeto = null;
                mChamado = null;
                metodoTeste = null;
            }
            
            
            if ((corrente == '\n') || (corrente == ' ') || (corrente == ',') || auxiliar.equals("ï»¿")){
                sbaux.delete(0, sbaux.length());
            }
            
        }
    }
}
