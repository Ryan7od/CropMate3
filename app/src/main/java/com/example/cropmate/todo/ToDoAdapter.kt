package com.example.cropmate.todo

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.cropmate.R
import android.view.*

class ToDoAdapter(private val list: MutableList<Event>)
    : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_to_do,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Event) {
        list.add(todo)
        notifyItemInserted(list.size - 1)
    }

    fun deleteDoneTodos() {
        list.removeAll {
            it.done
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(cbDone: CheckBox, isChecked: Boolean) {
        if(isChecked) {
            cbDone.paintFlags = cbDone.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            cbDone.paintFlags = cbDone.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentToDo = list[position]
        holder.itemView.apply {
            cbDone.text = currentToDo.name
            cbDone.isChecked = currentToDo.done
            toggleStrikeThrough(cbDone, currentToDo.done)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(cbDone, isChecked)
                currentToDo.done = !currentToDo.done
            }
        }
    }


}