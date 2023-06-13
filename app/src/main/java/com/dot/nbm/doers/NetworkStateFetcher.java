package com.dot.nbm.doers;

import static android.net.NetworkCapabilities.TRANSPORT_BLUETOOTH;
import static android.net.NetworkCapabilities.TRANSPORT_CELLULAR;
import static android.net.NetworkCapabilities.TRANSPORT_ETHERNET;
import static android.net.NetworkCapabilities.TRANSPORT_LOWPAN;
import static android.net.NetworkCapabilities.TRANSPORT_USB;
import static android.net.NetworkCapabilities.TRANSPORT_WIFI;
import static android.net.NetworkCapabilities.TRANSPORT_WIFI_AWARE;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;

import com.dot.nbm.R;
import com.dot.nbm.model.NetworkState;

import java.util.ArrayList;
import java.util.List;

public class NetworkStateFetcher {

    public static List<NetworkState> getNetworkState(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        connectivityManager.getN
        List<NetworkState> networkStates = new ArrayList<>();

        Network[] networks = connectivityManager.getAllNetworks();
        for (Network network: networks) {
            NetworkCapabilities nc = connectivityManager.getNetworkCapabilities(network);
            LinkProperties linkProperties = connectivityManager.getLinkProperties(network);

            NetworkState networkState = new NetworkState();
            networkState.setInterfaceName(linkProperties.getInterfaceName());
            networkState.setDownstreamBandwidthKbps(nc.getLinkDownstreamBandwidthKbps());
            networkState.setUpstreamBandwidthKbps(nc.getLinkUpstreamBandwidthKbps());
            networkState.setTransportType(getTransportType(nc, context));

            networkStates.add(networkState);
        }

        return networkStates;
    }

    private static String getTransportType(NetworkCapabilities nc, Context context){
        if (nc.hasTransport(TRANSPORT_CELLULAR))
            return context.getString(R.string.TRANSPORT_CELLULAR);

        else if(nc.hasTransport(TRANSPORT_WIFI))
            return context.getString(R.string.TRANSPORT_WIFI);

        else if(nc.hasTransport(TRANSPORT_BLUETOOTH))
            return context.getString(R.string.TRANSPORT_BLUETOOTH);

        else if(nc.hasTransport(TRANSPORT_ETHERNET))
            return context.getString(R.string.TRANSPORT_ETHERNET);

        else if(nc.hasTransport(TRANSPORT_WIFI_AWARE))
            return context.getString(R.string.TRANSPORT_WIFI_AWARE);

        else if(nc.hasTransport(TRANSPORT_LOWPAN))
            return context.getString(R.string.TRANSPORT_LOWPAN);

        else if(nc.hasTransport(TRANSPORT_USB))
            return context.getString(R.string.TRANSPORT_USB);

        return null;
    }

}
