public class TZNPrele{
    //Ustavki
    private double i0ust = 800;
    private boolean trip = false;
    private double wMaxSen = 65*Math.PI/180;



    public boolean ReleTZNP(double I0_real, double I0_imag,double U0_real,double U0_imag) {

        //reverse U0 for finding power flow direction
        U0_imag = - U0_imag;
        U0_real = - U0_real;

        //Finding mod I0
        double Ideistv = Math.sqrt(Math.pow(I0_imag, 2)+Math.pow(I0_real, 2));

        //Finding and correction U0 and IO angle
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
        //Finding phase between U0 and I0
        double w0 = wU-wI;


        //Comparision turn on trip and ust
            if (Ideistv*Math.cos(w0-wMaxSen)>i0ust && Ideistv>i0ust) {
                trip = true;
            }



        return trip;

    }

}