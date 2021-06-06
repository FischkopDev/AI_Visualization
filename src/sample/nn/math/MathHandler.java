package sample.nn.math;

public class MathHandler {

    public static double activationFunction(double x){
        return 1/(1+Math.pow(Math.E, -x));
    }

    public static double altActivationFunction(double x){
        return Math.pow(x,4)+x*x;
    }

    public static double uebertrag(double[] weights,double[] inputs){
        double tmp = 0;
        System.out.println("Calculating for next input.");
        for(int i = 0; i < inputs.length; i++){
            tmp += weights[i]*inputs[i];
            System.out.println(tmp + " " + weights[i] + " " + inputs[i]);
        }
        return tmp;
    }
}
