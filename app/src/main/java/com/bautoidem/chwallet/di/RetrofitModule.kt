package com.bautoidem.chwallet.di

import com.bautoidem.chwallet.BuildConfig
import com.bautoidem.chwallet.utils.AppConstant
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    single { gsonBuilder() }
    single { httpClient() }
    single { retrofitRetry() }
}

private fun Scope.retrofitRetry(): Retrofit{
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(get())
        .build()
}

private fun gsonBuilder(): Gson {
    return GsonBuilder().create()
}

private fun httpClient(): OkHttpClient{
    var interceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        interceptor.level = HttpLoggingInterceptor.Level.NONE
    }

    val httpClient= OkHttpClient.Builder().apply {
        connectTimeout(AppConstant.RETROFIT_CONNECT_TIMEOUT,TimeUnit.MILLISECONDS)
        writeTimeout(AppConstant.RETROFIT_CONNECT_TIMEOUT,TimeUnit.MILLISECONDS)
        readTimeout(AppConstant.RETROFIT_CONNECT_TIMEOUT,TimeUnit.MILLISECONDS)
        retryOnConnectionFailure(true)
        addInterceptor(interceptor)
    }
    return httpClient.build()
}