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
import android.widget.ImageButton
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
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ToDo : ComponentActivity() {

    private lateinit var btnBack : ImageButton
    private lateinit var btnAddEvent : Button
    private lateinit var rvToDoList : RecyclerView
    private lateinit var todoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)
        btnBack = findViewById(R.id.btnBack)
        btnAddEvent = findViewById(R.id.btnAddEvent)
        rvToDoList = findViewById(R.id.rvToDoList)

        val list: MutableList<Event> = mutableListOf()
        val db = Firebase.database.getReference("Events")
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(data in dataSnapshot.children) {
                    val data2 = data.getValue(Event::class.java)
                    if (data2 != null) {
                        list.add(data2)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        db.addValueEventListener(valueEventListener)
        todoAdapter = ToDoAdapter(list)

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
            todoAdapter.deleteDoneTodos()
            val homePageIntent = Intent(this, MainActivity::class.java)
            startActivity(homePageIntent)
        }

        btnAddEvent.setOnClickListener {
            showCustomDialogForm()
        }




        rvToDoList.adapter = todoAdapter
        rvToDoList.layoutManager = LinearLayoutManager(this)
    }

    fun showCustomDialogForm() {
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
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            priority.adapter = adapter
        }

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
                val year = date.year
                val month = date.month // Remember, January is 0
                val day = date.dayOfMonth

                val calendar = Calendar.getInstance()
                calendar.set(year, month, day)
                val dateParsed: Date = calendar.time
                todoAdapter.addTodo(
                    Event(
                        name.text.toString(),
                        desc.text.toString(),
                        dateParsed,
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