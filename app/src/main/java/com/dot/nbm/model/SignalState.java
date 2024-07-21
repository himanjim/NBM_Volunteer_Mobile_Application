package com.dot.nbm.model;

import androidx.annotation.Keep;

import java.io.Serializable;
import java.util.Objects;

@Keep
public class SignalState implements Serializable, Comparable<SignalState> {

    public SignalState() {

    }

    private int simIndex = 1;

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

    private int rssi;

    private int rsrq;

    private int csi_rsrp;

    private int csi_rsrq;

    private int ss_rsrp;

    private int ss_rsrq;


    private int rssnr;

    private String mobileCountryCode;

    private String mobileNetworkCode;

    public int getSimIndex() {
        return simIndex;
    }

    public void setSimIndex(int simIndex) {
        this.simIndex = simIndex;
    }

    public int getRssnr() {
        return rssnr;
    }

    public void setRssnr(int rssnr) {
        this.rssnr = rssnr;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public int getRsrq() {
        return rsrq;
    }

    public void setRsrq(int rsrq) {
        this.rsrq = rsrq;
    }

    public int getCsi_rsrp() {
        return csi_rsrp;
    }

    public void setCsi_rsrp(int csi_rsrp) {
        this.csi_rsrp = csi_rsrp;
    }

    public int getCsi_rsrq() {
        return csi_rsrq;
    }

    public void setCsi_rsrq(int csi_rsrq) {
        this.csi_rsrq = csi_rsrq;
    }

    public int getSs_rsrp() {
        return ss_rsrp;
    }

    public void setSs_rsrp(int ss_rsrp) {
        this.ss_rsrp = ss_rsrp;
    }

    public int getSs_rsrq() {
        return ss_rsrq;
    }

    public void setSs_rsrq(int ss_rsrq) {
        this.ss_rsrq = ss_rsrq;
    }

    public String getMobileCountryCode() {
        return mobileCountryCode;
    }

    public void setMobileCountryCode(String mobileCountryCode) {
        this.mobileCountryCode = mobileCountryCode;
    }

    public String getMobileNetworkCode() {
        return mobileNetworkCode;
    }

    public void setMobileNetworkCode(String mobileNetworkCode) {
        this.mobileNetworkCode = mobileNetworkCode;
    }

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
    public String toString() {
        return "SignalState{" +
                "simIndex=" + simIndex +
                ", operatorName='" + operatorName + '\'' +
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
                ", rssi=" + rssi +
                ", rsrq=" + rsrq +
                ", csi_rsrp=" + csi_rsrp +
                ", csi_rsrq=" + csi_rsrq +
                ", ss_rsrp=" + ss_rsrp +
                ", ss_rsrq=" + ss_rsrq +
                ", rssnr=" + rssnr +
                ", mobileCountryCode='" + mobileCountryCode + '\'' +
                ", mobileNetworkCode='" + mobileNetworkCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SignalState)) return false;
        SignalState that = (SignalState) o;
        return getSimIndex() == that.getSimIndex() && getSignalStrength() == that.getSignalStrength() && getChannelNo() == that.getChannelNo() && Objects.equals(getOperatorName(), that.getOperatorName()) && Objects.equals(getTechnology(), that.getTechnology());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOperatorName(), getSignalStrength(), getTechnology(), getChannelNo());
    }

    @Override
    public int compareTo(SignalState o) {
        return o.getSignalStrength() - this.getSignalStrength();
    }
}
