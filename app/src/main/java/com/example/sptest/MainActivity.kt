package com.example.sptest

import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.*
import java.lang.StringBuilder
import java.sql.BatchUpdateException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inputText = onLoad()
        val editText:EditText = findViewById(R.id.editText)
        val button:Button = findViewById(R.id.button)
        if (inputText.isNotEmpty()) {
            editText.setText(inputText)
            editText.setSelection(inputText.length)
            Toast.makeText(this,"Restore succeed",Toast.LENGTH_SHORT).show()
        }
        button.setOnClickListener {
            save(editText.text.toString())
        }
    }
    private fun onLoad() :String {
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e:IOException) {
            e.printStackTrace()
        }
        return content.toString()
    }
    private fun save(inputText:String) {
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(inputText)
            }
            Log.e(TAG,"store success")
        } catch (e:IOException) {
            e.printStackTrace()
            Log.e(TAG,"store error")
        }
    }
}