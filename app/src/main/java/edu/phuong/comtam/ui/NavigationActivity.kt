package edu.phuong.comtam

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val logoutButton: Button = findViewById(R.id.btnLogout)
        logoutButton.setOnClickListener {
            finish() // Kết thúc Activity Navigation để quay lại màn hình đăng nhập
        }
    }
}
