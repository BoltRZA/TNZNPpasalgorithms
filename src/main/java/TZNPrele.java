public class TZNPrele{
    //Ustavki
    private double u0ust = 100;
    private double i0ust = 100;
    private boolean trip = false;
    private double wMaxSen = 65*Math.PI/180;



    public boolean ReleTZNP(double I0_real, double I0_imag,double U0_real,double U0_imag) {

        double U0 = Math.sqrt(Math.pow(U0_imag, 2)+Math.pow(U0_real, 2));
        double wI = Math.atan(I0_imag/I0_real);

        double wU = Math.atan(U0_imag/U0_real);


        double w0 = Math.atan(I0_imag/I0_real)-Math.atan(U0_imag/U0_real);


        double Ideistv = Math.sqrt(Math.pow(I0_imag, 2)+Math.pow(I0_real, 2));
            //System.out.println(Math.atan(I0_imag/I0_real)*180/Math.PI);

            if (Ideistv*Math.cos(w0-wMaxSen)>i0ust) {
                trip = true;
            }



        return trip;

    }

}