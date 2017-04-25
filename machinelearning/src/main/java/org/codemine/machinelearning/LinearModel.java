/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codemine.machinelearning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author BENCPCHAN
 */
public class LinearModel {

//    private final List<Double> features = new ArrayList<>();
    private List<Double> thetaList;

    public LinearModel() {

    }

    public void train(TrainingInputItem[] trainingInputList, double learningRate) throws Exception {
        thetaList = new ArrayList<>();
        for (int i = 0; i < trainingInputList[0].getFeatureList().length; ++i) {
            thetaList.add(0.0);
        }

        double totalCost = 0.0;
        while (true) {
            for (TrainingInputItem trainingInputItem : trainingInputList) {
                double actualOutput = predict(trainingInputItem.getFeatureList());
                double itemCost = (actualOutput - trainingInputItem.getExpectedOutput());
                totalCost += (itemCost * itemCost);
            }
            totalCost = totalCost / (2 * trainingInputList.length);
            System.out.println("TotalCost=" + totalCost);
            if (totalCost < 0.0000000001){
                return;
            }
            for (int i = 0; i < thetaList.size(); ++i) {
                double adjustedTheta = this.adjustTheta(trainingInputList, thetaList.get(i), i, learningRate);
                thetaList.set(i, adjustedTheta);
            }
        }

    }

    private double adjustTheta(TrainingInputItem[] trainingInputList, double theta, int featureIndex, double learningRate) throws Exception {
        double adjustment = 0.0;
        for (TrainingInputItem trainingInputItem : trainingInputList) {
            double actualOutput = predict(trainingInputItem.getFeatureList());
            double itemCost = (actualOutput - trainingInputItem.getExpectedOutput());
            adjustment += (itemCost * trainingInputItem.getFeatureList()[featureIndex]);
        }
        adjustment = adjustment * learningRate / trainingInputList.length;
        double newTheta = theta - adjustment;
        return newTheta;
    }

    public double predict(double[] featureList) throws Exception {
        if (featureList.length != thetaList.size()) {
            throw new Exception("Feature number mismatch for training & prediction");
        }
        if (featureList[0] != 1.0) {
            throw new Exception("The first bias feature must be 1.0");
        }
        if (thetaList.isEmpty()) {
            throw new Exception("Please train the model first");
        }

        double result = 0;
        for (int i = 0; i < featureList.length; ++i) {
            double feature = featureList[i];
            result += (feature * thetaList.get(i));
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        LinearModel linearModel = new LinearModel();
        linearModel.train(
                new TrainingInputItem[]{
                    new TrainingInputItem(new double[]{1.0, 1.0}, 1.0),
                    new TrainingInputItem(new double[]{1.0, 2.0}, 2.0),
                    new TrainingInputItem(new double[]{1.0, 3.0}, 3.0)
                }, 0.15);
        
        
        System.out.println(linearModel.predict(new double[]{1.0,4.0}));
        
        
        System.out.println(linearModel.predict(new double[]{1.0,23.0}));
    }

}
