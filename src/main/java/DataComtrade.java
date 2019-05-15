import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.*;

public class DataComtrade {
    private File comtrCfg;
    private File comtrDat;
    private BufferedReader br;
    private String line;
    private String[] lineData;
    private TZNPrele tznPrele;

    public void setTznPrele(TZNPrele tznPrele) {
        this.tznPrele = tznPrele;
    }

    private double k1[] = new double[6];
    private double k2[] = new double[6];
    private double[] realIs = new double[3];
    private double[] realUs = new double[3];
    private double[] imagIs = new double[3];
    private double[] imagUs = new double[3];
    private int trip = 0;

    private XYSeriesCollection dataset = new XYSeriesCollection();
    private XYSeries series = new XYSeries("");

    public DataComtrade(String path, String file) {
        comtrCfg = new File(path + file + ".cfg");
        comtrDat = new File(path + file + ".dat");
    }

    public void run() {
        try {
            br = new BufferedReader(new FileReader(comtrCfg));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                //	System.out.println(line);
                if (lineNum == 2) {
                    lineData = line.split(",");
                    k1[0] = Double.parseDouble(lineData[5]); // koefficient preobrazovaniya
                    k2[0] = Double.parseDouble(lineData[6]);

                }
                if (lineNum == 3) {
                    lineData = line.split(",");
                    k1[1] = Double.parseDouble(lineData[5]); // koefficient preobrazovaniya
                    k2[1] = Double.parseDouble(lineData[6]);
                }
                if (lineNum == 4) {
                    lineData = line.split(",");
                    k1[2] = Double.parseDouble(lineData[5]); // koefficient preobrazovaniya
                    k2[2] = Double.parseDouble(lineData[6]);
                }
                if (lineNum == 5) {
                    lineData = line.split(",");
                    k1[3] = Double.parseDouble(lineData[5]); // koefficient preobrazovaniya
                    k2[3] = Double.parseDouble(lineData[6]);
                }
                if (lineNum == 6) {
                    lineData = line.split(",");
                    k1[4] = Double.parseDouble(lineData[5]); // koefficient preobrazovaniya
                    k2[4] = Double.parseDouble(lineData[6]);
                }
                if (lineNum == 7) {
                    lineData = line.split(",");
                    k1[5] = Double.parseDouble(lineData[5]); // koefficient preobrazovaniya
                    k2[5] = Double.parseDouble(lineData[6]);
                }
                lineNum++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //_________________________________________________________________________________________________
        try {
            br = new BufferedReader(new FileReader(comtrDat));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Furie fI0 = new Furie();
        Furie fU0 = new Furie();

        Double Uamgn;
        Double Ubmgn;
        Double Ucmgn;
        Double Iamgn;
        Double Ibmgn;
        Double Icmgn;

        try {
            while ((line = br.readLine()) != null) {
                lineData = line.split(",");
                Uamgn = 1000* Double.parseDouble(lineData[2]) * k1[0] + k2[0];
                Ubmgn = 1000* Double.parseDouble(lineData[3]) * k1[1] + k2[1];
                Ucmgn = 1000* Double.parseDouble(lineData[4]) * k1[2] + k2[2];

                double U0 = Uamgn + Ucmgn + Ubmgn;


                Iamgn = 1000* Double.parseDouble(lineData[5]) * k1[3] + k2[3];
                Ibmgn = 1000*  Double.parseDouble(lineData[6]) * k1[4] + k2[4];
                Icmgn = 1000* Double.parseDouble(lineData[7]) * k1[5] + k2[5];

                double I0 = Iamgn + Ibmgn + Icmgn;


                realIs[0] = fI0.getReal(I0);
                imagIs[0] = fI0.getImaginary(I0);


                realUs[0] = fU0.getReal(U0);
                imagUs[0] = fU0.getImaginary(U0);
                Charts.addAnalogData(1,0,trip);
                Charts.addAnalogData(0,0, Math.sqrt(realIs[0]*realIs[0] + imagIs[0]*imagIs[0]));
                if (fU0.wait80() == true) {
                    if (tznPrele.ReleTZNP(realIs[0], imagIs[0], realUs[0], imagUs[0]) == true){
                        trip = 1;


                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
