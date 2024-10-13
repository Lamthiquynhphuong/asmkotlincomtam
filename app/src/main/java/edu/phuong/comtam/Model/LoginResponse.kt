package edu.phuong.comtam.Model

data class LoginResponse(
//    val id: String,
//    val username: String,
//    val password: String
    val id: String,
    val username: String,
    val token: String,   // Token nhận được khi đăng nhập thành công
    val status: String,  // "success" hoặc "failed"
    val message: String? // Thông báo lỗi nếu có
)
