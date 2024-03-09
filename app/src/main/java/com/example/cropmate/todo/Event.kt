package com.example.cropmate.todo

import java.util.Date

enum class Priority {
    LOW,
    MEDIUM,
    HIGH,
}

class Event(
    private val id: Int,
    val name: String,
    private val desc: String,
    private val date: Date,
    private val priority: Priority,
    var done: Boolean,
) {
}