package com.dot.nbm.model;

import android.text.Spanned;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {


    private List<Spanned> signals = new ArrayList<>();

    private int noOfContributions;

    public int getNoOfContributions() {
        return noOfContributions;
    }

    public void setNoOfContributions(int noOfContributions) {
        this.noOfContributions = noOfContributions;
    }

    public List<Spanned> getSignals() {
        return signals;
    }

    public void setSignals(List<Spanned> signals) {
        this.signals = signals;
    }

    //    public void setSignals(List<Spanned> signals) {
//        this.signals = signals;
//    }

    @NonNull
    @Override
    public String toString() {
        return "MainActivityViewModel{" +
                "signals=" + signals +
                ", noOfContributions=" + noOfContributions +
                '}';
    }

}
