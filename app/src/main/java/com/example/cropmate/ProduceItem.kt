package com.example.cropmate

import kotlin.random.Random

class ProduceItem(override val name: String = "",
                  override val quantity: String = "",
                  override val unit: String = "",
): Item {
    override val id: String = Random(System.currentTimeMillis()).nextInt(1000000).toString()
    override fun toString(): String {
        return "$name : $quantity $unit"
    }
}