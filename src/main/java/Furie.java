public class Furie {
    private double sin[]= new double[80];
    private double cos[]= new double[80];
    private double bufferReal[]= new double[80];
    private double bufferImg[]= new double[80];
    private double sigXs = 0.0,sigX = 0.0;
    private double sigYs= 0.0, sigY = 0.0;
    private int numReal = 0;
    private int numImg = 0;
    private double kf =(double) Math.sqrt(2)/80;
    private int count = 0;

    public Furie() {
        for (int i=0;i<80; i++) {
            sin[i]=Math.sin(2*Math.PI*i/80);
            cos[i]=Math.cos(2*Math.PI*i/80);
        }
    }
    public double getReal(double instValue){
        sigXs=sigXs+ instValue*cos[numReal]-bufferReal[numReal]*cos[numReal];
        sigX=sigXs*kf;
        bufferReal[numReal] = instValue;
        numReal++;
        if (numReal ==80) numReal =0;
        return sigX;
    }
    public double getImaginary(double instValue){
        sigYs=sigYs +instValue*sin[numImg]-bufferImg[numImg]*sin[numImg];
        sigY=sigYs*kf;
        bufferImg[numImg] = instValue;
        numImg++;

        count++;
        if (numImg ==80) numImg =0;
        return sigY;
    }
    public boolean wait80(){
        if (count>=80){
            return true;
        }else{
            return false;
        }
    }

}