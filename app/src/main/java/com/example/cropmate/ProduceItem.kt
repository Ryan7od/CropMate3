package com.example.cropmate

class ProduceItem(override val Id: String = "",
                  override val Name: String = "",
                  override val Quantity: String = "",
                  override val unit: String = ""
): Item {
    override fun toString(): String {
        return "$Name : $Quantity $unit"
    }
}