package com.example.cropmate

interface Item {
    val id: String

    val name: String

    val quantity: String

    val unit: String


    override fun toString(): String

}