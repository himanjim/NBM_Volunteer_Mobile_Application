package com.dot.nbm.model;

import java.io.Serializable;
import java.util.Objects;

public class SignalState implements Serializable, Comparable<SignalState> {

    public SignalState() {

    }

    private String operatorName;

    private int signalStrength;

    private String technology;

    private int channelNo;

    private String generation;

    private long cellId;

    private int locationAreaCode;

    private int baseStationIdentityCode;

    private int trackingAreaCode;

    private int physicalCellId;

    private int systemId;

    private int networkId;

    private int level;

    private int cpid;

    public int getCpid() {
        return cpid;
    }

    public void setCpid(int cpid) {
        this.cpid = cpid;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getCellId() {
        return cellId;
    }

    public void setCellId(long cellId) {
        this.cellId = cellId;
    }

    public int getLocationAreaCode() {
        return locationAreaCode;
    }

    public void setLocationAreaCode(int locationAreaCode) {
        this.locationAreaCode = locationAreaCode;
    }

    public int getBaseStationIdentityCode() {
        return baseStationIdentityCode;
    }

    public void setBaseStationIdentityCode(int baseStationIdentityCode) {
        this.baseStationIdentityCode = baseStationIdentityCode;
    }

    public int getTrackingAreaCode() {
        return trackingAreaCode;
    }

    public void setTrackingAreaCode(int trackingAreaCode) {
        this.trackingAreaCode = trackingAreaCode;
    }

    public int getPhysicalCellId() {
        return physicalCellId;
    }

    public void setPhysicalCellId(int physicalCellId) {
        this.physicalCellId = physicalCellId;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public int getNetworkId() {
        return networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SignalState)) return false;
        SignalState that = (SignalState) o;
        return getSignalStrength() == that.getSignalStrength() && Objects.equals(getOperatorName(), that.getOperatorName()) && Objects.equals(getTechnology(), that.getTechnology());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOperatorName(), getSignalStrength(), getTechnology());
    }

    @Override
    public String toString() {
        return "SignalState{" +
                ", operaterName='" + operatorName + '\'' +
                ", signalStrength=" + signalStrength +
                ", technology='" + technology + '\'' +
                ", channelNo=" + channelNo +
                ", generation='" + generation + '\'' +
                ", cellId=" + cellId +
                ", locationAreaCode=" + locationAreaCode +
                ", baseStationIdentityCode=" + baseStationIdentityCode +
                ", trackingAreaCode=" + trackingAreaCode +
                ", physicalCellId=" + physicalCellId +
                ", systemId=" + systemId +
                ", networkId=" + networkId +
                ", level=" + level +
                ", cpid=" + cpid +
                '}';
    }

    @Override
    public int compareTo(SignalState o) {
        return o.getSignalStrength() - this.getSignalStrength();
    }
}
