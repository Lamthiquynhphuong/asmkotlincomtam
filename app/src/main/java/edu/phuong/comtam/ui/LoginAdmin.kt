package edu.phuong.comtam.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import edu.phuong.comtam.Navigation
import edu.phuong.comtam.NavigationActivity
import edu.phuong.comtam.R

class LoginAdmin : ComponentActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    // Cố định thông tin đăng nhập admin
    private val adminUsername = "admin"
    private val adminPassword = "admin123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_admin)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // Kiểm tra thông tin đăng nhập
            if (username == adminUsername && password == adminPassword) {
                Log.d("LoginAdmin", "Navigating to NavigationAdmin")
                // Nếu là admin, chuyển đến màn hình chính
                val intent = Intent(this, NavigationAdmin::class.java)
                startActivity(intent)
                finish() // Kết thúc Activity Login để không quay lại
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}