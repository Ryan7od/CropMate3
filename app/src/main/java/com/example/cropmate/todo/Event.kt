package com.example.cropmate.todo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import kotlin.random.Random

enum class Priority {
    LOW,
    MEDIUM,
    HIGH;

    override fun toString(): String {
        return when (this) {
            LOW -> "Low"
            MEDIUM -> "Medium"
            HIGH -> "High"
        }
    }
}

class Event(
    val name: String = "",
    val desc: String = "",
    val date: Date = Date(1),
    val priority: Priority = Priority.LOW,
) {
    var done: Boolean = false
    val id: Int = Random(System.currentTimeMillis()).nextInt(100000000)
}