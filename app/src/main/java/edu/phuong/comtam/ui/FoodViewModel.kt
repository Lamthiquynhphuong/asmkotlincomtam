package edu.phuong.comtam.ui

import FoodAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.phuong.comtam.Model.FoodAdmin

class FoodViewModel : ViewModel() {
    private val _foods = MutableLiveData<MutableList<FoodAdmin>>()
    val foods: LiveData<MutableList<FoodAdmin>> get() = _foods

    init {
        // Khởi tạo danh sách món ăn từ dữ liệu đã cho
        _foods.value = mutableListOf(
            FoodAdmin(57, "Com ga", 600000, "content://com.android.providers.downloads.documents/document/26", "very good"),
            FoodAdmin(58, "Com bo kho", 550000, "content://com.android.providers.downloads.documents/document/21", "Bo my"),
            FoodAdmin(59, "Com bi cha", 450000, "content://com.android.providers.downloads.documents/document/22", "okokokoko"),
            FoodAdmin(60, "Thap cam", 900000, "content://com.android.providers.downloads.documents/document/21", "da dang"),
            FoodAdmin(61, "Com vit", 650000, "content://com.android.providers.downloads.documents/document/26", "ngonnn"),
            FoodAdmin(62, "Com dac biet", 77770, "content://com.android.providers.downloads.documents/document/23", "okkkkk"),
            FoodAdmin(63, "com tam", 99990, "content://com.android.providers.downloads.documents/document/26", "aaaaaa")
        )
    }

    // Thêm món ăn
    fun addFood(food: FoodAdmin) {
        _foods.value?.add(food)
        _foods.value = _foods.value // Cập nhật LiveData
    }

    // Sửa món ăn
    fun updateFood(food: FoodAdmin) {
        _foods.value?.let {
            val index = it.indexOfFirst { f -> f.id == food.id }
            if (index != -1) {
                it[index] = food
                _foods.value = it // Cập nhật LiveData
            }
        }
    }

    // Xóa món ăn
    fun deleteFood(food: FoodAdmin) {
        _foods.value?.remove(food)
        _foods.value = _foods.value // Cập nhật LiveData
    }
}