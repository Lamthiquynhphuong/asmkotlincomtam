package edu.phuong.comtam.RetrofitAll

import edu.phuong.comtam.api.ApiServer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://666035f55425580055b2cd5e.mockapi.io/" // Thay đổi URL nếu cần

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    // Sử dụng lazy để tạo instance của ApiServer
    val instance: ApiServer by lazy {
        Retrofit.Builder() // Sửa thành Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServer::class.java)
    }
}
