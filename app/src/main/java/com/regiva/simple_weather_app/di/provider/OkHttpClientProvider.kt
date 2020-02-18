package com.regiva.simple_weather_app.di.provider

import android.content.Context
import com.regiva.simple_weather_app.BuildConfig
import com.regiva.simple_weather_app.model.data.network.interceptor.OpenWeatherInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

class OkHttpClientProvider @Inject constructor(
    private val context: Context
) : Provider<OkHttpClient> {

    override fun get() = with(OkHttpClient.Builder()) {
        protocols(arrayListOf(Protocol.HTTP_2, Protocol.HTTP_1_1))
        cache(Cache(context.cacheDir, CACHE_SIZE_BYTES))
        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
        addInterceptor(OpenWeatherInterceptor())
        if (BuildConfig.DEBUG) {
            addNetworkInterceptor(
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            )
        }
        build()
    }

    companion object {
        private const val CACHE_SIZE_BYTES = 20 * 1024L
        private const val TIMEOUT = 100L
    }
}