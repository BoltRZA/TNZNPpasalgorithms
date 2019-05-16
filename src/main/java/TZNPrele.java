public class TZNPrele{
    //Ustavki
    private double u0ust = 100;
    private double i0Nom = 100;
    private double i0ust = 800;
    private boolean trip = false;
    private double wMaxSen = 65*Math.PI/180;



    public boolean ReleTZNP(double I0_real, double I0_imag,double U0_real,double U0_imag) {
        U0_imag = - U0_imag;
        U0_real = - U0_real;

        double U0 = Math.sqrt(Math.pow(U0_imag, 2)+Math.pow(U0_real, 2));
        double Ideistv = Math.sqrt(Math.pow(I0_imag, 2)+Math.pow(I0_real, 2));

        double wI = Math.atan(I0_imag/I0_real);
        double wU = Math.atan(U0_imag/U0_real);

        if (U0_imag < 0 && U0_real < 0){
            wU = wU +Math.PI;
        }
        if (I0_imag < 0 && I0_real < 0){
            wI = wI +Math.PI;
        }
        if (wI<0){
            wI = 2*Math.PI + wI;
        }
        if (wU<0){
            wU = 2*Math.PI + wU;
        }

        double w0 = wU-wI;

        System.out.println(Ideistv*Math.cos(w0-wMaxSen));
        System.out.println(wI*180/Math.PI+"////"+wU*180/Math.PI);
            //System.out.println(Math.atan(I0_imag/I0_real)*180/Math.PI);

            if (Ideistv*Math.cos(w0-wMaxSen)>i0Nom && Ideistv>i0ust) {
                trip = true;
            }



        return trip;

    }

}