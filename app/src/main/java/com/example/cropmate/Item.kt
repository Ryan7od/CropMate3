package com.example.cropmate

interface Item {
    val Id: String

    val Name: String

    val Quantity: String

    val unit: String


    override fun toString(): String

}