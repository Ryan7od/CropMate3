package com.example.cropmate

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
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

class ToDo : ComponentActivity(), View.OnClickListener {

    private lateinit var btnBack : Button
    private lateinit var btnAddEvent : Button
    private lateinit var llListContainer : LinearLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)
        btnBack = findViewById(R.id.btnBack)
        btnAddEvent = findViewById(R.id.btnAddEvent)
        llListContainer = findViewById(R.id.llListContainer)

        btnBack.setOnClickListener(this)
        btnAddEvent.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnAddEvent -> {}
            R.id.btnBack -> {
                val homePageIntent = Intent(this, MainActivity::class.java)
                startActivity(homePageIntent)
                finish()
            }
        }
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