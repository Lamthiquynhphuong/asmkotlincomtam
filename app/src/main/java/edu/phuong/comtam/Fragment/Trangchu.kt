// Trangchu.kt
package edu.phuong.comtam.Fragment

import FoodAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.phuong.comtam.Adapter.ReviewFood
import edu.phuong.comtam.Model.FoodAdmin
import edu.phuong.comtam.R
import edu.phuong.comtam.RetrofitAll.MonanRetro
import edu.phuong.comtam.ui.FoodViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Trangchu : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodAdapter: ReviewFood
    private lateinit var foodNameEditText: EditText
    private lateinit var foodPriceEditText: EditText
    private lateinit var foodDescriptionEditText: EditText
    private lateinit var addButton: Button
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button

    private val foodViewModel: FoodViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.trangchuaa, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        foodNameEditText = view.findViewById(R.id.food_name_edit_text)
        foodPriceEditText = view.findViewById(R.id.food_price_edit_text)
        foodDescriptionEditText = view.findViewById(R.id.food_description_edit_text)
        addButton = view.findViewById(R.id.add_button)
        updateButton = view.findViewById(R.id.update_button)
        deleteButton = view.findViewById(R.id.delete_button)

        foodAdapter = ReviewFood(mutableListOf()) { food -> onFoodClicked(food) }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = foodAdapter

        foodViewModel.foods.observe(viewLifecycleOwner, Observer { foods ->
            foodAdapter.setFoods(foods)
        })

        addButton.setOnClickListener { addFood() }
        updateButton.setOnClickListener { updateFood() }
        deleteButton.setOnClickListener { deleteFood() }

        return view
    }

    private fun onFoodClicked(food: FoodAdmin) {
        foodNameEditText.setText(food.name)
        foodPriceEditText.setText(food.price.toString())
        foodDescriptionEditText.setText(food.description)
    }

    private fun addFood() {
        val foodName = foodNameEditText.text.toString()
        val foodPrice = foodPriceEditText.text.toString().toIntOrNull()
        val foodDescription = foodDescriptionEditText.text.toString()

        if (foodName.isNotEmpty() && foodPrice != null && foodDescription.isNotEmpty()) {
            val newFood = FoodAdmin(
                id = 0, // ID sẽ được cấp tự động từ server
                name = foodName,
                price = foodPrice,
                thumbnail = "", // Cập nhật với URL của hình ảnh nếu có
                description = foodDescription
            )

            MonanRetro.api.addFood(newFood).enqueue(object : Callback<FoodAdmin> {
                override fun onResponse(call: Call<FoodAdmin>, response: Response<FoodAdmin>) {
                    if (response.isSuccessful) {
                        foodViewModel.addFood(response.body()!!)
                        Toast.makeText(requireContext(), "Đã thêm món ăn", Toast.LENGTH_SHORT).show()
                        clearInputs()
                    } else {
                        Toast.makeText(requireContext(), "Lỗi thêm món ăn: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<FoodAdmin>, t: Throwable) {
                    Toast.makeText(requireContext(), "Lỗi: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(requireContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateFood() {
        val foodName = foodNameEditText.text.toString()
        val foodPrice = foodPriceEditText.text.toString().toIntOrNull()
        val foodDescription = foodDescriptionEditText.text.toString()

        if (foodName.isNotEmpty() && foodPrice != null && foodDescription.isNotEmpty()) {
            val updatedFood = FoodAdmin(
                id =57 /* ID của món ăn cần cập nhật, bạn cần lấy ID từ món ăn đã chọn */,
                name = foodName,
                price = foodPrice,
                thumbnail = "", // Cập nhật với URL của hình ảnh nếu có
                description = foodDescription
            )

            MonanRetro.api.updateFood(updatedFood.id, updatedFood).enqueue(object : Callback<FoodAdmin> {
                override fun onResponse(call: Call<FoodAdmin>, response: Response<FoodAdmin>) {
                    if (response.isSuccessful) {
                        foodViewModel.updateFood(response.body()!!)
                        Toast.makeText(requireContext(), "Đã cập nhật món ăn", Toast.LENGTH_SHORT).show()
                        clearInputs()
                    } else {
                        Toast.makeText(requireContext(), "Lỗi cập nhật món ăn: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<FoodAdmin>, t: Throwable) {
                    Toast.makeText(requireContext(), "Lỗi: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(requireContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteFood() {
        val foodName = foodNameEditText.text.toString()
        val foodToDelete = foodViewModel.foods.value?.firstOrNull { it.name == foodName }
        if (foodToDelete != null) {
            MonanRetro.api.deleteFood(foodToDelete.id).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        foodViewModel.deleteFood(foodToDelete)
                        Toast.makeText(requireContext(), "Đã xóa món ăn", Toast.LENGTH_SHORT).show()
                        clearInputs()
                    } else {
                        Toast.makeText(requireContext(), "Lỗi xóa món ăn: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(requireContext(), "Lỗi: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(requireContext(), "Món ăn không tồn tại", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearInputs() {
        foodNameEditText.text.clear()
        foodPriceEditText.text.clear()
        foodDescriptionEditText.text.clear()
    }
}
