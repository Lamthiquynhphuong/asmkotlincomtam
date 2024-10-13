package edu.phuong.comtam

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen { navigateToLoginScreen() }
        }
    }
    private fun navigateToLoginScreen() {
        startActivity(Intent(this, Login::class.java))
        finish() // Đóng màn hình chào
    }
}
@Composable
fun SplashScreen(onLoginClick: () -> Unit) {
    // Sử dụng Column để căn giữa ảnh và nút
    Column(
        modifier = Modifier.fillMaxSize(), // Chiếm toàn bộ màn hình
        horizontalAlignment = Alignment.CenterHorizontally, // Căn giữa theo chiều ngang
        verticalArrangement = Arrangement.Center // Căn giữa theo chiều dọc
    ) {
        Image(
            painter = painterResource(id = R.drawable.comtam), // Thay đổi bằng tên ảnh của bạn
            contentDescription = null,
            modifier = Modifier.size(300.dp), // Điều chỉnh kích thước ảnh
        )

        // Nút đăng nhập
        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .padding(top = 16.dp) // Khoảng cách phía trên nút
                .size(200.dp, 50.dp) // Kích thước nút
        ) {
            Text(text = "Đăng Nhập", color = Color.White) // Văn bản trên nút
        }
    }
}



