/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weathergui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.toedter.calendar.JDateChooser;
import java.awt.Button;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 *
 * @author iDesign
 */
public class WeatherGUI extends JApplet {
    ArrayList md = new ArrayList(); // Array List to get all Data From requested URL
    ArrayList timed= new ArrayList();// to collect time record
    ArrayList tempd = new ArrayList();// Array list to get only temperature data
    ArrayList pressd = new ArrayList();// Array list to get only atmo. pressure data
    ArrayList windd = new ArrayList();// Array list to get only wind speed data
    ArrayList precid = new ArrayList();// Array list to get only precipitation data
    String[] locationStrings = { "Silverstone", "Turweston", "Nottingham" };// String Array to Store Location
    JComboBox jComboBox1= new JComboBox(locationStrings);// Bind Location Array String in Combox Box
    // Get Data In correct Formate in JDataChooser
    com.toedter.calendar.JDateChooser jDateChooser1 = new JDateChooser();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");// Format of Date
    JFrame a=new JFrame();// Main Frame
    JFrame b= new JFrame();// Second Frame
    
    public static void main(String[] args) {
       WeatherGUI obj= new WeatherGUI();
       obj.MainINterface();
    }
    public void MainINterface()
    {
    JPanel panel= new JPanel();
    JPanel centerPanel = new JPanel();// Panel in Frame 'a' (Already Declared)
    Button button1= new Button("Get Information");// Button on main Frame
    jDateChooser1.setSize(100,20);// applying size on data chooser
    JLabel label1= new JLabel("Weather Report of England");
    JLabel label2= new JLabel("Select Location");
    JLabel label3= new JLabel("Select Date");
    a.setTitle("Weather Information Application");// Set title to main Frame 'a'
    a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// on closing the window exit application
    centerPanel.setVisible(true);// making visibility true for centerPanel
    // adding various components in panel and centerPanel
    panel.add(label1);
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
    centerPanel.add(label2);
    centerPanel.add(jComboBox1);
    centerPanel.setBackground(Color.orange);
    centerPanel.add(label3);
    centerPanel.add(jDateChooser1);
    centerPanel.add(button1);
    // adding components in main Frame 'a'
    a.add(panel, BorderLayout.NORTH);
    a.add(centerPanel, BorderLayout.CENTER);
    a.setSize(500, 120);
    a.setResizable(false);
    a.setLocationRelativeTo(null);
    a.setVisible(true);
    // Button Click Event handeling
    button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    // method calling
                    button1ActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(WeatherGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Button click operation is handeled in this function
     private void button1ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {  
         //Remove all elements from array list so that previous added data can't affect this
         md.clear();
         // Declearing Date object 
     Date date;
     // Assigning Date to given object from JDateChooser
        date=jDateChooser1.getDate();
        // Vailidation of date
        if(date==null)
        {
            JOptionPane.showMessageDialog(null, "Please select Date!");
            jDateChooser1.grabFocus();
        }
        else
        {
            // Now check wheather Date is of future 
            Calendar cal = Calendar.getInstance();
            if(date.compareTo(cal.getTime())>0)
            {
                JOptionPane.showMessageDialog(null, "Wrong Date Selected");
            }
            // assigning corresponding area code to selected location 
            else
            {
                String LocationCode;
                if(jComboBox1.getSelectedItem().toString().equals("Silverstone"))
                {
                    LocationCode="EGBV";
                }
                else
                {
                    if(jComboBox1.getSelectedItem().toString().equals("Turweston"))
                        {
                             LocationCode="EGBT";
                        }
                    else
                    {
                        LocationCode="EGBK";
                    }
                }
                // making URL address according to selected date and location
                String address="http://www.wunderground.com/history/airport/"+LocationCode+"/"+dateFormat.format(date)+"/DailyHistory.html?HideSpecis=1&format=1";
                DataParsing(address);// passing URL to DataParsing methos to get data
            }
        }
     }
     public void DataParsing(String address)
     {
         try
         {
             // downloading requested page from Weather UnderGround server
                URL page = new URL(address);
                //StringBuffer text = new StringBuffer();
                HttpURLConnection conn = (HttpURLConnection) page.openConnection();
                conn.connect();
                InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
                BufferedReader buff = new BufferedReader(in);
                
                String line,k;
                do {
                     line = buff.readLine();
                     if(line!=null)
                     {
                         for(int i=1; i<13;i++){
                         
                             // passing line to str_piece method to get data saperate by ','
                             k=str_piece(line, ',', i);
                             //Normalization Process Starts here for
                             //-9999.0
                             //-9999
                             //N/A and
                             //-
                             // if extra condition want to apply you can add more as string because 'k' is of string type
                             if(Arrays.asList("N/A", "-9999.0","-9999","-","Calm","").contains(k))
                             {
                                 // adding value to ArrayList 'md' declared on the top of program
                                 md.add("0");
                             }
                             else
                             {
                                 // adding value to ArrayList 'md' declared on the top of program
                                 md.add(k);
                             }


                            }
                       
                     }
                  
                  } while (line != null);
                //JOptionPane.showMessageDialog(null, md.toString());
         }
         catch (IOException ex)
                 {
                      JOptionPane.showMessageDialog(null, ex.toString());
                 }
         
         if(md.size()<=36)
         {
             JOptionPane.showMessageDialog(null, "No data is avaiblable for this location and date");
         }
         else
         {
             collect_saperate_data();
         }
                
     }
     private static String str_piece(String str, char separator, int index) {
        String str_result = "";
        int count = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == separator) {
                count++;
                if(count == index) {
                    break;
                }
            }
            else {
                if(count == index-1) {
                    str_result += str.charAt(i);
                }
            }
        }
        return str_result;
    }
     public void collect_saperate_data()
     {
         tempd.clear();
         // get bigger loop counter to apply looping
         int counter=md.size()/12;
         int loopcounter=counter-2;
         //JOptionPane.showMessageDialog(null, counter);
         for(int i=1;i<=loopcounter;i++)
         {
             timed.add(md.get((12*(i+1))));
             tempd.add(md.get((13*(i+1)-i)));
             pressd.add(md.get((16*(i+1))-(4*i)));
             windd.add(md.get(((16*(i+1))-(4*i)+3)));
             precid.add(md.get(((16*(i+1))-(4*i)+5)));
         }
       // JOptionPane.showMessageDialog(null, windd.toArray());
         draw_chart();
         //show_graph();
     }
     
     public void draw_chart()
     {
         a.dispose();
         b.setTitle("testing");
        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);
 
        b.setSize(640, 480);
        b.add(chartPanel);
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.setLocationRelativeTo(null);
        b.setVisible(true);
        //return b;
     }
      private JPanel createChartPanel() {
        // creates a line chart object
        // returns the chart panel
            String chartTitle = "Weather Report Chart";
            String categoryAxisLabel = "Time Frame from Mind Night";
            String valueAxisLabel = "Data";
           
            CategoryDataset dataset = createDataset();

            JFreeChart chart = ChartFactory.createLineChart(chartTitle, categoryAxisLabel, valueAxisLabel, dataset);
//              CategoryPlot plot = chart.getCategoryPlot();
//              LineAndShapeRenderer renderer = new LineAndShapeRenderer();
// 
//// sets paint color for each series
//renderer.setSeriesPaint(0, Color.RED);
//renderer.setSeriesPaint(1, Color.GREEN);
//renderer.setSeriesPaint(2, Color.BLUE);
//renderer.setSeriesPaint(3, Color.YELLOW);
// 
//// sets thickness for series (using strokes)
//renderer.setSeriesStroke(0, new BasicStroke(4.0f));
//renderer.setSeriesStroke(1, new BasicStroke(3.0f));
//renderer.setSeriesStroke(2, new BasicStroke(2.0f));
//renderer.setSeriesStroke(3, new BasicStroke(1.5f));
// 
//plot.setRenderer(renderer);
            return new ChartPanel(chart);
    }
      private CategoryDataset createDataset() {
        // creates chart dataset...
        // returns the dataset
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    String series1 = "Temperature";
    String series2 = "Pressure";
    String series3 = "Wind Speed";
    String series4 = "Total Precipitation";
     for (int i=1;i<timed.size();i=i+2)
     {
         dataset.addValue(Double.parseDouble(tempd.get(i).toString()), series1, timed.get(i).toString());
         dataset.addValue(Double.parseDouble(pressd.get(i).toString())/100, series2, timed.get(i).toString());
         dataset.addValue(Double.parseDouble(windd.get(i).toString()), series3, timed.get(i).toString());
         dataset.addValue(Double.parseDouble(precid.get(i).toString()), series4, timed.get(i).toString());
     }

    return dataset;
    }
      
      public void show_graph()
      {
          JPanel j1= new JPanel();
          j1.setSize(600, 200);
          j1.setBackground(Color.red);
          j1.setLocation(20,20);
          JPanel j2= new JPanel();
          j2.setSize(600, 200);
          j2.setBackground(Color.green);
          j2.setLocation(20,240);
          JPanel j3= new JPanel();
          j3.setSize(600, 200);
          j3.setLocation(20,450);
          j3.setBackground(Color.red);
          JPanel j4= new JPanel();
          j4.setSize(600, 200);
          j4.setBackground(Color.white);
          j4.setLocation(20,800);
        b.setSize(660, 800);
        b.add(j1);
        b.add(j2);
        b.add(j3);
        b.add(j4);
    
        //b.pack();
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.setLocationRelativeTo(null);
        b.setVisible(true);
      }
}
    
   