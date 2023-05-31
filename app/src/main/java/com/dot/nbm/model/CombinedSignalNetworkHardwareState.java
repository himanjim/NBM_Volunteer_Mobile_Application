package com.dot.nbm.model;

import java.util.List;

public class CombinedSignalNetworkHardwareState {

    private double latitude;

    private double longitude;

    private long timeStamp;

    private boolean synced;

    private String manufacturer;

    private String product;

    private String brand;

    private String model;

    private String host;

    private String hardware;

    public List<SignalState> getSignalStates() {
        return signalStates;
    }

    @Override
    public String toString() {
        return "CombinedSignalNetworkHardwareState{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", timeStamp=" + timeStamp +
                ", synced=" + synced +
                ", manufacturer='" + manufacturer + '\'' +
                ", product='" + product + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", host='" + host + '\'' +
                ", hardware='" + hardware + '\'' +
                ", signalStates=" + signalStates +
                ", networkStates=" + networkStates +
                '}';
    }

    public void setSignalStates(List<SignalState> signalStates) {
        this.signalStates = signalStates;
    }

    public List<NetworkState> getNetworkStates() {
        return networkStates;
    }

    public void setNetworkStates(List<NetworkState> networkStates) {
        this.networkStates = networkStates;
    }

    private List<SignalState> signalStates;

    private List<NetworkState> networkStates;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isSynced() {
        return synced;
    }

    public void setSynced(boolean synced) {
        this.synced = synced;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

}
