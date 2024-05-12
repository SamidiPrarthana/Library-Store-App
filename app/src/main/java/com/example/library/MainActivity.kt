package com.example.library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.library.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: BooksDatabaseHelper
    private lateinit var bookAdapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) 
        setContentView(binding.root)

        db = BooksDatabaseHelper(this)
        bookAdapter = BooksAdapter(db.getAllBooks(), this)

        binding.LRV.layoutManager = LinearLayoutManager(this) 
        binding.LRV.adapter = bookAdapter 

        binding.btnadd.setOnClickListener {
            val intent = Intent(this, AddbooksActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        bookAdapter.refreshData(db.getAllBooks()) // Refresh data on resume
    }
}

