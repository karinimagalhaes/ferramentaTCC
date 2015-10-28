/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import faultlocalization.ComparatorResultado;
import faultlocalization.FaultLocalization;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Karini
 */
public class Interface extends javax.swing.JFrame {

    /**
     * Creates new form Interface
     */
    public Interface() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lEcobertura = new javax.swing.JLabel();
        abrEcobertura = new javax.swing.JButton();
        lJunit = new javax.swing.JLabel();
        arqJunit = new javax.swing.JTextField();
        abrJunit = new javax.swing.JButton();
        lheuristica = new javax.swing.JLabel();
        btnExecutar = new javax.swing.JButton();
        lJxr = new javax.swing.JLabel();
        arqJxr = new javax.swing.JTextField();
        abrJxr = new javax.swing.JButton();
        arqEcobertura = new javax.swing.JTextField();
        csTarantula = new javax.swing.JCheckBox();
        csOchiai = new javax.swing.JCheckBox();
        csJaccard = new javax.swing.JCheckBox();
        csSBI = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Localizador de Defeitos");
        setName("principal"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lEcobertura.setText("Cobertura *:");
        getContentPane().add(lEcobertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 19, 70, -1));

        abrEcobertura.setBackground(new java.awt.Color(204, 204, 255));
        abrEcobertura.setText("Abrir");
        abrEcobertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrEcoberturaActionPerformed(evt);
            }
        });
        getContentPane().add(abrEcobertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 75, -1));

        lJunit.setText("Junit*:");
        getContentPane().add(lJunit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 40, -1));
        getContentPane().add(arqJunit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 540, -1));

        abrJunit.setBackground(new java.awt.Color(204, 204, 255));
        abrJunit.setText("Abrir");
        abrJunit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrJunitActionPerformed(evt);
            }
        });
        getContentPane().add(abrJunit, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, 75, -1));

        lheuristica.setBackground(new java.awt.Color(255, 255, 255));
        lheuristica.setText("Heurística*:");
        getContentPane().add(lheuristica, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 70, -1));

        btnExecutar.setBackground(new java.awt.Color(153, 204, 255));
        btnExecutar.setText("Executar");
        btnExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecutarActionPerformed(evt);
            }
        });
        getContentPane().add(btnExecutar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 250, -1, -1));

        lJxr.setText("Jxr*:");
        getContentPane().add(lJxr, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 30, -1));
        getContentPane().add(arqJxr, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 540, -1));

        abrJxr.setBackground(new java.awt.Color(204, 204, 255));
        abrJxr.setText("Abrir");
        abrJxr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrJxrActionPerformed(evt);
            }
        });
        getContentPane().add(abrJxr, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 75, -1));
        getContentPane().add(arqEcobertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 39, 540, -1));

        csTarantula.setText("Tarantula");
        csTarantula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csTarantulaActionPerformed(evt);
            }
        });
        getContentPane().add(csTarantula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        csOchiai.setText("Ochiai");
        getContentPane().add(csOchiai, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        csJaccard.setText("Jaccard");
        getContentPane().add(csJaccard, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        csSBI.setText("SBI");
        getContentPane().add(csSBI, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        jLabel2.setText("Ajuda");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 40, -1));

        jLabel1.setText("* campos obrigatórios.");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 130, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public File ecobertura;
    public File junit;
    public File jxr;
    private Document document;
    private String elementos;           // elementos para escrever no arquivo txt
    private Interface parserHtmlJunit;
    private Interface parserHtmlEcobertura;
    private Interface parserHtmlJxr;
    private ArrayList<Informacoes> linhaMetodo;       // armazena a linha e o método chamado
    private Junit lerJunit;
    private Ecobertura lerEcob;
    private Jxr lerJxr;
    private FaultLocalization heuristicas;
    private File file;
    private FileWriter fw;
    private BufferedWriter bw;
    private FileInputStream reader;
    private String path = "C:\\Users\\Karini\\workspace\\myProject\\target\\site\\teste.txt";

    //private Hashtable prob = new Hashtable();
    private List<Resultado> probTar = new ArrayList<>();
    private List<Resultado> probOch = new ArrayList<>();
    private List<Resultado> probJac = new ArrayList<>();
    private List<Resultado> probSbi = new ArrayList<>();
    private TreeMap<String, Informacoes> inf = new TreeMap<>();
    private Map<String, ArrayList<Integer>> linhas = new TreeMap();  // armazena e classe e as linhas cobertas da classe
    private ArrayList<String> classes = new ArrayList<>();
    private ArrayList<String> files = new ArrayList<>();    // armazena a lista de arquivos do diretório
    private Hashtable infJxr = new Hashtable();
    private ArrayList<DadosTeste> aux = new ArrayList<>();

    public Hashtable getInfJxr() {
        return infJxr;
    }

    public Interface(Document document) {
        this.document = document;
    }

    public Document leituraHtml(String file) throws IOException {
        File leitura = new File(file);
        Document document = Jsoup.parse(leitura, null);
        return document;
    }

    public Document getDocument() {
        return document;
    }


    private void abrJunitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrJunitActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showSaveDialog(null);
        if (i == 1) {
            arqJunit.setText("");
        } else {
            junit = file.getSelectedFile();
            arqJunit.setText(junit.getPath());
        }
    }//GEN-LAST:event_abrJunitActionPerformed

    private void abrEcoberturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrEcoberturaActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int i = file.showSaveDialog(null);
        if (i == 1) {
            arqEcobertura.setText("");
        } else {
            ecobertura = file.getSelectedFile();   // seleciona um arquivo na caixa de dialogo
            arqEcobertura.setText(ecobertura.getPath()); // escreve o endereço do arquivo na caixa de texto    

        }
    }//GEN-LAST:event_abrEcoberturaActionPerformed

    private void abrJxrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrJxrActionPerformed
        // TODO add your handling code here:
        JFileChooser caminho = new JFileChooser();
        caminho.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int i = caminho.showSaveDialog(null);
        if (i == 1) {
            arqJxr.setText("");
        } else {
            jxr = caminho.getSelectedFile();   // seleciona um arquivo na caixa de dialogo
            arqJxr.setText(jxr.getPath()); // escreve o endereço do arquivo na caixa de texto
        }
    }//GEN-LAST:event_abrJxrActionPerformed

    private void btnExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecutarActionPerformed
        // TODO add your handling code here:

        if (!csTarantula.isSelected() && !csJaccard.isSelected() && !csOchiai.isSelected() && !csSBI.isSelected()) {
            JOptionPane.showMessageDialog(null, "Selecione uma heurística");
        }
        // Lê Junit
        File fileJunit = new File(junit.getPath());
        //File diretorioJunit = new File(junit.getPath());
        Document documentoJunit;
        try {
            documentoJunit = Jsoup.parse(fileJunit, null);
            Interface parserHtmlJunit = new Interface(documentoJunit);
            lerJunit = new Junit(parserHtmlJunit.document);
            lerJunit.totais();                              // possui qtde de testes, falhas e sucesso do projeto
            lerJunit.testesFalhos();                        // possui os testes que falharam ou contiveram erros
        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }

        // ---------------------------- Processa arquivos Ecobertura --------------------------------------
        navegar(ecobertura.getPath());     // armazena os arquivos do diretório no arrayList files
        for (int i = 0; i < files.size(); i++) {
            File fileEcob = new File(files.get(i));
            Document documentoEcob;

            try {
                //System.out.println(files.get(i));
                documentoEcob = Jsoup.parse(fileEcob, null);
                Interface parserHtmlEcob = new Interface(documentoEcob);
                lerEcob = new Ecobertura(parserHtmlEcob.document);
                boolean cobertura = lerEcob.cobertura();
                if (cobertura == true) {            // se o arquivo não cobrir o código fonte -> dispensa arquivo
                    lerEcob.escreveTxt();
                    if (lerEcob.getClasse() != null) {
                        linhas.put(lerEcob.getClasse(), lerEcob.qtdeLinhasCod());   // armazena a classe e as linhas cobertas da classe
                        classes.add(lerEcob.getClasse());                           // armazena todas as classes do sistema
                        linhaMetodo = lerEcob.getInf();
                        for (int j = 0; j < linhaMetodo.size(); j++) {        // linhaMetodo é um array de informacoes
                            String chave = Integer.toString(linhaMetodo.get(j).getLinha()) + lerEcob.getClasse();    // chave da treeMap
                            inf.put(chave, linhaMetodo.get(j));         // inf armazena a linha e o método chamado por ela
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        files.clear();

        //----------------------------------- Fim processamento Ecobertura--------------------------------------
        // ----------------------------------Processamento arquivos JXR (código de teste) -----------------------------
        navegar(jxr.getPath());
        for (int i = 0; i < files.size(); i++) {
            File fileJxr = new File(files.get(i));
            Document documentoJxr;

            try {
                documentoJxr = Jsoup.parse(fileJxr, null);
                Interface parserHtmlJxr = new Interface(documentoJxr);
                lerJxr = new Jxr(parserHtmlJxr.document);
                parserHtmlJxr.elementos = lerJxr.leituraJxr();  // retorna o código do relatório sem lixo
                // System.out.println(parserHtmlJxr.elementos);
                escreveTxt(parserHtmlJxr.elementos);     // escreve ocódigo em um arquivo TXT para nova leitura

                for (int j = 0; j < classes.size(); j++) {
                    reader = new FileInputStream(path);
                    aux = lerJxr.leTxt(classes.get(j), path, reader);                       // busca métodos, objetos da classe passada por parâmetro em todos os códigos de teste
                    for (int k = 0; k < aux.size(); k++) {
                        infJxr.put(aux.get(k).getMetodoTeste() + "_" + aux.get(k).getClass(), aux.get(k));
                        // System.out.println(aux.get(k).getMetodoTeste()+"_"+aux.get(k).getClasse());
                    }
                    reader.close();
                }
                //  reader.close();
                file.delete();
                aux.clear();

            } catch (IOException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //------------------------------------------ imprime valores do hashtable -----------------------------
       /*Set values = infJxr.entrySet();
         Iterator myIterator = values.iterator();
         System.out.println("Listando arquivos contidos no HashMap myHashMap:");
         while (myIterator.hasNext()) {
         DadosTeste dados = (DadosTeste) ((Entry) myIterator.next()).getValue();
         System.out.println(" Classe: " + dados.getClasse() + " MetodoTeste: " + dados.getMetodoTeste()
         + " Objetos: " + dados.getObjetos() + " MChamados: " + dados.getmChamado() );
         }*/
        //---------------------------------- fim impressão hashtable---------------------------------------------
        files.clear();
        //----------------------------------- Fim processamento Jxr -----------------------------------------------

        //--------------------------------------- Início cálculo das heurísticas -------------------------------------
        Double sucesso = 0.0;
        Double falha = 0.0;
        Double totSucesso = 0.0;
        Double totFalha = 0.0;
        Double probabilidade = 0.0;
        ArrayList<Integer> linhasClasse = new ArrayList<>();
        /*
         Para cada array de linha dentro da treeMap temos que passá-lo para o método falharam
         */

        //
        totSucesso = (double) lerJunit.getQtdeSucesso();
        totFalha = (double) lerJunit.getQtdeFalhas();

        for (int i = 0; i < classes.size(); i++) {

            linhasClasse = linhas.get(classes.get(i));
            //System.out.println(inf.keySet());
            //System.out.println(linhasClasse);
            for (int count = 0; count < linhasClasse.size(); count++) {
                lerEcob.falharam(classes.get(i), linhasClasse.get(count), inf, infJxr, lerJunit);   // pega todos os arraylist contidos em linhas
                sucesso = (double) lerEcob.getPassaram();
                falha = (double) lerEcob.getFalharam();

                if (csTarantula.isSelected()) {
                    heuristicas = new FaultLocalization(sucesso, falha, totSucesso, totFalha);
                    probabilidade = heuristicas.tarantula();
                    // System.out.println("Linha: "+linhasClasse.get(count)+" probabilidade: "+probabilidade);
                    probTar.add(new Resultado(classes.get(i), linhasClasse.get(count), probabilidade, "Tarantula"));
                }
                // entriesSortedByValues(probTar);
                ///System.out.println(entriesSortedByValues(probTar));

                if (csOchiai.isSelected()) {
                    heuristicas = new FaultLocalization(sucesso, falha, totSucesso, totFalha);
                    probabilidade = heuristicas.ochiai();
                    probOch.add(new Resultado(classes.get(i), linhasClasse.get(count), probabilidade, "Ochiai"));
                }
                //System.out.println(entriesSortedByValues(probOch));

                if (csJaccard.isSelected()) {
                    heuristicas = new FaultLocalization(sucesso, falha, totSucesso, totFalha);
                    probabilidade = heuristicas.jaccard();
                    probJac.add(new Resultado(classes.get(i), linhasClasse.get(count), probabilidade, "Jaccard"));
                    //System.out.println(entriesSortedByValues(probJac));
                }
                if (csSBI.isSelected()) {
                    heuristicas = new FaultLocalization(sucesso, falha, totSucesso, totFalha);
                    probabilidade = heuristicas.sbi();
                    probSbi.add(new Resultado(classes.get(i), linhasClasse.get(count), probabilidade, "SBI"));
                    //System.out.println("linha: " + linhas.get(i)+ " falha: " + falhasLinha.get(i) + " sucesso: " + sucessoLinha.get(i) );
                    //System.out.println(entriesSortedByValues(probSbi));
                }
            }
        }

    
     ordena(probTar);
     ordena(probJac);
     ordena(probOch);
     ordena(probSbi);
     System.out.println("------------------------IMPRESSAO TAR-----------------------------------------------------\n" );
        for(int i=0; i<probTar.size(); i++){
            System.out.println("Classe: "+ probTar.get(i).getClasse()+" Linha: "+probTar.get(i).getLinha()+" Probabilidade: "+probTar.get(i).getProbabilidade());
        }
        System.out.println("-----------------------------IMPRESSAO Jac------------------------------------------------\n");
        for(int i=0; i<probJac.size(); i++){
            System.out.println("Classe: "+ probJac.get(i).getClasse()+" Linha: "+probJac.get(i).getLinha()+" Probabilidade: "+probJac.get(i).getProbabilidade());
        }
        System.out.println("----------------------------------------IMPRESSAO OCH-----------------------------------------\n" );
        for(int i=0; i<probTar.size(); i++){
            System.out.println("Classe: "+ probOch.get(i).getClasse()+" Linha: "+probOch.get(i).getLinha()+" Probabilidade: "+probOch.get(i).getProbabilidade());
        }
        System.out.println("---------------------------------------------IMPRESSAO SBI-----------------------------------------------\n" );
        for(int i=0; i<probTar.size(); i++){
            System.out.println("Classe: "+ probSbi.get(i).getClasse()+" Linha: "+probSbi.get(i).getLinha()+" Probabilidade: "+probSbi.get(i).getProbabilidade());
        }/*
    
        
        ResultadoJanela.inicializaJanela(probTar, probJac, probOch, probSbi);*/

        //--------------------------------------------Fim cálculo das heurísticas -----------------------------------------------------

    }//GEN-LAST:event_btnExecutarActionPerformed

    public void ordena(List<Resultado> outro){
            Collections.sort(outro, new Comparator<Resultado>() {
    @Override
    public int compare(Resultado c1, Resultado c2) {
        return Double.compare(c1.getProbabilidade(), c2.getProbabilidade());
    }
});
    }
    
    public void escreveTxt(String elementos) throws IOException {       //escreve código do teste no arquivo txt
        file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }
        fw = new FileWriter(file, true);
        bw = new BufferedWriter(fw);
        bw.write(elementos);
        bw.flush();
        bw.close();
        fw.close();
    }

    public void navegar(String caminho) {
        File diretorio = new File(caminho);
        for (File file : diretorio.listFiles()) {
            if (file.isFile()) {
                if (!file.getName().startsWith("frame") && !file.getName().startsWith("index")
                        && !file.getName().startsWith("help") && !file.getName().startsWith("package")
                        && !file.getName().startsWith("allclasses") && !file.getName().startsWith("overview")
                        && !file.getName().startsWith("stylesheet")) {
                    files.add(caminho + "\\" + file.getName());
                }
            } else if ((!file.getName().equals("css") && !file.getName().equals("images") && !file.getName().equals("js"))) {
                //System.out.println("DIRETORIO: " + file.getName());
                navegar(caminho + "\\" + file.getName());
            }
        }
    }

    static <K, V extends Comparable<? super V>>
            SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
        SortedSet<Map.Entry<K, V>> sortedEntries;
        sortedEntries = new TreeSet<Map.Entry<K, V>>(
                new Comparator<Map.Entry<K, V>>() {
                    @Override
                    public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
                        int res = e1.getValue().compareTo(e2.getValue());
                        return res != 0 ? res : 1;
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }


    private void csTarantulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csTarantulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_csTarantulaActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Este programa tem como objetivo auxiliar os desenvolvedores na procura por defeitos no código fonte.\n"
                + "Abaixo são descritas as funcionalidades de cada campo:\n"
                + "*Cobertura: Destinado para inserção do DIRETÓRIO dos relatórios de cobertura de código gerados pelo plugin Maven Cobertura.\n"
                + "*Jxr: Destinado para inserção do DIRETÓRIO dos relatórios com os códigos dos testes unitários gerados pelo plugin Maven Jxr.\n"
                + "*Junit: Destinado para inserção do RALATÓRIO de teste gerado pelo plugin Surefire Report.\n"
                + "*Heurística: Destinado para a escolha de pelo menos uma heurística de localização.");
    }//GEN-LAST:event_jLabel2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrEcobertura;
    private javax.swing.JButton abrJunit;
    private javax.swing.JButton abrJxr;
    private javax.swing.JTextField arqEcobertura;
    private javax.swing.JTextField arqJunit;
    private javax.swing.JTextField arqJxr;
    private javax.swing.JButton btnExecutar;
    private javax.swing.JCheckBox csJaccard;
    private javax.swing.JCheckBox csOchiai;
    private javax.swing.JCheckBox csSBI;
    private javax.swing.JCheckBox csTarantula;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lEcobertura;
    private javax.swing.JLabel lJunit;
    private javax.swing.JLabel lJxr;
    private javax.swing.JLabel lheuristica;
    // End of variables declaration//GEN-END:variables

}
