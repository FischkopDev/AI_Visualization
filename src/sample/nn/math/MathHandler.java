package sample.nn.math;

public class MathHandler {

    public static double e = 0.01;

    public static double activationFunction(double x){
        return 1/(1+Math.pow(Math.E, -x));
    }

    public static double altActivationFunction(double x){
        return Math.pow(x,4)+x*x;
    }

    public static double uebertrag(double[] weights,double[] inputs){
        double tmp = 0;
        //System.out.println("Calculating for next input.");
        for(int i = 0; i < inputs.length; i++){
            tmp += weights[i]*inputs[i];
          //  System.out.println(tmp + " " + weights[i] + " " + inputs[i]);
        }
        return tmp;
    }

    public static double delta(double xSoll,double xIst){
        return activationFunction(xSoll)-activationFunction(xIst);
    }

    public static double deltaWeightOutput(double xSoll, double xIst){
            return e*delta(xSoll,xIst)*activationFunction(xIst);
    }

    public static double deltaWeightHidden(double[] weights, double xSoll, double xIst){
        double deltaOut = delta(xSoll, xIst);
        double deltaHidden = 0;

        for(int i = 0; i < weights.length; i++){
            deltaHidden+= deltaOut*weights[i];
        }
        return e*deltaHidden*activationFunction(xIst);
    }

    @Deprecated
    public static double deltaWeightInput(double[] weightsIn, double[] weightsHidden, double xSoll, double xIst){
        double deltaOut = delta(xSoll, xIst);
        double deltaHidden = 0;

        for(int i = 0; i < weightsHidden.length; i++){
            deltaHidden+= deltaOut*weightsHidden[i];
        }

        for(int i = 0; i< weightsIn.length; i++){

        }
        return e*deltaHidden*activationFunction(xIst);
    }
}
