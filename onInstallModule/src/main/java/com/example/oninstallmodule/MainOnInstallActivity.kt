package com.example.oninstallmodule

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.oninstallmodule.databinding.ActivityMainOnInstallBinding
import com.google.android.play.core.splitcompat.SplitCompat

class MainOnInstallActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainOnInstallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainOnInstallBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.install(this)
    }
}