package com.myschool.app1mvvm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.myschool.app1mvvm.databinding.ActivityMainBinding
import com.myschool.app1mvvm.fragments.NotesListFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, NotesListFragment())
            .addToBackStack(null)
            .commit()

    }
}