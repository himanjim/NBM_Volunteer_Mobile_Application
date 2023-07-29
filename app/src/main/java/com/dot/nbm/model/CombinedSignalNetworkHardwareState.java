package com.dot.nbm.model;

import androidx.annotation.Keep;

import java.util.List;
@Keep
public class CombinedSignalNetworkHardwareState {

    private double latitude;

    private double longitude;

    private long timeStamp;

    private String manufacturer;

    private String product;

    private String brand;

    private String model;

    private String host;

    private String hardware;

    private String authCode;

    public List<SignalState> getSignalStates() {
        return signalStates;
    }

    public List<NetworkState> getNetworkStates() {
        return networkStates;
    }

    public void setSignalStates(List<SignalState> signalStates) {
        this.signalStates = signalStates;
    }

    public void setNetworkStates(List<NetworkState> networkStates) {
        this.networkStates = networkStates;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
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


    @Override
    public String toString() {
        return "CombinedSignalNetworkHardwareState{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", timeStamp=" + timeStamp +
                ", manufacturer='" + manufacturer + '\'' +
                ", product='" + product + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", host='" + host + '\'' +
                ", hardware='" + hardware + '\'' +
                ", authCode='" + authCode + '\'' +
                ", signalStates=" + signalStates +
                ", networkStates=" + networkStates +
                '}';
    }
}
