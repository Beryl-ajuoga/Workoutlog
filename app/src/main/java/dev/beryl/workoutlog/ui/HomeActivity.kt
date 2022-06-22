package dev.beryl.workoutlog.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.beryl.workoutlog.R

class HomeActivity : AppCompatActivity() {
    lateinit var bnvHome:BottomNavigationView
    lateinit var fcvhome:FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Castviews()
        setupBottomNav()
    }
    fun Castviews(){
        bnvHome=findViewById(R.id.bottomnavigation)
        fcvhome =findViewById(R.id.fcvhome)
    }
    fun setupBottomNav(){
        bnvHome.setOnItemSelectedListener{ item->
            when(item.itemId) {
                R.id.plan -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvhome, PlanFragment()).commit()
                    true
                }
                R.id.track -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvhome, TrackFragment2()).commit()
                    true
                }

                R.id.track -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvhome, ProfileFragment2()).commit()
                    true
                }
                else-> false
            }
        }

    }
}