package com.dot.nbm;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private String technology;

    private String operator;

    private float signalStrength;

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public float getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(float signalStrength) {
        this.signalStrength = signalStrength;
    }
}
