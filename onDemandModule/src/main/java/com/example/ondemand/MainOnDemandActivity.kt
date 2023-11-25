package com.example.ondemand

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ondemand.databinding.ActivityMainOnDemandBinding
import com.google.android.play.core.splitcompat.SplitCompat

class MainOnDemandActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainOnDemandBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainOnDemandBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.install(this)
    }
}