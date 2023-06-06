package com.dot.nbm.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {


    private List<String> signals = new ArrayList<>();

    private int noOfContributions;

    public int getNoOfContributions() {
        return noOfContributions;
    }

    public void setNoOfContributions(int noOfContributions) {
        this.noOfContributions = noOfContributions;
    }

    public List<String> getSignals() {
        return signals;
    }

    public void setSignals(List<String> signals) {
        this.signals = signals;
    }

    @Override
    public String toString() {
        return "MainActivityViewModel{" +
                "signals=" + signals +
                ", noOfContributions=" + noOfContributions +
                '}';
    }

}
