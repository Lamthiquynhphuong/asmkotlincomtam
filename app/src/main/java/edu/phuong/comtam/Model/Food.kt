package edu.phuong.comtam.Model

data class Food(
    val id: String,
    val name: String,
   val price: Double,
    val thumbnail: String, // Đổi từ imageUrl thành thumbnail
    val description: String // Đảm bảo trường này tồn tại
)
