
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.toedter.calendar.JDateChooser;
import java.awt.Button;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;



public class LineChartWithCategoryDatasetExample extends JFrame {
 
    public LineChartWithCategoryDatasetExample() {
        super("Line Chart Example with JFreechart");
 
        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);
 
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
 
    private JPanel createChartPanel() {
        // creates a line chart object
        // returns the chart panel
        String chartTitle = "Programming Languages Trends";
    String categoryAxisLabel = "Interest over time";
    String valueAxisLabel = "Popularity";
 
    CategoryDataset dataset = createDataset();
 
    JFreeChart chart = ChartFactory.createLineChart(chartTitle,
            categoryAxisLabel, valueAxisLabel, dataset);
 
    return new ChartPanel(chart);
    }
 
    private CategoryDataset createDataset() {
        // creates chart dataset...
        // returns the dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    String series1 = "Java";
    String series2 = "PHP";
    String series3 = "C++";
    String series4 = "C#";
 
    dataset.addValue(5.0, series1, "2005");
    dataset.addValue(4.8, series1, "2006");
    dataset.addValue(4.5, series1, "2007");
    dataset.addValue(4.3, series1, "2008");
    dataset.addValue(4.0, series1, "2009");
    dataset.addValue(4.1, series1, "2010");
    dataset.addValue(4.2, series1, "2011");
    dataset.addValue(4.2, series1, "2012");
    dataset.addValue(4.0, series1, "2013");
 
    dataset.addValue(4.0, series2, "2005");
    dataset.addValue(4.2, series2, "2006");
    dataset.addValue(3.8, series2, "2007");
    dataset.addValue(3.6, series2, "2008");
    dataset.addValue(3.4, series2, "2009");
    dataset.addValue(3.4, series2, "2010");
    dataset.addValue(3.3, series2, "2011");
    dataset.addValue(3.1, series2, "2012");
    dataset.addValue(3.2, series2, "2013");
 
    dataset.addValue(3.6, series3, "2005");
    dataset.addValue(3.4, series3, "2006");
    dataset.addValue(3.5, series3, "2007");
    dataset.addValue(3.2, series3, "2008");
    dataset.addValue(3.2, series3, "2009");
    dataset.addValue(3.0, series3, "2010");
    dataset.addValue(2.8, series3, "2011");
    dataset.addValue(2.8, series3, "2012");
    dataset.addValue(2.6, series3, "2013");
 
    dataset.addValue(3.2, series4, "2005");
    dataset.addValue(3.2, series4, "2006");
    dataset.addValue(3.0, series4, "2007");
    dataset.addValue(3.0, series4, "2008");
    dataset.addValue(2.8, series4, "2009");
    dataset.addValue(2.7, series4, "2010");
    dataset.addValue(2.6, series4, "2011");
    dataset.addValue(2.6, series4, "2012");
    dataset.addValue(2.4, series4, "2013");
 
    return dataset;
    }
 
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LineChartWithCategoryDatasetExample().setVisible(true);
            }
        });
    }
    
}