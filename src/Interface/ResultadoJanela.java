/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
public class ResultadoJanela extends JFrame {
   
    public ResultadoJanela(List<Resultado> tar, List<Resultado> jac, List<Resultado> och, List<Resultado> sbi) {
        super("Resultado");
        CategoryDataset dataset;
        JFreeChart chart;
        dataset = gerarDataset(tar);
        chart = gerarGrafico(dataset);
        JFrame frame = new JFrame("Chart");
        frame.getContentPane().add(new ChartPanel(chart), BorderLayout.WEST);
        frame.setSize(12, 16);
        
        dataset = gerarDataset(jac);
        chart = gerarGrafico(dataset);
        frame.getContentPane().add(new ChartPanel(chart), BorderLayout.EAST);
        frame.setSize(12, 16);
        
        dataset = gerarDataset(och);
        chart = gerarGrafico(dataset);
        frame.getContentPane().add(new ChartPanel(chart), BorderLayout.SOUTH);
        frame.setSize(12, 16);
        
        dataset = gerarDataset(jac);
        chart = gerarGrafico(dataset);
        frame.getContentPane().add(new ChartPanel(chart), BorderLayout.NORTH);
        frame.setSize(12, 16);
        frame.pack();

    }

    public static void inicializaJanela(List<Resultado> tar, List<Resultado> jac, List<Resultado> och, List<Resultado> sbi) {
        new ResultadoJanela(tar, jac, och, sbi).setVisible(true);
    }
    
    public JFreeChart gerarGrafico(CategoryDataset dataSet){
        JFreeChart chart = ChartFactory.createBarChart(
            "Resultado da Localização para classe", //Titulo
            "Heurística", // Eixo X
            "Probabilidade", //Eixo Y
            dataSet, // Dados para o grafico
            PlotOrientation.VERTICAL, //Orientacao do grafico
            true, 
            true, 
            false); // exibir: legendas, tooltips, url
        return chart;
    }
    
    private static CategoryDataset gerarDataset(List<Resultado> resultado) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0; i<8; i++){
            dataset.addValue(resultado.get(i).getProbabilidade(), resultado.get(i).getHeuristica(), Integer.toString(resultado.get(i).getLinha()));
        }
        return dataset;
    }
}
