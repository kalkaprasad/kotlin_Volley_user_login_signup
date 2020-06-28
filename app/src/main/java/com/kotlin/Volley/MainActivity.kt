package com.kotlin.Volley

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var  sharedPreferences: SharedPreferences

    private  var userdetails:String="userdata"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var actionbar = supportActionBar
        actionbar!!.title="Welcome page"
        sharedPreferences=this.getSharedPreferences(userdetails,Context.MODE_PRIVATE)
    }
    fun logout(view: View) {
        var getvalue= sharedPreferences.edit()
        getvalue.clear()
        getvalue.apply()

        var intentpass= Intent(this,Login::class.java)
        startActivity(intentpass)
        finish()

    }

    override fun onBackPressed() {

        AlertDialog.Builder(this)
            .setTitle("EXIT")
            .setMessage("Are you sure you want to Exit ") // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(android.R.string.yes,
                DialogInterface.OnClickListener { dialog, which -> // Continue with delete operation
                    finish()
                }) // A null listener allows the button to dismiss the dialog and take no further action.
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
}
