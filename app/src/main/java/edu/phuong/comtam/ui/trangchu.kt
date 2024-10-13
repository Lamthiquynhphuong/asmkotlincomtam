package edu.phuong.comtam.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import edu.phuong.comtam.Model.Food
import edu.phuong.comtam.R
import edu.phuong.comtam.RetrofitAll.FoodRetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class trangchu : ComponentActivity() {

    private lateinit var foodContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        foodContainer = findViewById(R.id.food_container)

        fetchFoods()
    }

    private fun fetchFoods() {
        FoodRetrofitClient.instance.getFoods().enqueue(object : Callback<List<Food>> {
            override fun onResponse(call: Call<List<Food>>, response: Response<List<Food>>) {
                if (response.isSuccessful) {
                    response.body()?.let { foods ->
                        for (food in foods) {
                            addFoodView(food)
                        }
                    }
                } else {
                    // Xử lý lỗi phản hồi không thành công
                }
            }

            override fun onFailure(call: Call<List<Food>>, t: Throwable) {
                // Xử lý lỗi mạng
            }
        })
    }

    private fun addFoodView(food: Food) {
        // Inflate layout cho mỗi món ăn
        val foodView = LayoutInflater.from(this).inflate(R.layout.item_food, foodContainer, false)

        val foodName: TextView = foodView.findViewById(R.id.food_name)
        val foodDescription: TextView = foodView.findViewById(R.id.food_description)

        // Thiết lập tên và mô tả cho món ăn
        foodName.text = food.name
        foodDescription.text = food.description ?: "Không có mô tả"

        // Thêm view vào LinearLayout
        foodContainer.addView(foodView)
    }
}