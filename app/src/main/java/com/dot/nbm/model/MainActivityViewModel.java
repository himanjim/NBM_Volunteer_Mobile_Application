package com.dot.nbm.model;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private String technology;

    private String operatorName;

    private float signalStrength;

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
}
