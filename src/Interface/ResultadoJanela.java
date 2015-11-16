/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Leitura.Resultado;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.awt.event.MouseEvent;
import static java.lang.System.out;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

/**
 *
 * @author Karini
 */
public final class ResultadoJanela extends JFrame {

    public ResultadoJanela(List<Resultado> tar, List<Resultado> jac, List<Resultado> och, List<Resultado> sbi) {
        //  super("Resultado");
        CategoryDataset dataset;

        //---------------------gerando resultados tarantula-------------------------------------
        dataset = gerarDataset(tar, jac, och, sbi);
        JFreeChart chart = gerarGrafico(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setDomainZoomable(true);

        JLabel lAjuda = new JLabel("Ajuda", JLabel.RIGHT);
        lAjuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icone_informacao.gif"))); // NOI18N
        lAjuda.setPreferredSize(new Dimension(50, 50));
        lAjuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lAjudaMouseClicked(evt);
            }
        });
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(lAjuda, BorderLayout.BEFORE_FIRST_LINE);
        panel.add(chartPanel, BorderLayout.LINE_START);
        JLabel lTabela = new JLabel("Tabela de Resultados", JLabel.CENTER);
        panel.add(lTabela, BorderLayout.SOUTH);
 
        JTable table = new JTable(criarValores(tar, jac, och, sbi), criarColunas());

        // Adiciona o JTable dentro do painel
        JScrollPane scrollPane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        panel.add(scrollPane, BorderLayout.SOUTH);

        JFrame frame = new JFrame();
        frame.setTitle("JLoc - Resultado");
        frame.setVisible(true);
        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    public static void inicializaJanela(List<Resultado> tar, List<Resultado> jac, List<Resultado> och, List<Resultado> sbi) {
        new ResultadoJanela(tar, jac, och, sbi);
    }

    public JFreeChart gerarGrafico(CategoryDataset dataSet) {
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Resultado da Localização de Defeitos", //Titulo
                " ", // Eixo X
                "Probabilidade (%)", //Eixo Y
                dataSet, // Dados para o grafico
                PlotOrientation.VERTICAL, //Orientacao do grafico
                true,
                true,
                true); // exibir: legendas, tooltips, url
        chart.setBackgroundPaint(Color.getHSBColor(0, 0, (float) 0.835)); // Set the background colour of the chart
        
        
        CategoryPlot p = chart.getCategoryPlot(); // Get the Plot object for a bar graph
        p.setBackgroundPaint(Color.white); // Modify the plot background 
        p.setRangeGridlinePaint(Color.BLACK);
        
        CategoryAxis axis = p.getDomainAxis(); 
        axis.setTickLabelFont(new Font("Helvetica", Font.PLAIN, 10)); 
        axis.setMaximumCategoryLabelWidthRatio(1.0f); 
        axis.setMaximumCategoryLabelLines(2); 
        p.setDomainAxis(axis );
        

        return chart;
    }

    private CategoryDataset gerarDataset(List<Resultado> tar, List<Resultado> jac, List<Resultado> och, List<Resultado> sbi) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       

         if(tar.size() > 8){
            for(int i = 0; i<7; i++){
                dataset.addValue(tar.get(i).getProbabilidade()*100, tar.get(i).getHeuristica(), Integer.toString(tar.get(i).getLinha())+" "+tar.get(i).getClasse());
                dataset.addValue(jac.get(i).getProbabilidade()*100, jac.get(i).getHeuristica(), Integer.toString(jac.get(i).getLinha())+" "+jac.get(i).getClasse());
                dataset.addValue(och.get(i).getProbabilidade()*100, och.get(i).getHeuristica(), Integer.toString(och.get(i).getLinha())+" "+och.get(i).getClasse());
                dataset.addValue(sbi.get(i).getProbabilidade()*100, sbi.get(i).getHeuristica(), Integer.toString(sbi.get(i).getLinha())+" "+sbi.get(i).getClasse()); 
            }
        }    
         else{
            for(int i = 0; i<tar.size(); i++){
                dataset.addValue(tar.get(i).getProbabilidade()*100, tar.get(i).getHeuristica(), Integer.toString(tar.get(i).getLinha())+" "+tar.get(i).getClasse());
                dataset.addValue(jac.get(i).getProbabilidade()*100, jac.get(i).getHeuristica(), Integer.toString(jac.get(i).getLinha())+" "+jac.get(i).getClasse());
                dataset.addValue(och.get(i).getProbabilidade()*100, och.get(i).getHeuristica(), Integer.toString(och.get(i).getLinha())+" "+och.get(i).getClasse());
                dataset.addValue(sbi.get(i).getProbabilidade()*100, sbi.get(i).getHeuristica(), Integer.toString(sbi.get(i).getLinha())+" "+sbi.get(i).getClasse()); 
            }
        }
        return dataset;
    }

    public Object[] criarColunas() {
        Object[] colunas = new Object[]{ "Linha", "Classe", "Tarantula (%)", "Jaccard (%)", "Ochiai (%)", "SBI (%)"};
        return colunas;
    }

    public Object[][] criarValores(List<Resultado> tar, List<Resultado> jac, List<Resultado> och, List<Resultado> sbi) {
        Object[][] valores = new Object[tar.size()][6];
         DecimalFormat df = new DecimalFormat("#.00");
        for (int i = 0; i < tar.size(); i++) {
            valores[i][0] = tar.get(i).getLinha();      // primeira coluna "Linha"
            valores[i][1] = tar.get(i).getClasse();
            valores[i][2] = df.format((tar.get(i).getProbabilidade()*100));      
            valores[i][3] = df.format(jac.get(i).getProbabilidade()*100);     
            valores[i][4] = df.format(och.get(i).getProbabilidade()*100);      
            valores[i][5] = df.format(sbi.get(i).getProbabilidade()*100);      

        }
        return valores;
    }
    
     private void lAjudaMouseClicked(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Nesta tela são apresentados o resultado do cálculo da localização de defeitos\n"
                + "* O gráfico apresentado mostra as linhas mais propensas a conter o defeito de acordo com cada heurística\n"
                + "selecionada na página anterior.\n"
                + "* A tabela apresenta todas as linhas apontadas pelo programa. As linhas são exibidas em ordem decrescente de\n"
                + " prioridade, sendo assim, as primeiras linhas apresentadas são as que possuem maior probabilidade de conter o defeito.\n"
                + "     - A 1º coluna apresenta a classe;\n"
                + "     - A 2º coluna apresenta a linha que contém o provável defeito da prespectiva classe;\n"
                + "     - As 3º, 4º e 5º colunas apresentam a probabilidade linha conter o defeito.\n");
    } 
}
