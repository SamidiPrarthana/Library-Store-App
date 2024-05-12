package com.example.library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.library.databinding.ActivityUpdateBooksBinding

class UpdateBooksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBooksBinding
    private lateinit var db: BooksDatabaseHelper
    private var booksId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBooksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = BooksDatabaseHelper(this)

        booksId = intent.getIntExtra("books_id", -1)
        if (booksId == -1) {
            finish()
            return
        }


        val books = db.getBooksByID(booksId)


        if (books == null) {
            Toast.makeText(this, "Books not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        binding.upt1.setText(books.sname)
        binding.upt2.setText(books.bname)
        binding.upt4.setText(books.quantity.toString())
        binding.upt5.setText(books.date)


        binding.editbtn.setOnClickListener {
            val newSName = binding.upt1.text.toString()
            val newBName = binding.upt2.text.toString()
            val newQuantity = binding.upt4.text.toString().toIntOrNull() ?: 0
            val newDate = binding.upt5.text.toString()


            val updateBooks = Books(booksId, newSName,newBName, newQuantity, newDate)


            db.updateBooks(updateBooks)


            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()


            finish()
        }
    }
}

