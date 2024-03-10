package com.example.cropmate

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cropmate.ui.theme.CropMateTheme

class InventoryPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_page)

        val homeButton: ImageButton = findViewById(R.id.homeButton)
        homeButton.setOnClickListener {
            val HomePageintent = Intent(this, MainActivity::class.java)
            startActivity(HomePageintent)

            finish()
        }

        val addButton: Button = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            showAddItemDialogue()
        }





    }
    private fun showAddItemDialogue() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.add_item_dialog, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()
        val nameEditText : EditText = dialogView.findViewById(R.id.itemNameEditText)
        val QuantityEditText: EditText = dialogView.findViewById(R.id.itemQuantityEditText)
        val typeEditText: EditText = dialogView.findViewById(R.id.itemTypeEditText)
        val unitEditText: EditText = dialogView.findViewById(R.id.itemUnitEditText)
        val idEditText: EditText = dialogView.findViewById(R.id.itemIdEditText)
        val saveButton: Button = dialogView.findViewById(R.id.saveItemButton)
        saveButton.setOnClickListener {
            val type = typeEditText.text.toString()
            if (type.lowercase() == "capital") {
                addItemToUI(CapitalItem(idEditText.text.toString(), nameEditText.text.toString(), QuantityEditText.text.toString(), unitEditText.text.toString()))
            } else  {
                addItemToUI(ProduceItem(idEditText.text.toString(), nameEditText.text.toString(), QuantityEditText.text.toString(), unitEditText.text.toString()))
            }
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun addItemToUI(item: Item) {
        val inflator = LayoutInflater.from(this)
        val view = inflator.inflate(R.layout.item_layout, null, false)

        val itemNameTextView: TextView = view.findViewById(R.id.itemNameTextView)
        val itemQuantityTextView: TextView = view.findViewById(R.id.itemQuantityTextView)
        val removeButton: Button = view.findViewById(R.id.removeItemButton)


        itemNameTextView.text = "${item.Name}"
        itemQuantityTextView.text = "${item.Quantity}(${item.unit})"

        if (item is CapitalItem) {
            val inventoryLayout: LinearLayout = findViewById(R.id.capitalInventoryLayout)
            inventoryLayout.addView(view)
            removeButton.setOnClickListener {
                inventoryLayout.removeView(view)
            }
        } else {
            val inventoryLayout: LinearLayout = findViewById(R.id.produceInventoryLayout)
            inventoryLayout.addView(view)
            removeButton.setOnClickListener {
                inventoryLayout.removeView(view)
            }
        }


    }






}

