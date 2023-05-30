package com.dot.nbm.model;

public class NetworkState {

    private int downstreamBandwidthKbps;

    private int upstreamBandwidthKbps;

    private String interfaceName;

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
                '}';
    }
}
