/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codemine.machinelearning;

/**
 *
 * @author BENCPCHAN
 */
public class TrainingInputItem {
    private final double[] featureList;
    private final double expectedOutput;

    public TrainingInputItem(double[] featureList, double expectedOutput) {
        this.featureList = featureList;
        this.expectedOutput = expectedOutput;
    }

    /**
     * @return the featureList
     */
    public double[] getFeatureList() {
        return featureList;
    }

    /**
     * @return the expectedOutput
     */
    public double getExpectedOutput() {
        return expectedOutput;
    }
}
