package edu.phuong.comtam.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import edu.phuong.comtam.Login
import edu.phuong.comtam.R

class Profile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_fragment, container, false)

        // Tìm nút Đăng xuất
        val logoutButton: Button = view.findViewById(R.id.btn_logout)

        // Xử lý sự kiện khi nhấn vào nút Đăng xuất
        logoutButton.setOnClickListener {
            // Ví dụ: Chuyển về màn hình đăng nhập (LoginActivity)
            val intent = Intent(activity, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            // Hoặc nếu bạn muốn đóng ứng dụng thì có thể dùng dòng sau:
            // activity?.finish()
        }

        return view
    }
}