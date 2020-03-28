package com.example.testapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item.view.*

class RvAdapter(val mList: List<Book>) : RecyclerView.Adapter<RvAdapter.BooksViewHolder>() {


    inner class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(books: Book) {
            itemView.title.text = books.title
            itemView.decrpt.text = books.descrpt

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return BooksViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val storeGoods = mList[position]
        holder.bind(storeGoods)
    }
}