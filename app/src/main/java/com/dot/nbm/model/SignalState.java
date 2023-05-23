package com.dot.nbm.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "signal_state")
public class SignalState {

    public SignalState(long id, String operaterName, int signalStrength, String technology, double latitude, double longitude, long timeStamp, boolean synced) {
        this.id = id;
        this.operaterName = operaterName;
        this.signalStrength = signalStrength;
        this.technology = technology;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeStamp = timeStamp;
        this.synced = synced;
    }

    @Ignore
    public SignalState(){

    }

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "operator_name")
    private String operaterName;

    @ColumnInfo(name = "signal_strength")
    private int signalStrength;

    @ColumnInfo(name = "technology")
    private String technology;

    @ColumnInfo(name = "latitude")
    private double latitude;

    @ColumnInfo(name = "longitude")
    private double longitude;

    @ColumnInfo(name = "timestamp")
    private long timeStamp;

    @ColumnInfo(name = "synced")
    private boolean synced;


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
}
