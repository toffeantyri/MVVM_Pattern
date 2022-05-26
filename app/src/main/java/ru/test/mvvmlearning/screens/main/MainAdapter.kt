package ru.test.mvvmlearning.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*
import ru.test.mvvmlearning.R
import ru.test.mvvmlearning.model.AppNote

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    private var mListNote = emptyList<AppNote>()

    inner class MainHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val nameNote: TextView = item.item_note_name
        private val textNote: TextView = item.item_note_text

        fun bind(position: Int){
            nameNote.text = mListNote[position].name
            textNote.text = mListNote[position].text
        }
    }

    override fun onViewAttachedToWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener {
            MainFragment.clickItem(mListNote[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener(null) // Удаляем слушатель
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return MainHolder(view)
    }


    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = mListNote.size



    fun setList(list: List<AppNote>) {
        mListNote = list
        notifyDataSetChanged()
    }
}