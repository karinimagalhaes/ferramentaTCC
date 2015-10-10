/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Real;
import faultlocalization.FaultLocalization;
import faultlocalization.MapValueComparator;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Localizador de Defeitos");
        setName("principal"); // NOI18N

        lEcobertura.setText("Cobertura:");

        abrEcobertura.setBackground(new java.awt.Color(204, 204, 255));
        abrEcobertura.setText("Abrir");
        abrEcobertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrEcoberturaActionPerformed(evt);
            }
        });

        lJunit.setText("Junit:");

        abrJunit.setBackground(new java.awt.Color(204, 204, 255));
        abrJunit.setText("Abrir");
        abrJunit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrJunitActionPerformed(evt);
            }
        });

        lheuristica.setBackground(new java.awt.Color(255, 255, 255));
        lheuristica.setText("Heurística:");

        btnExecutar.setBackground(new java.awt.Color(153, 204, 255));
        btnExecutar.setText("Executar");
        btnExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecutarActionPerformed(evt);
            }
        });

        lJxr.setText("Jxr:");

        abrJxr.setBackground(new java.awt.Color(204, 204, 255));
        abrJxr.setText("Abrir");
        abrJxr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrJxrActionPerformed(evt);
            }
        });

        csTarantula.setText("Tarantula");
        csTarantula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csTarantulaActionPerformed(evt);
            }
        });

        csOchiai.setText("Ochiai");

        csJaccard.setText("Jaccard");

        csSBI.setText("SBI");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(arqJxr)
                            .addComponent(arqJunit)
                            .addComponent(arqEcobertura)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lheuristica)
                                    .addComponent(csTarantula)
                                    .addComponent(csSBI))
                                .addGap(0, 370, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(abrJunit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(abrEcobertura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExecutar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(abrJxr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(csJaccard)
                            .addComponent(lEcobertura)
                            .addComponent(lJunit)
                            .addComponent(lJxr)
                            .addComponent(csOchiai))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lEcobertura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(abrEcobertura)
                    .addComponent(arqEcobertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lJunit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arqJunit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(abrJunit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lJxr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arqJxr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(abrJxr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lheuristica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(csTarantula)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(csOchiai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(csJaccard)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnExecutar))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(csSBI)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public File ecobertura;
    public File junit;
    public File jxr;
    private Document document;
    private String elementos;
    private Interface parserHtmlJunit;
    private Interface parserHtmlEcobertura;
    private Interface parserHtmlJxr;
    //private Hashtable prob = new Hashtable();
    private TreeMap<Integer, Double> probTar = new TreeMap<Integer, Double>();
    private TreeMap<Integer, Double> probOch = new TreeMap<Integer, Double>();
    private TreeMap<Integer, Double> probJac = new TreeMap<Integer, Double>();
    private TreeMap<Integer, Double> probSbi = new TreeMap<Integer, Double>();
    private ArrayList<Integer> linhas = new ArrayList<Integer>();
    private Junit lerJunit;
    private Ecobertura lerEcob;
    private Jxr lerJxr;
    private FaultLocalization heuristicas;

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
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
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
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showSaveDialog(null);
        if (i == 1) {
            arqJxr.setText("");
        } else {
            jxr = file.getSelectedFile();   // seleciona um arquivo na caixa de dialogo
            arqJxr.setText(jxr.getPath()); // escreve o endereço do arquivo na caixa de texto

        }
    }//GEN-LAST:event_abrJxrActionPerformed

    private void btnExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecutarActionPerformed
        // TODO add your handling code here:
        double probabilidade;
        JTextArea tar = new JTextArea();
        JTextArea och = new JTextArea();
        JTextArea jac = new JTextArea();
        JTextArea sbi = new JTextArea();
        try {
            //Lendo arquivo Junit
            File fileJunit = new File("C:\\Users\\Karini\\workspace\\Teste\\target\\site\\surefire-report.html");
            Document documentoJunit = Jsoup.parse(fileJunit, null);
            Interface parserHtmlJunit = new Interface(documentoJunit);
            lerJunit = new Junit(parserHtmlJunit.document);
            lerJunit.totais();
            lerJunit.testesFalhos();

            //Lendo Ecobertura
            File fileEcob = new File("C:/Users/Karini/workspace/Teste/target/site/cobertura/TesteUnitario.Teste.Calculadora.html");
            Document documentoEcob = Jsoup.parse(fileEcob, null);
            Interface parserHtmlEcob = new Interface(documentoEcob);
            lerEcob = new Ecobertura(parserHtmlEcob.document);
            linhas = lerEcob.qtdeLinhasCod();
            lerEcob.escreveTxt();

            //Lendo Teste Jxr
            File fileJxr = new File("C:/Users/Karini/workspace/Teste/target/site/xref-test/TesteUnitario/Teste/CalculadoraTest.html");
            Document documentoJxr = Jsoup.parse(fileJxr, null);
            Interface parserHtmlJxr = new Interface(documentoJxr);
            lerJxr = new Jxr(parserHtmlJxr.document);
            parserHtmlJxr.elementos = lerJxr.leituraJxr();
            lerJxr.escreveTxt(parserHtmlJxr.elementos);
            lerJxr.leTxt(lerEcob.getClasse());

            /* verificar caixas de seleção
             getPassaram é o total de testes que passaram na linha x
             getFalharam é o total de testes que falharam e passaram pela linha x
             getQtdeSucesso é a quantidade de testes executados que tiveram sucesso
             getQtdeFalhas é a quantidade de testes executados que falharam
             */
            ArrayList<Double> sucesso = new ArrayList<Double>();
            ArrayList<Double> falha = new ArrayList<Double>();
            ArrayList<Double> totSucesso = new ArrayList<Double>();
            ArrayList<Double> totFalha = new ArrayList<Double>();

            for (int i = 0; i < linhas.size(); i++) {
                lerEcob.falharam(linhas.get(i), lerJxr, lerJunit);

                sucesso.add((double) lerEcob.getPassaram());
                falha.add((double) lerEcob.getFalharam());
                totSucesso.add((double) lerJunit.getQtdeSucesso());
                totFalha.add((double) lerJunit.getQtdeFalhas());
            }

            if(!csTarantula.isSelected() && !csJaccard.isSelected() && !csOchiai.isSelected() && !csSBI.isSelected()){
                JOptionPane.showMessageDialog(null,"Selecione uma heurística");
            }
            
            if(csTarantula.isSelected()){
                for (int i = 0; i < linhas.size(); i++) {
                    heuristicas = new FaultLocalization(sucesso.get(i), falha.get(i), totSucesso.get(i), totFalha.get(i));
                    probabilidade = heuristicas.tarantula();
                    probTar.put(linhas.get(i), probabilidade);
                }
                entriesSortedByValues(probTar);
                System.out.println(entriesSortedByValues(probTar));
            }
            if (csOchiai.isSelected()) {

                for (int i = 0; i < linhas.size(); i++) {
                    heuristicas = new FaultLocalization(sucesso.get(i), falha.get(i), totSucesso.get(i), totFalha.get(i));
                    probabilidade = heuristicas.ochiai();
                    probOch.put(linhas.get(i), probabilidade);
                }
                System.out.println(entriesSortedByValues(probOch));
            }

            if (csJaccard.isSelected()) {
                for (int i = 0; i < linhas.size(); i++) {
                    heuristicas = new FaultLocalization(sucesso.get(i), falha.get(i), totSucesso.get(i), totFalha.get(i));
                    probabilidade = heuristicas.jaccard();
                    probJac.put(linhas.get(i), probabilidade);
                }
                System.out.println(entriesSortedByValues(probJac));
            }

            if (csSBI.isSelected()) {
                for (int i = 0; i < linhas.size(); i++) {
                    heuristicas = new FaultLocalization(sucesso.get(i), falha.get(i), totSucesso.get(i), totFalha.get(i));
                    probabilidade = heuristicas.sbi();
                    probSbi.put(linhas.get(i), probabilidade);
                    //System.out.println("linha: " + linhas.get(i)+ " falha: " + falhasLinha.get(i) + " sucesso: " + sucessoLinha.get(i) );
                }
                System.out.println(entriesSortedByValues(probSbi));
            }
            
            JanelaResult panel = new JanelaResult();
            panel.setVisible(true);
            

        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExecutarActionPerformed
    
    static <K,V extends Comparable<? super V>>
SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
        new Comparator<Map.Entry<K,V>>() {
            @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
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
    private javax.swing.JLabel lEcobertura;
    private javax.swing.JLabel lJunit;
    private javax.swing.JLabel lJxr;
    private javax.swing.JLabel lheuristica;
    // End of variables declaration//GEN-END:variables

}