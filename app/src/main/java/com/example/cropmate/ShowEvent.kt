package com.example.cropmate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cropmate.ui.theme.CropMateTheme

class ShowEvent : ComponentActivity() {
    private lateinit var name: TextView
    private lateinit var desc: TextView
    private lateinit var date: TextView
    private lateinit var prio: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_event)

        name = findViewById(R.id.tvName)
        desc = findViewById(R.id.tvDesc)
        date = findViewById(R.id.tvDate)
        prio = findViewById(R.id.tvPrio)
        button = findViewById(R.id.button)

        name.text = intent.getStringExtra("name")
        desc.text = intent.getStringExtra("desc")
        date.text = intent.getStringExtra("date")
        prio.text = intent.getStringExtra("prio")

        button.setOnClickListener {
            val todoIntent = Intent(this, ToDo::class.java)
            startActivity(todoIntent)
            finish()
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CropMateTheme {
        Greeting2("Android")
    }
}