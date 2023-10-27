package com.oli.mywearremote.network

import android.content.Context
import com.oli.mywearremote.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.PUT
import java.io.InputStream
import java.security.KeyStore
import java.security.cert.Certificate
import java.security.cert.CertificateFactory
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

object RetrofitHelper {
    private const val baseUrl: String = "https://192.168.0.178:5000/"

    fun getRetrofitInstance(context: Context): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHTTPClient(context))
            .build()
    }

    private fun getHTTPClient(context: Context): OkHttpClient {
        val (sslContext, trustManagerFactory) = getSSLContext(context)
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .sslSocketFactory(
                sslContext.socketFactory,
                trustManagerFactory.trustManagers.first { it is X509TrustManager } as X509TrustManager)
            .hostnameVerifier(HostnameVerifier { _, _ -> true })
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private fun getSSLContext(context: Context): Pair<SSLContext, TrustManagerFactory> {
        // Load CAs
        val stream: InputStream = context.resources.openRawResource(R.raw.publiccert)
        val certificate: Certificate = stream.use { stream ->
            CertificateFactory.getInstance("X.509")
                .generateCertificate(stream)
        }

        // Keystore with trusted certificates
        val keystore: KeyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        keystore.load(null, null)
        keystore.setCertificateEntry("certificate", certificate)

        // TrustManager that trusts the certificates in our keystore
        val trustManagerFactory =
            TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(keystore)

        // SSLContext that uses our TrustManager
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustManagerFactory.trustManagers, null)
        return Pair(sslContext, trustManagerFactory)
    }

}

interface MediaControlsAPI {
    companion object {
        const val subPath: String = "media/"
    }

    @PUT("${subPath}volume-up")
    suspend fun volumeUp(): Response<VolumeResponse>

    @PUT("${subPath}volume-down")
    suspend fun volumeDown(): Response<VolumeResponse>

    @PUT("${subPath}play")
    suspend fun play(): Response<ControlResponse>

    @PUT("${subPath}pause")
    suspend fun pause(): Response<ControlResponse>

    @PUT("${subPath}next")
    suspend fun next(): Response<ControlResponse>

    @PUT("${subPath}previous")
    suspend fun previous(): Response<ControlResponse>
}

interface NavigationControlsAPI {
    companion object {
        const val subPath: String = "navigation/"
    }

    @PUT("${subPath}left")
    suspend fun left(): Response<ControlResponse>

    @PUT("${subPath}right")
    suspend fun right(): Response<ControlResponse>

    @PUT("${subPath}up")
    suspend fun up(): Response<ControlResponse>

    @PUT("${subPath}down")
    suspend fun down(): Response<ControlResponse>

    @PUT("${subPath}select")
    suspend fun select(): Response<ControlResponse>
}

public open class ControlResponse(
    val executedCommand: String
)

public class VolumeResponse(
    executedCommand: String,
    val volume: Float?
) : ControlResponse(executedCommand)


