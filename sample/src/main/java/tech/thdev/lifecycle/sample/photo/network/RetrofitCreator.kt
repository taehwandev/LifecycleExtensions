package tech.thdev.lifecycle.sample.photo.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tech.thdev.lifecycle.sample.photo.BuildConfig
import java.util.concurrent.TimeUnit

/**
 * Created by tae-hwan on 7/27/17.
 */
fun <T> createRetrofit(clazz: Class<T>, baseUrl: String) =
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build().create(clazz)!!

private fun createOkHttpClient() =
        OkHttpClient.Builder().apply {
            readTimeout(5000, TimeUnit.MILLISECONDS)
            writeTimeout(5000, TimeUnit.MILLISECONDS)
            connectTimeout(5000, TimeUnit.MILLISECONDS)
            addInterceptor(initHttpLoggingInterceptor())
        }.build()

/**
 * Init logging level
 */
private fun initHttpLoggingInterceptor() =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.v("OkHttp", "message : $it")
        }).apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }