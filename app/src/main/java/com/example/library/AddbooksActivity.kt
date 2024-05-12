package com.example.library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.library.databinding.ActivityAddbookBinding

class AddbooksActivity : AppCompatActivity() {


    private lateinit var  binding: ActivityAddbookBinding
    private lateinit var db:BooksDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddbookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= BooksDatabaseHelper(this)

        binding.sbtn.setOnClickListener {
            val sname = binding.Etxt22.text.toString()
            val bname = binding.Etxt33.text.toString()
            val quantity = binding.Etxt44.text.toString().toIntOrNull() ?: 0 // Convert quantity to Int or default to 0 if null
            val date = binding.Etxt55.text.toString()
            val books = Books(0, sname,bname, quantity, date)
            db.insertBooks(books)
            finish()
            Toast.makeText(this, "Books Saved", Toast.LENGTH_SHORT).show()
        }
    }
}