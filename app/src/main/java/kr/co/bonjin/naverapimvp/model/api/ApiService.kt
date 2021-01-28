package kr.co.bonjin.naverapimvp.model.api

import com.google.gson.GsonBuilder
import kr.co.bonjin.naverapimvp.common.Const.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {
    private var mRetrofit: Retrofit? = null
    private val gson = GsonBuilder().create()

    private val mHttpLoggingIntercepter = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    private val mOkHttpClientBuilder = OkHttpClient.Builder()
        .addInterceptor(mHttpLoggingIntercepter)

    private val mOkHttpClient = mOkHttpClientBuilder.build()

    fun <T> createService(serviceClass: Class<T>?): T {
        if (mRetrofit == null) {
            mRetrofit = Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return mRetrofit!!.create(serviceClass)
    }

}