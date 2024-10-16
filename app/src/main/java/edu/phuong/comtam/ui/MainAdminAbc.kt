package edu.phuong.comtam.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.phuong.comtam.R
import edu.phuong.comtam.Fragment.Trangchu // Thay đổi theo package thực tế của bạn

class MainAdminAbc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Layout cho Activity

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction() // Bắt đầu giao dịch với fragment
                .replace(R.id.fragment_container, Trangchu()) // Thay thế fragment
                .commit() // Thực hiện giao dịch
        }
    }
}
