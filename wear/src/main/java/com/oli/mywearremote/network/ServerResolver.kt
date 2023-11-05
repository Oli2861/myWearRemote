package com.oli.mywearremote.network

import android.util.Log
import com.oli.mywearremote.network.ServerConstants.SERVICE_NAME
import com.oli.mywearremote.network.ServerConstants.SERVICE_TYPE
import com.oli.mywearremote.network.ServerConstants.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.InetAddress
import javax.jmdns.JmDNS


object ServerConstants {
    const val SERVICE_TYPE = "_http._tcp.local."
    const val SERVICE_NAME = "MyWearRemote"
    const val TAG = "ServerResolver"
}

class ServerResolver {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val availableServerAddresses: MutableSet<String> = mutableSetOf()


    init {
        searchServer()
    }

    fun searchServer() {
        coroutineScope.launch {
            val jmDNS = JmDNS.create()
            jmDNS.addServiceListener(SERVICE_TYPE, ServiceListenerImpl(jmDNS))
        }
    }

    inner class ServiceListenerImpl(
        private val jmDNS: JmDNS
    ) : javax.jmdns.ServiceListener {

        override fun serviceAdded(event: javax.jmdns.ServiceEvent?) {
            Log.d(TAG, "Service added: ${event?.info}")
            jmDNS.requestServiceInfo(event?.type, event?.name)
        }

        override fun serviceRemoved(event: javax.jmdns.ServiceEvent?) {
            Log.d(TAG, "Service removed: ${event?.info}")
        }

        override fun serviceResolved(event: javax.jmdns.ServiceEvent?) {
            Log.d(TAG, "Service resolved: ${event?.info}")

            if (event?.info?.name?.contains(SERVICE_NAME) == true) {
                val port: Int = event?.info?.port ?: return
                val host: InetAddress = event?.info?.inetAddresses?.first() ?: return
                val address = "https://${host}:${port}/"
                availableServerAddresses.add(address)
                Log.d(TAG, "Resolved address: $address. Available addresses: $availableServerAddresses")
            }
        }

    }


    /*

    https://stackoverflow.com/questions/43820672/bonjour-dnsserviceregister-on-android --> Permissions: ACCESS_NETWORK_STATE, CHANGE_WIFI_MULTICAST_STATE, ACCESS_WIFI_STATE
    https://stackoverflow.com/questions/52324738/android-mdnsresponder-using-native-api-returns-kdnsserviceerr-servicenotrunning --> Multicast lock

    https://issuetracker.google.com/issues/37018409
    https://developer.apple.com/documentation/dnssd/1823426-anonymous/kdnsserviceerr_badparam/ (65540 -> kDNSServiceErr_BadParam) also: https://cs.android.com/android/platform/superproject/+/master:external/mdnsresponder/mDNSCore/mDNSEmbeddedAPI.h;l=317;drc=master;bpv=0;bpt=0
    https://android.googlesource.com/platform/external/mdnsresponder/+/refs/heads/master/mDNSCore/mDNS.c (sources)
    https://android.googlesource.com/platform/system/netd/+/3d4c7585e35a93d9608fce8cc056b7eee9123a53/MDnsSdListener.cpp (sources)

    private val mNsdManager: NsdManager = context.getSystemService(NsdManager::class.java)
    private val wifi = context.getSystemService(Context.WIFI_SERVICE) as WifiManager?
    private val lock = wifi!!.createMulticastLock("mylock")

    private val resolveListener: NsdManager.ResolveListener = object : NsdManager.ResolveListener {

        override fun onResolveFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
            // Called when the resolve fails. Use the error code to debug.
            Log.e(TAG, "Resolve failed: $errorCode")
            cleanUp()
        }

        override fun onServiceResolved(serviceInfo: NsdServiceInfo) {
            Log.e(TAG, "Resolve Succeeded. $serviceInfo")

            if (serviceInfo.serviceName == ServerConstants.SERVICE_NAME) {
                Log.d(TAG, "Same name.")
                return
            }

            val port: Int = serviceInfo.port
            val host: InetAddress = serviceInfo.host
            urlInterceptor.url = "https://${host}:${port}/"
            Log.d(TAG, "Resolved address: ${urlInterceptor.url}")
            cleanUp()
        }
    }

    private val discoveryListener: NsdManager.DiscoveryListener = object : NsdManager.DiscoveryListener {

            override fun onDiscoveryStarted(regType: String) {
                Log.d(TAG, "Service discovery started")
            }

            override fun onServiceFound(service: NsdServiceInfo) {
                Log.d(TAG, "Service discovery success $service")
                when {
                    service.serviceName.contains(SERVICE_NAME) -> mNsdManager.resolveService(service, resolveListener)
                    else -> Log.d(TAG, "Unknown Service Type: " + service.serviceType)
                }
            }

            override fun onServiceLost(service: NsdServiceInfo) {
                Log.e(TAG, "service lost: $service")
                cleanUp()
            }

            override fun onDiscoveryStopped(serviceType: String) {
                Log.i(TAG, "Discovery stopped: $serviceType")
                cleanUp()
            }

            override fun onStartDiscoveryFailed(serviceType: String, errorCode: Int) {
                Log.e(TAG, "Discovery failed: Error code:$errorCode")
                cleanUp()
            }

            override fun onStopDiscoveryFailed(serviceType: String, errorCode: Int) {
                Log.e(TAG, "Discovery failed: Error code:$errorCode")
                cleanUp()
            }
        }

    init {
        lock.acquire()
        mNsdManager.discoverServices(SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, discoveryListener)
    }

    fun cleanUp() {
        lock.release()
    }
*/
}