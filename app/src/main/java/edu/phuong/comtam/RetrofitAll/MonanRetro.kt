package edu.phuong.comtam.RetrofitAll

import edu.phuong.comtam.api.Monan
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MonanRetro {
    private const val BASE_URL = "https://comtam.phuocsangbn.workers.dev/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: Monan by lazy {
        retrofit.create(Monan::class.java)
    }
}