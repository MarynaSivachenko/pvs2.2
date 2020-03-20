package l2;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class FPF {
    public static double m = 0;
    public static double d = 0;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Charts");

                frame.setSize(600, 400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                XYDataset ds = createDataset();

                JFreeChart chart = ChartFactory.createXYLineChart("F(p)",
                        "p", "F", ds, PlotOrientation.VERTICAL, true, true,
                        false);


                ChartPanel cp = new ChartPanel(chart);
                JPanel data = new JPanel();

                JLabel label = new JLabel();
                data.add(label);

                Container pane = frame.getContentPane();

                pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

                pane.add(cp);
                pane.add(data);

            }
        });

    }

    private static XYDataset createDataset() {
        int n = 6;
        int w = 2100;
        int nn = 64;
        double sum = 0;
        double a;
        double f;

        double[] dataN = new double[nn];
        double[] dataX = new double[nn];
        double[] dataF = new double[nn];



        for (int t = 0; t < nn; t++) {
            sum = 0;
            a = (Math.random());
            f = (Math.random());
            for (int x = 0; x < n; x++) {
                sum += a * Math.sin(w*nn + f);
            }
            dataN[t] = t;
            dataX[t] = sum;

        }

        for (int p = 0; p < nn; p++) {
            double Fi1 = 0.0;
            double Fr1 = 0.0;
            double Fi2 = 0.0;
            double Fr2 = 0.0;
            for (int k = 0; k < (nn / 2) - 1; k++) {
                Fi1 += dataX[2 * k] * Math.sin(2 * p * k * Math.PI / (nn / 2));
                Fr1 += dataX[2 * k] * Math.cos(2 * p * k * Math.PI / (nn / 2));
                Fi2 += dataX[2 * k + 1] * Math.sin(2 * p * (2 * k + 1) * Math.PI / nn);
                Fr2 += dataX[2 * k + 1] * Math.cos(2 * p * (2 * k + 1) * Math.PI / nn);
            }
            dataF[p] = Math.sqrt(Math.pow(Fr1 + Fr2, 2) + Math.pow(Fi1 + Fi2, 2));
        }

        //double[][] data2 = { {0.4, 0.233, 0.88}, {5, 3, 1} };


        XYSeriesCollection ds = new XYSeriesCollection();


        XYSeries s1 = new XYSeries("s1");
        // XYSeries s2 = new XYSeries("s1");

        for (int i = 0; i < dataN.length; i++) {
            s1.add(dataN[i], dataF[i]);
            // s2.add(data2[i][0], data2[i][1]);
        }

        ds.addSeries(s1);
        //ds.addSeries(s2);

        return ds;
    }
}
