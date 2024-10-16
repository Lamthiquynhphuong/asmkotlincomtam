package edu.phuong.comtam.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import edu.phuong.comtam.R

class Hotro : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hotro, container, false)

        // Lấy tham chiếu đến nút đăng xuất
        val logoutButton: Button = view.findViewById(R.id.logout_button)

        // Thiết lập sự kiện click cho nút đăng xuất
        logoutButton.setOnClickListener {
            // Xử lý logic đăng xuất ở đây
            logoutUser()
        }

        return view
    }

    private fun logoutUser() {
        // Logic để đăng xuất người dùng
        // Ví dụ: Xóa thông tin đăng nhập, chuyển hướng về màn hình đăng nhập, v.v.

        Toast.makeText(requireContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show()

        // Nếu bạn muốn trở về màn hình đăng nhập, bạn có thể thực hiện điều này:
        // (Giả sử MainActivity là activity chính)
        requireActivity().finish() // Hoặc khởi động lại Activity đăng nhập
    }
}
