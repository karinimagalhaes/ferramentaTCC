/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

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
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(chartPanel, BorderLayout.CENTER);
        
        
        JTable table = new JTable(criarValores(tar, jac, och, sbi), criarColunas());

        // Adiciona o JTable dentro do painel
        JScrollPane scrollPane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        panel.add(scrollPane, BorderLayout.SOUTH);
        
        
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.add(panel);
      
        frame.pack();
        frame.setVisible(true);
    }

    public static void inicializaJanela(List<Resultado> tar, List<Resultado> jac, List<Resultado> och, List<Resultado> sbi) {
        new ResultadoJanela(tar, jac, och, sbi);
    }
    
      public JFreeChart gerarGrafico(CategoryDataset dataSet){
        JFreeChart chart = ChartFactory.createBarChart(
            "Resultado da Localização de Defeitos", //Titulo
            "Heurística", // Eixo X
            "Probabilidade", //Eixo Y
            dataSet, // Dados para o grafico
            PlotOrientation.VERTICAL, //Orientacao do grafico
            true, 
            true, 
            true); // exibir: legendas, tooltips, url
        return chart;
    }
    
    private CategoryDataset gerarDataset(List<Resultado> tar, List<Resultado> jac, List<Resultado> och, List<Resultado> sbi) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i = 0; i<7; i++){
            dataset.addValue(tar.get(i).getProbabilidade(), tar.get(i).getHeuristica(), Integer.toString(tar.get(i).getLinha()));
            dataset.addValue(jac.get(i).getProbabilidade(), jac.get(i).getHeuristica(), Integer.toString(jac.get(i).getLinha()));
            dataset.addValue(och.get(i).getProbabilidade(), och.get(i).getHeuristica(), Integer.toString(och.get(i).getLinha()));
            dataset.addValue(sbi.get(i).getProbabilidade(), sbi.get(i).getHeuristica(), Integer.toString(sbi.get(i).getLinha()));
        }
        return dataset;
    }
 
        public Object[] criarColunas(){
        Object[] colunas = new Object[]{"Classe", "Linha", "Tarantula",  "Jaccard", "Ochiai", "SBI"};
        return colunas;
    }
    
        public Object[][] criarValores(List<Resultado> tar, List<Resultado> jac, List<Resultado> och, List<Resultado> sbi){
        Object[][] valores = new Object[tar.size()][12];
        for (int i = 0; i < tar.size(); i++) {
            valores[i][0] = tar.get(i).getClasse();      // primeira coluna "Linha"
            valores[i][1] = tar.get(i).getLinha();
            valores[i][2] = tar.get(i).getProbabilidade();      // primeira coluna "Linha"
            valores[i][3] = jac.get(i).getProbabilidade();      // primeira coluna "Linha"
            valores[i][4] = och.get(i).getProbabilidade();      // primeira coluna "Linha"
            valores[i][5] = sbi.get(i).getProbabilidade();      // primeira coluna "Linha"
           
        }
        return valores;
    }
}
