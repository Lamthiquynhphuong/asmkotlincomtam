package edu.phuong.comtam.ui

import HomeFragment
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.phuong.comtam.Fragment.Hotro
import edu.phuong.comtam.Fragment.Quanly
import edu.phuong.comtam.Fragment.ThongkeFragment
import edu.phuong.comtam.Fragment.Trangchu
import edu.phuong.comtam.R

class NavigationAdmin : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottomadmin)

        // Load the default fragment
        loadFragment(Trangchu())

        // Find BottomNavigationView
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    loadFragment (Trangchu())
                    true
                }
                R.id.management -> {
                    loadFragment(Quanly())
                    true
                }
                R.id.statistics -> {
                    loadFragment(ThongkeFragment())
                    true
                }
                R.id.support -> {
                    loadFragment(Hotro())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction =supportFragmentManager .beginTransaction()
        transaction.replace(R.id.conavi, fragment)
        transaction.commit()
        Log.d("NavigationAdmin", "Fragment loaded: ${fragment.javaClass.simpleName}")
    }
}