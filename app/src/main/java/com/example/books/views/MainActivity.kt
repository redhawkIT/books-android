package com.example.books.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.books.R
import com.example.books.viewModels.MainViewModel

class MainActivity : BaseActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var rvBook: RecyclerView
    private lateinit var mainViewModel: MainViewModel

    var startIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvBook = findViewById(R.id.rv_book)

        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvBook.layoutManager = linearLayoutManager
        mainViewModel = MainViewModel()

        loadBooks(startIndex)
    }

    private fun loadBooks(index: Int) {
        showProgressHUD()

        mainViewModel.loadBooksByIndex(index) { err, listBook ->

            hideProgressHUD()
            if (err != null)
            {
                Toast.makeText(this, err, Toast.LENGTH_SHORT).show()
                return@loadBooksByIndex
            }

        }
    }
}