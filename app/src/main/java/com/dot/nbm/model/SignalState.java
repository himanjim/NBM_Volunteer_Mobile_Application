package com.dot.nbm.model;

import java.io.Serializable;

public class SignalState implements Serializable {

    public SignalState(long id) {
        this.id = id;
    }


    public SignalState() {

    }

    private long id;

    private String operaterName;

    private int signalStrength;

    private String technology;

    private int channelNo;

    private String generation;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOperaterName() {
        return operaterName;
    }

    public void setOperaterName(String operaterName) {
        this.operaterName = operaterName;
    }

    public int getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(int signalStrength) {
        this.signalStrength = signalStrength;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public int getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(int channelNo) {
        this.channelNo = channelNo;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    @Override
    public String toString() {
        return "SignalState{" +
                "id=" + id +
                ", operaterName='" + operaterName + '\'' +
                ", signalStrength=" + signalStrength +
                ", technology='" + technology + '\'' +
                ", channelNo=" + channelNo +
                ", generation='" + generation + '\'' +
                '}';
    }
}
