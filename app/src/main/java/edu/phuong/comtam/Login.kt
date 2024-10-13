package edu.phuong.comtam

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.core.view.setPadding
import edu.phuong.comtam.Model.LoginResponse
import edu.phuong.comtam.RetrofitAll.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : ComponentActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Tạo LinearLayout chính
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER // Căn giữa tất cả các thành phần theo chiều dọc và ngang
            setPadding(32) // Padding tổng thể
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(Color.WHITE) // Màu nền trắng
        }

        // Tạo ImageView cho ảnh ở trên cùng
        val imageView = ImageView(this).apply {
            setImageResource(R.drawable.comtam) // Thay đổi tên ảnh ở đây
            layoutParams = LinearLayout.LayoutParams(
                400, // Chiều rộng là 400dp
                400 // Chiều cao của ảnh là 400dp
            )
        }

        // Giảm khoảng cách trên và dưới của ảnh
        val paramsImageView = imageView.layoutParams as LinearLayout.LayoutParams
        paramsImageView.setMargins(0, 10, 0, 50) // Cách trên là 10dp và cách dưới 50dp
        imageView.layoutParams = paramsImageView

        // Tạo EditText cho username
        etUsername = EditText(this).apply {
            hint = "Username"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                130 // Chiều cao là 130dp
            ).apply {
                setMargins(0, 0, 0, 20) // Khoảng cách giữa các EditText
            }
            setPadding(32)
            background = createEditTextDrawable(Color.parseColor("#3F51B5")) // Viền màu xanh tím với bo góc
            setTextColor(Color.parseColor("#212121")) // Màu chữ đen
            setHintTextColor(Color.parseColor("#757575")) // Màu gợi ý
            textSize = 16f
        }

        // Tạo EditText cho password
        etPassword = EditText(this).apply {
            hint = "Password"
            inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                130 // Chiều cao là 130dp
            )
            setPadding(32)
            background = createEditTextDrawable(Color.parseColor("#3F51B5")) // Viền màu xanh tím với bo góc
            setTextColor(Color.parseColor("#212121"))
            setHintTextColor(Color.parseColor("#757575")) // Màu gợi ý
            textSize = 16f
        }

        // Tạo Button cho login
        btnLogin = Button(this).apply {
            text = "Login"
            setTextColor(Color.WHITE)
            textSize = 18f
            layoutParams = LinearLayout.LayoutParams(
                500, // Giới hạn chiều rộng của nút Login là 500dp
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER // Căn giữa nút Login theo chiều ngang
                setMargins(0, 50, 0, 0) // Cách nút Login với các thành phần phía trên
            }
            background = createButtonDrawable() // Bo góc và màu gradient cho nút
            setPadding(0, 40, 0, 40) // Padding cho nút
            setOnClickListener {
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()

                if (username.isNotEmpty() && password.isNotEmpty()) {
                    login(username, password)
                } else {
                    Toast.makeText(this@Login, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Thêm các View vào Layout
        layout.addView(imageView)
        layout.addView(etUsername)
        layout.addView(etPassword)
        layout.addView(btnLogin)

        // Đặt Layout làm nội dung của Activity
        setContentView(layout)
    }

    // Tạo Drawable cho EditText với viền và bo góc
    private fun createEditTextDrawable(borderColor: Int): GradientDrawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 20f // Bo góc 20dp
            setColor(Color.parseColor("#FFFFFF")) // Nền màu trắng
            setStroke(3, borderColor) // Viền màu truyền vào
        }
    }

    // Tạo Drawable cho nút đăng nhập với bo góc và màu gradient
    private fun createButtonDrawable(): GradientDrawable {
        return GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, intArrayOf(
            Color.parseColor("#3F51B5"), // Gradient bắt đầu từ màu xanh tím
            Color.parseColor("#5C6BC0")  // Kết thúc bằng màu xanh nhạt hơn
        )).apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 40f // Bo góc cho nút
        }
    }

    private fun login(username: String, password: String) {
        val call = RetrofitClient.instance.login(username, password)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val token = response.body()?.id // Hoặc trường nào bạn cần từ LoginResponse
                    Toast.makeText(this@Login, "Login Successful! Token: $token", Toast.LENGTH_SHORT).show()

                    // Khởi động Navigation Activity
                    val intent = Intent(this@Login, Navigation::class.java)
                    startActivity(intent)
                    finish() // Kết thúc Activity Login để không quay lại
                    // Lưu token và chuyển sang màn hình khác nếu cần
                } else {
                    Toast.makeText(this@Login, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@Login, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
