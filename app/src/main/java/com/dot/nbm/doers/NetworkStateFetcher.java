package com.dot.nbm.doers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Log;

import com.dot.nbm.model.NetworkState;

import java.util.ArrayList;
import java.util.List;

public class NetworkStateFetcher {

    List<NetworkState> getNetworkState(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        List<NetworkState> networkStates = new ArrayList<>();

        Network[] networks = connectivityManager.getAllNetworks();
        for (Network network: networks) {
            NetworkCapabilities nc = connectivityManager.getNetworkCapabilities(network);
            LinkProperties linkProperties = connectivityManager.getLinkProperties(network);

            NetworkState networkState = new NetworkState();
            networkState.setInterfaceName(linkProperties.getInterfaceName());
            networkState.setDownstreamBandwidthKbps(nc.getLinkDownstreamBandwidthKbps());
            networkState.setUpstreamBandwidthKbps(nc.getLinkUpstreamBandwidthKbps());
            networkStates.add(networkState);
        }

        return networkStates;
    }
}
