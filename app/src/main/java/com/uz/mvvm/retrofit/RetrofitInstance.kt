package com.uz.mvvm.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Eldor Turgunov on 24.08.2022.
 * Mvvm
 * eldorturgunov777@gmail.com
 */
object RetrofitInstance {
    private var BASE_URL = "https://jsonplaceholder.typicode.com/"
    private var retrofit: Retrofit? = null

    fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}