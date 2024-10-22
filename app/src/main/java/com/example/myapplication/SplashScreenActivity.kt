package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class SplashScreenActivity : AppCompatActivity() {

    private val imageList = listOf(
        R.drawable.img,
        R.drawable.img_1,
        R.drawable.img_2,
        R.drawable.img_3,
        R.drawable.img_4,
        R.drawable.img_5,
        R.drawable.img_6,
        R.drawable.img_7,
        R.drawable.img_8,
        R.drawable.img_9,
        R.drawable.img_10,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val adapter = SplashScreenAdapter(this, imageList)
        viewPager.adapter = adapter

        // Автоматическое переключение слайдов
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                val currentItem = viewPager.currentItem
                viewPager.setCurrentItem((currentItem + 1) % imageList.size, true)
                handler.postDelayed(this, 1) // Скорость переключения в миллисекундах
            }
        }
        handler.postDelayed(runnable, 5)

        // Переход на MainActivity спустя 2000 мс
        val intent = Intent(this, MainActivity::class.java)
        handler.postDelayed({
            startActivity(intent)
            finish()
        }, 2000)
    }

    class SplashScreenAdapter(activity: FragmentActivity, private val imageList: List<Int>) : FragmentStateAdapter(activity) {

        override fun getItemCount(): Int {
            return imageList.size
        }

        override fun createFragment(position: Int): Fragment {
            return SplashScreenFragment.newInstance(imageList[position])
        }
    }
}

