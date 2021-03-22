package org.adako.todo.ui.activity.main

import android.graphics.Paint
import android.text.Spannable
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import org.adako.todo.R
import org.adako.todo.data.model.ToDoEntity
import timber.log.Timber
import java.util.*

class TodoListAdapter: RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    private var data: List<ToDoEntity> = listOf()

    var onItemClick: ((pos: Int) -> Unit)? = null
//    var onLongItemClick: ((pos: Int) -> Unit)? = null
    var onStatusClick: ((pos: Int) -> Unit)? = null
    var onRemoveClick: ((pos: Int) -> Unit)? = null

    fun setData(data: List<ToDoEntity>){
        this.data = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder = TodoViewHolder(LayoutInflater.from(parent.context), parent)

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class TodoViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
        R.layout.item_todo, parent, false))  {

        private var title: AppCompatTextView = itemView.findViewById(R.id.todo_title)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(adapterPosition)
            }
            /*itemView.setOnLongClickListener {
                onLongItemClick?.invoke(adapterPosition)
                true
            }*/
            itemView.setOnCreateContextMenuListener { menu, v, menuInfo ->
                val finish = menu.add(0, v.id, 0, if (data[adapterPosition].isCompleted) "Resume" else "Finish")
                val delete = menu.add(0, v.id, 0, "Delete")
                finish.setOnMenuItemClickListener {
                    onStatusClick?.invoke(adapterPosition)
                    true
                }
                delete.setOnMenuItemClickListener {
                    onRemoveClick?.invoke(adapterPosition)
                    true
                }
            }
        }

        fun bind(item: ToDoEntity){
            if (item.isCompleted){
                title.setText(item.title, TextView.BufferType.SPANNABLE)
                val string = title.text as Spannable
                string.setSpan(StrikethroughSpan(), 0, item.title!!.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            } else {
                title.text = item.title
            }
        }

    }
}

