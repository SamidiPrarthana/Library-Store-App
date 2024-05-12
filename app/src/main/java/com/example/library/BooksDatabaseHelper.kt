package com.example.library

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BooksDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "booksapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allbooks"
        private const val COLUMN_ID = "id"
        private const val COLUMN_STUDENTNAME = "sname"
        private const val COLUMN_BOOKNAME = "bname"
        private const val COLUMN_QUANTITY = "quantity"
        private const val COLUMN_DATE = "date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_STUDENTNAME TEXT, $COLUMN_BOOKNAME TEXT, $COLUMN_QUANTITY INTEGER, $COLUMN_DATE TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertBooks(books: Books) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_STUDENTNAME, books.sname)
            put(COLUMN_BOOKNAME, books.bname)
            put(COLUMN_QUANTITY, books.quantity)
            put(COLUMN_DATE, books.date)
        }

        db.insert(TABLE_NAME, null, values)
        db.close()
    }


    fun getAllBooks(): List<Books>{
        val bookList= mutableListOf<Books>()
        val db = readableDatabase
        val query="SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query,null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val sname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENTNAME))
            val bname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOOKNAME))
            val quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY))
            val date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))

            val books = Books(id,sname,bname,quantity,date)
            bookList.add(books)
        }
        cursor.close()
        db.close()
        return bookList
    }

    fun updateBooks(books: Books){
        val db = writableDatabase
        val values=ContentValues().apply {
            put(COLUMN_STUDENTNAME,books.sname)
            put(COLUMN_BOOKNAME,books.bname)
            put(COLUMN_QUANTITY,books.quantity)
            put(COLUMN_DATE,books.date)
        }

        val whereClause = "$COLUMN_ID =?"
        val whereArgs = arrayOf(books.id.toString())
        db.update(TABLE_NAME, values, whereClause,whereArgs)
        db.close()
    }
    fun getBooksByID(booksId:Int):Books{
        val db= readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $booksId "
        val cursor = db.rawQuery(query,null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val sname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENTNAME))
        val bname = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOOKNAME))
        val quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY))
        val date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))

        cursor.close()
        db.close()
        return Books(id,sname,bname,quantity,date)
    }
    fun deleteBooks(booksId: Int){
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(booksId.toString())
        db.delete(TABLE_NAME,whereClause,whereArgs)
        db.close()
    }
    }


