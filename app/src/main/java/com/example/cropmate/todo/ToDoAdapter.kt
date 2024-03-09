package com.example.cropmate.todo

import android.graphics.Color
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.cropmate.R
import android.view.*
import android.widget.TextView

class ToDoAdapter(private val list: MutableList<Event>)
    : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    var onItemClick: ((Event) -> Unit)? = null

    inner class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cbDone: CheckBox = itemView.findViewById(R.id.cbDone)
        var tvEventTitle: TextView = itemView.findViewById(R.id.tvEventTitle)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(list[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.event_item,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Event) {
        list.add(todo)
        list.sortBy { it.date }
        notifyItemInserted(list.size - 1)
    }

    fun deleteDoneTodos() {
        list.sortBy { it.date }
        list.removeAll {
            it.done
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvEventTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvEventTitle.paintFlags = tvEventTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvEventTitle.paintFlags = tvEventTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    private fun setTextColour(tvEventTitle: TextView, priority: Priority) {
        when (priority) {
            Priority.LOW -> { tvEventTitle.setTextColor(Color.parseColor("#FFD300")) }
            Priority.MEDIUM -> { tvEventTitle.setTextColor(Color.parseColor("#ED7117")) }
            Priority.HIGH -> { tvEventTitle.setTextColor(Color.parseColor("#800000")) }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentToDo = list[position]
        holder.apply {
            tvEventTitle.text = currentToDo.name
            cbDone.isChecked = currentToDo.done
            toggleStrikeThrough(tvEventTitle, currentToDo.done)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvEventTitle, isChecked)
                currentToDo.done = isChecked
            }
            setTextColour(tvEventTitle, currentToDo.priority)
        }
    }


}