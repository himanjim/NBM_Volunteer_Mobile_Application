package com.dot.nbm.model;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private String technology;

    private String operatorName;

    private float signalStrength;


    private int noOfContributions;

    public int getNoOfContributions() {
        return noOfContributions;
    }

    public void setNoOfContributions(int noOfContributions) {
        this.noOfContributions = noOfContributions;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public float getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(float signalStrength) {
        this.signalStrength = signalStrength;
    }

    @Override
    public String toString() {
        return "MainActivityViewModel{" +
                "technology='" + technology + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", signalStrength=" + signalStrength +
                ", noOfContributions=" + noOfContributions +
                '}';
    }
}
