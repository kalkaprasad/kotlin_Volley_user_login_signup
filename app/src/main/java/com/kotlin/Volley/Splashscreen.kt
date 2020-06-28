package com.kotlin.Volley

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager

class Splashscreen : AppCompatActivity() {

    lateinit var sharedPreferences :SharedPreferences
    // var share: class ka naam
   var  sharedPrefFile:String="userdata"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

       sharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        //making this activity full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splashscreen)
        //4second splash time
        Handler().postDelayed({
            //start main activity
            val sharedNameValue = sharedPreferences.getString("email","null")
            if(sharedNameValue.toString()!=="null")
            {
                startActivity(Intent(this, MainActivity::class.java))
                //finish this activity
                finish()
            }
            else
            {
                startActivity(Intent(this, Signup::class.java))
                //finish this activity
                finish()
            }

        },5000)

    }
}
