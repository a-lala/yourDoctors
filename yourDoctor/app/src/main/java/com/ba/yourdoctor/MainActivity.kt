package com.ba.yourdoctor

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener loadfragment(ContentFragment())
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener loadfragment(ContentFragment())
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener loadfragment(ContentFragment())
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val f:FrameLayout = findViewById(R.id.fragment_container);
        loadfragment(ContentFragment())
        //textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }


    fun  loadfragment(fragment: Fragment):Boolean{
        if (fragment!=null) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
            return true
        }
        return false
    }

}
