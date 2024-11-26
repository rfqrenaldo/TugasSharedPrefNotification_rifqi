package com.example.broadcast

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.broadcast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        prefManager = PrefManager.getInstance(this)
        checkLoginStatus()
        with(binding){
            txtUsername.text = prefManager.getUsername()
            btnLogout.setOnClickListener{
                prefManager.saveUsername("")
                checkLoginStatus()
            }
            btnClear.setOnClickListener {
                prefManager.clear()
                checkLoginStatus()
            }
        }
    }

    private fun checkLoginStatus() {
        val username = prefManager.getUsername()
        if (username.isEmpty()){
            startActivity(
                Intent(this@MainActivity, LoginActivity::class.java)
            )
            finish()
        }
    }
}