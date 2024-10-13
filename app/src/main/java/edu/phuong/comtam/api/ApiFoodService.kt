package edu.phuong.comtam.api

import edu.phuong.comtam.Model.Food
import retrofit2.Call
import retrofit2.http.GET

interface ApiFoodService {
    @GET("/foods")
    fun getFoods(): Call<List<Food>>

}