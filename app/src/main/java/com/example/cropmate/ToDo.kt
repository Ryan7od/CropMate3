package com.example.cropmate

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cropmate.todo.Event
import com.example.cropmate.todo.Priority
import com.example.cropmate.todo.ToDoAdapter
import com.example.cropmate.ui.theme.CropMateTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ToDo : ComponentActivity() {

    private lateinit var btnBack : Button
    private lateinit var btnAddEvent : Button
    private lateinit var rvToDoList : RecyclerView
    private lateinit var todoAdapter: ToDoAdapter
    private lateinit var etAddToDo: EditText
    private lateinit var btDelDone: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)
        btnBack = findViewById(R.id.btnBack)
        btnAddEvent = findViewById(R.id.btnAddEvent)
        rvToDoList = findViewById(R.id.rvToDoList)
        btDelDone = findViewById(R.id.btnDelDone)
        todoAdapter = ToDoAdapter(mutableListOf())

        todoAdapter.onItemClick = { item ->
            val showIntent = Intent(this, ShowEvent::class.java).also {
                it.putExtra("name", item.name)
                it.putExtra("desc", item.desc)
                it.putExtra("date", formatDate(item.date))
                it.putExtra("prio", item.priority.toString())
            }
            startActivity(showIntent)
        }


        btnBack.setOnClickListener {
            val homePageIntent = Intent(this, MainActivity::class.java)
            startActivity(homePageIntent)
            finish()
        }

        btnAddEvent.setOnClickListener {
            showCustomDialogForm()
        }

        btDelDone.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }



        rvToDoList.adapter = todoAdapter
        rvToDoList.layoutManager = LinearLayoutManager(this)
    }

    fun showCustomDialogForm() {
        // Inflate the custom layout
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialogue_add_event, null)
        val name = dialogView.findViewById<EditText>(R.id.name)
        val desc = dialogView.findViewById<EditText>(R.id.desc)
        val priority = dialogView.findViewById<Spinner>(R.id.priority)
        val date = dialogView.findViewById<DatePicker>(R.id.date)
        ArrayAdapter.createFromResource(
            this,
            R.array.dropdown_items,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            priority.adapter = adapter
        }

        // Build the dialog
        val builder = AlertDialog.Builder(this)
        var prio = Priority.LOW
        builder.setView(dialogView)
            .setTitle("Custom Form")
            .setPositiveButton("OK") { dialog, which ->
                priority.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                        // Get selected value
                        val selectedItem = parent.getItemAtPosition(position).toString()
                        when(selectedItem) {
                            "Low" -> prio = Priority.LOW
                            "Medium" -> prio = Priority.MEDIUM
                            "High" -> prio = Priority.HIGH
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                    }
                }
                todoAdapter.addTodo(
                    Event(
                        name.text.toString(),
                        desc.text.toString(),
                        Date(date.maxDate),
                        prio,
                )
            )

            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }

        val dialog = builder.create()
        dialog.show()
    }

    fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return formatter.format(date)
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CropMateTheme {
        Greeting("Android")
    }
}