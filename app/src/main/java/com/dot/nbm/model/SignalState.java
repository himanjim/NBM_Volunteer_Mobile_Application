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

    private int cellId;

    private int locationAreaCode;

    private int baseStationIdentityCode;

    private int trackingAreaCode;

    private int physicalCellId;

    private int systemId;

    private int networkId;

    public int getCellId() {
        return cellId;
    }

    public void setCellId(int cellId) {
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
                ", cellId=" + cellId +
                ", locationAreaCode=" + locationAreaCode +
                ", baseStationIdentityCode=" + baseStationIdentityCode +
                ", trackingAreaCode=" + trackingAreaCode +
                ", physicalCellId=" + physicalCellId +
                ", systemId=" + systemId +
                ", networkId=" + networkId +
                '}';
    }
}
