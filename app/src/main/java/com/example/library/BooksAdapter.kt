package com.example.library

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class BooksAdapter(private var books: List<Books>, private val context: Context) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    private val db:BooksDatabaseHelper= BooksDatabaseHelper(context)

    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vtext4: TextView = itemView.findViewById(R.id.vtext4)
        val vtext5: TextView = itemView.findViewById(R.id.vtext5)
        val vtext6: TextView = itemView.findViewById(R.id.vtext6)
        val vtext8: TextView = itemView.findViewById(R.id.vtext8)
        val updatebtn: TextView = itemView.findViewById(R.id.updatebtn)
        val deletebtn: TextView = itemView.findViewById(R.id.deletebtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.books_item, parent, false)
        return BooksViewHolder(view)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val book = books[position]
        holder.vtext4.text = book.sname
        holder.vtext5.text = book.bname
        holder.vtext6.text = book.quantity.toString()
        holder.vtext8.text = book.date

        holder.updatebtn.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateBooksActivity::class.java).apply {
                putExtra("books_id", book.id) // Corrected accessing book's id
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deletebtn.setOnClickListener {
            db.deleteBooks(book.id) // Corrected accessing book's id
            refreshData(db.getAllBooks())
            Toast.makeText(context, "Book Deleted", Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshData(newBooks: List<Books>) {
        books = newBooks
        notifyDataSetChanged()
    }
}

