package com.example.library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainpageAvtivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainpage)


        val btn = findViewById<Button>(R.id.start1)
        btn.setOnClickListener{
            val Intent = Intent(this,AddbooksActivity::class.java)
            startActivity(Intent)

        }
    }
}