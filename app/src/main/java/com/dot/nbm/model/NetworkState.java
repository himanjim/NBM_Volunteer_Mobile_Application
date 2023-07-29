package com.dot.nbm.model;

import androidx.annotation.Keep;

@Keep
public class NetworkState {

    private int downstreamBandwidthKbps;

    private int upstreamBandwidthKbps;

    private String interfaceName;

    private String transportType;

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public int getDownstreamBandwidthKbps() {
        return downstreamBandwidthKbps;
    }

    public void setDownstreamBandwidthKbps(int downstreamBandwidthKbps) {
        this.downstreamBandwidthKbps = downstreamBandwidthKbps;
    }

    public int getUpstreamBandwidthKbps() {
        return upstreamBandwidthKbps;
    }

    public void setUpstreamBandwidthKbps(int upstreamBandwidthKbps) {
        this.upstreamBandwidthKbps = upstreamBandwidthKbps;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    @Override
    public String toString() {
        return "NetworkState{" +
                "downstreamBandwidthKbps=" + downstreamBandwidthKbps +
                ", upstreamBandwidthKbps=" + upstreamBandwidthKbps +
                ", interfaceName='" + interfaceName + '\'' +
                ", transportType='" + transportType + '\'' +
                '}';
    }
}
