package edu.phuong.comtam.api
import edu.phuong.comtam.Model.FoodAdmin
import retrofit2.Call
import retrofit2.http.*
interface Monan {
    @GET("foods")
    fun getFoods(): Call<List<FoodAdmin>>

    @POST("foods")
    fun addFood(@Body food: FoodAdmin): Call<FoodAdmin>

    @PUT("foods/{id}")
    fun updateFood(@Path("id") id: Int, @Body food: FoodAdmin): Call<FoodAdmin>

    @DELETE("foods/{id}")
    fun deleteFood(@Path("id") id: Int): Call<Void>


}