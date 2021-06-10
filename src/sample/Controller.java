package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import sample.nn.math.MathHandler;

import java.util.Random;

public class Controller {

    @FXML
    TextField input1, input2, input3, prefOutFalse, prefOutTrue;//Input for the input neuron

    @FXML
    Label neuron1In, neuron1Out, neuron2In, neuron2Out, neuron3In, neuron3Out,
            neuron4In, neuron4Out, neuron5In, neuron5Out, neuron6In, neuron6Out,
            neuron7In, neuron7Out, neuron8In, neuron8Out,
            finalOutputTrue,finalOutputFalse;

    //Weights between input and hidden layer
    @FXML
    Line weight11, weight12, weight13, weight21, weight22, weight23, weight31, weight32, weight33;


    //current step in calculation
    private int steps = 0;

    //weights between input and hidden layer
    private double[] weights1 = new double[3];//weights1 are all from hidden1 to all input neurons
    private double[] weights2 = new double[3];
    private double[] weights3 = new double[3];

    private double[] weights4 = new double[3];
    private double[] weights5 = new double[3];
    private double[] weights6 = new double[3];

    @FXML
    protected void stepForward(){
        if(steps == 0){
            //receiving input values
            neuron1In.setText(input1.getText());
            neuron2In.setText(input2.getText());
            neuron3In.setText(input3.getText());
        }
        else if(steps == 1){
            //Calculated result
            neuron1Out.setText(""+MathHandler.activationFunction(Double.parseDouble(neuron1In.getText())));
            neuron2Out.setText(""+MathHandler.activationFunction(Double.parseDouble(neuron2In.getText())));
            neuron3Out.setText(""+MathHandler.activationFunction(Double.parseDouble(neuron3In.getText())));
        }
        else if(steps == 2){
            double[] inputs = new double[3];
            inputs[0] = Double.parseDouble(neuron1Out.getText());
            inputs[1] = Double.parseDouble(neuron2Out.getText());
            inputs[2] = Double.parseDouble(neuron3Out.getText());

            neuron4In.setText(MathHandler.uebertrag(weights1, inputs)+"");
            neuron5In.setText(MathHandler.uebertrag(weights2, inputs)+"");
            neuron6In.setText(MathHandler.uebertrag(weights3, inputs)+"");
        }
        else if(steps == 3){
            neuron4Out.setText(""+MathHandler.activationFunction(Double.parseDouble(neuron4In.getText())));
            neuron5Out.setText(""+MathHandler.activationFunction(Double.parseDouble(neuron5In.getText())));
            neuron6Out.setText(""+MathHandler.activationFunction(Double.parseDouble(neuron6In.getText())));
        }
        else if(steps == 4){
            double[] inputs = new double[3];
            inputs[0] = Double.parseDouble(neuron4Out.getText());
            inputs[1] = Double.parseDouble(neuron5Out.getText());
            inputs[2] = Double.parseDouble(neuron6Out.getText());

            neuron7In.setText(MathHandler.uebertrag(weights4, inputs)+"");
            neuron8In.setText(MathHandler.uebertrag(weights5, inputs)+"");
        }
        else if(steps == 5){
            neuron7Out.setText(""+MathHandler.activationFunction(Double.parseDouble(neuron7In.getText())));
            neuron8Out.setText(""+MathHandler.activationFunction(Double.parseDouble(neuron8In.getText())));

            finalOutputTrue.setText(neuron7Out.getText());
            finalOutputFalse.setText(neuron8Out.getText());
            steps = 0;

            clearNetwork();
            return;
        }
        steps++;
    }

    private int backStep = 0;

    @FXML
    protected void setBackward(){
        double outTrue = Double.parseDouble(prefOutTrue.getText());
        double outFalse = Double.parseDouble(prefOutFalse.getText());

        System.out.println(outTrue-calculateNetwork());
        while((outTrue-calculateNetwork()) > MathHandler.e) {
            // if(backStep == 0){
            System.out.println(weights4[0]);
            double deltaWeight = MathHandler.deltaWeightOutput(outTrue, calculateNetwork());
            for (int i = 0; i < weights4.length; i++) {
                weights4[i] += deltaWeight;
                weights5[i] += deltaWeight;
                weights6[i] += deltaWeight;

                System.out.println(weights4[i]);
            }

            //          if(backStep == 1) {
            deltaWeight = MathHandler.deltaWeightHidden(weights1, outTrue, calculateNetwork());
            for (int i = 0; i < weights1.length; i++) {
                weights1[i] += deltaWeight;
            }

            deltaWeight = MathHandler.deltaWeightHidden(weights2, outTrue, calculateNetwork());
            for (int i = 0; i < weights2.length; i++) {
                weights2[i] += deltaWeight;
            }

            deltaWeight = MathHandler.deltaWeightHidden(weights3, outTrue, calculateNetwork());
            for (int i = 0; i < weights3.length; i++) {
                weights3[i] += deltaWeight;
            }
            System.out.println("Finished backpropagation");
            backStep = 0;
            //return;
            //  }
            //}
        }
        clearNetwork();
        backStep++;
    }

    @FXML
    protected void setRndInputs(){
        Random r = new Random();
        int range= 15;
        input1.setText(r.nextInt(range)+"");
        input2.setText(r.nextInt(range)+"");
        input3.setText(r.nextInt(range)+"");
    }

    @FXML
    protected void setRndWeights(){
        for(int i= 0; i < weights1.length; i++){
            weights1[i] = Math.random();
            weights2[i] = Math.random();
            weights3[i] = Math.random();
        }
        for(int i= 0; i < weights4.length; i++){
            weights4[i] = Math.random();
            weights5[i] = Math.random();
            weights6[i] = Math.random();
        }

    }

    private double calculateNetwork(){
        //receiving input values
        neuron1In.setText(input1.getText());
        neuron2In.setText(input2.getText());
        neuron3In.setText(input3.getText());

        //Calculated result
        neuron1Out.setText(""+MathHandler.activationFunction(Double.parseDouble(neuron1In.getText())));
        neuron2Out.setText(""+MathHandler.activationFunction(Double.parseDouble(neuron2In.getText())));
        neuron3Out.setText(""+MathHandler.activationFunction(Double.parseDouble(neuron3In.getText())));

        double[] inputs = new double[3];
        inputs[0] = Double.parseDouble(neuron1Out.getText());
        inputs[1] = Double.parseDouble(neuron2Out.getText());
        inputs[2] = Double.parseDouble(neuron3Out.getText());

        neuron4In.setText(MathHandler.uebertrag(weights1, inputs)+"");
        neuron5In.setText(MathHandler.uebertrag(weights2, inputs)+"");
        neuron6In.setText(MathHandler.uebertrag(weights3, inputs)+"");

        neuron4Out.setText(""+MathHandler.activationFunction(Double.parseDouble(neuron4In.getText())));
        neuron5Out.setText(""+MathHandler.activationFunction(Double.parseDouble(neuron5In.getText())));
        neuron6Out.setText(""+MathHandler.activationFunction(Double.parseDouble(neuron6In.getText())));

        inputs[0] = Double.parseDouble(neuron4Out.getText());
        inputs[1] = Double.parseDouble(neuron5Out.getText());
        inputs[2] = Double.parseDouble(neuron6Out.getText());

        neuron7In.setText(MathHandler.uebertrag(weights4, inputs)+"");
        neuron8In.setText(MathHandler.uebertrag(weights5, inputs)+"");
        neuron7Out.setText(""+MathHandler.activationFunction(Double.parseDouble(neuron7In.getText())));
        neuron8Out.setText(""+MathHandler.activationFunction(Double.parseDouble(neuron8In.getText())));

        finalOutputTrue.setText(neuron7Out.getText());
        finalOutputFalse.setText(neuron8Out.getText());

        return Double.parseDouble(finalOutputTrue.getText());

    }

    private void clearNetwork(){
        neuron1In.setText("In");
        neuron1Out.setText("Out");

        neuron2In.setText("In");
        neuron2Out.setText("Out");

        neuron3In.setText("In");
        neuron3Out.setText("Out");

        neuron4In.setText("In");
        neuron4Out.setText("Out");

        neuron5In.setText("In");
        neuron5Out.setText("Out");

        neuron6In.setText("In");
        neuron6Out.setText("Out");

        neuron7In.setText("In");
        neuron7Out.setText("Out");

        neuron8In.setText("In");
        neuron8Out.setText("Out");
    }
}
