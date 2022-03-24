package com.example.sptest

import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText:EditText = findViewById(R.id.editText)
        val button:Button = findViewById(R.id.button)
        val button2:Button = findViewById(R.id.button_show)

        button.setOnClickListener {
            val editor = getSharedPreferences("data",Context.MODE_PRIVATE).edit()
            editor.putString("name","Tom")
            editor.putInt("age",28)
            editor.putBoolean("married",false)
            editor.apply()
        }

        button2.setOnClickListener {
            val prefs = getSharedPreferences("data",Context.MODE_PRIVATE)
            val name = prefs.getString("name","")
            val age = prefs.getInt("age",0)
            val married = prefs.getBoolean("married",false)
            editText.setText(name)
            if (name != null) {
                Log.e(TAG,name)
                Log.e(TAG,age.toString())
                Log.e(TAG,married.toString())
            }
        }

    }

}