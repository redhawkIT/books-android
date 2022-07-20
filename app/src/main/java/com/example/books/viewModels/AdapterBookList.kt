package com.example.books.viewModels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.books.R
import com.example.books.models.Book
import com.example.books.views.OnBottomReachedListener
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdapterBookList(val listener: OnBottomReachedListener): ListAdapter<Book, AdapterBookList.BookCellViewHolder>(BookCallBack()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addBooks(books: List<Book>?)
    {
        adapterScope.launch {

            withContext(Dispatchers.Main){
                submitList(books)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterBookList.BookCellViewHolder {
        val inflateView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_book, parent, false)

        return BookCellViewHolder(inflateView)
    }

    override fun onBindViewHolder(holder: AdapterBookList.BookCellViewHolder, position: Int) {
        val itemBook = getItem(position)
        holder.bindData(itemBook)

        if (position == itemCount - 1){
            listener.onBottomReached()
        }
    }

    class BookCellViewHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        private var imgThumb: ImageView
        private var txtTitle: TextView
        private var txtDescription: TextView

        private var book: Book? = null

        init {

            imgThumb = view.findViewById<View>(R.id.iv_thumb) as ImageView
            txtTitle = view.findViewById<View>(R.id.tv_title) as TextView
            txtDescription = view.findViewById<View>(R.id.tv_description) as TextView
        }

        fun bindData(data: Book)
        {
            this.book = data
            val imgURI = data.volumeInfo.imageLinks!!.get("smallThumbnail").asString
            Picasso.get()
                .load(imgURI)
                .placeholder(view.context.getDrawable(R.drawable.bg_blind)!!)
                .into(imgThumb)
            txtTitle.text = book!!.volumeInfo.title
            txtDescription.text = book!!.volumeInfo.description

        }
    }
}

class BookCallBack: DiffUtil.ItemCallback<Book>()
{
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }

}