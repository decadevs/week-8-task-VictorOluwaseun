package com.decagon.pokemonapp.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkStatusChecker(private  val connectivityManager: ConnectivityManager?) {
    //    You need the connectivity manager to check the network connection and its capabilities
    // This will check if there is good connection, and if there is, it will execute a
    // certain action
    inline fun performIfConnectedToInternet(action: ()->Unit){
        if(hasInternetConnection()){
            action()
        }
    }

    fun hasInternetConnection(): Boolean {
        val network = connectivityManager?.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?:return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                ||capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                ||capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }

    /**
     * To start off with creating an API call, you need to use a network checker to launch API calls
     * only if there is an internet connection. Also to enter the app, you first have to register  a user.
     * */

}