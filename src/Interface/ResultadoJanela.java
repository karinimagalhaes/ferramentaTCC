/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JFrame;
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
    DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
    private String classe;
    
    public ResultadoJanela() {
        super("Resultado");
        
        JFreeChart chart = gerarGrafico(dataSet, classe);
        this.add(new ChartPanel(chart));
        this.pack();
        
       /* //Cria um dataSet para inserir os dados que serão passados para a criação do grafico tipo Pie
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //gerar um gráfico para cada classe
        
        // (probabilidade, heuristica, linha)
        dataset.addValue(6, "Preto", "Corsa");
dataset.addValue(4, "Preto", "Fiesta");
dataset.addValue(3, "Preto", "Gol");
dataset.addValue(5, "Vermelho", "Corsa");
dataset.addValue(2, "Vermelho", "Fiesta");
dataset.addValue(3, "Vermelho", "Gol");
dataset.addValue(2, "Azul", "Corsa");
dataset.addValue(8, "Azul", "Fiesta");
dataset.addValue(1, "Azul", "Gol");

//Cria um objeto JFreeChart passando os seguintes parametros
        JFreeChart grafico = ChartFactory.createBarChart(
                "Título", 
                "Veículo",    // eixo x 
                "Quantidade",   // eixo y 
                dataset, 
                PlotOrientation.VERTICAL, 
                true,   // exibir legenda
                false, // exibir tooltip
                false   // exibir url
        );

        this.add(new ChartPanel(grafico));
        
        this.pack();*/
    }

    public static void inicializaJanela() {
        new ResultadoJanela().setVisible(true);
    }
    

    
    public void gerarDataSet(TreeMap<String, Resultado> tar, TreeMap<String, Resultado> jac,
            TreeMap<String, Resultado> och, TreeMap<String, Resultado> sbi){
        int i=0;
        for (Map.Entry<String, Resultado> probEntry : tar.entrySet()) {
            classe = probEntry.getValue().getClasse();
            dataSet.addValue(probEntry.getValue().getProbabilidade(), "Tarantula", Integer.toString(probEntry.getValue().getLinha()));
            if(i==8)
                break;
        }
        i=0;
        for (Map.Entry<String, Resultado> probEntry : jac.entrySet()) {  
            dataSet.addValue(probEntry.getValue().getProbabilidade(), "Jaccard", Integer.toString(probEntry.getValue().getLinha()));
            if(i==8)
                break;
        }
        i=0;
        for (Map.Entry<String, Resultado> probEntry : och.entrySet()) {  
            dataSet.addValue(probEntry.getValue().getProbabilidade(), "Ochiai", Integer.toString(probEntry.getValue().getLinha()));
            if(i==8)
                break;
        }
        i=0;
        for (Map.Entry<String, Resultado> probEntry : sbi.entrySet()) {  
            dataSet.addValue(probEntry.getValue().getProbabilidade(), "SBI", Integer.toString(probEntry.getValue().getLinha()));
            if(i==8)
                break;
        } 
    }
    
    public JFreeChart gerarGrafico(CategoryDataset dataSet, String classe){
        JFreeChart chart = ChartFactory.createBarChart(
            "Resultado da Localização para "+classe, //Titulo
            "Heurística", // Eixo X
            "Probabilidade", //Eixo Y
            dataSet, // Dados para o grafico
            PlotOrientation.VERTICAL, //Orientacao do grafico
            true, 
            true, 
            false); // exibir: legendas, tooltips, url
        return chart;
    }
}
