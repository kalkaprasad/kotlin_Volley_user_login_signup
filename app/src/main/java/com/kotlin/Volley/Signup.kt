package com.kotlin.Volley

import android.app.ProgressDialog
import android.content.Context
import com.android.volley.Request.Method.POST
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.Window
import android.view.textclassifier.TextLinks
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.Request.Method.POST
import com.android.volley.toolbox.JsonObjectRequest

import com.android.volley.toolbox.Volley
import com.kotlin.Volley.VolleySingleton.Companion.instance
import kotlinx.android.synthetic.main.activity_singup.*
import org.json.JSONException
import org.json.JSONObject
import java.time.Duration
import java.util.*

@Suppress("DEPRECATION")
class Signup : AppCompatActivity() {

    lateinit var email:EditText
    lateinit var  password:EditText
    lateinit var cpass:EditText
    lateinit var  signbtn:Button
    private var requestQueue: RequestQueue? = null
    lateinit var progressdialog:ProgressDialog

    private  var  signupdata:String="userdata"
    lateinit var sharedPreferences :SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)
        // casting of the  variable..
        email=findViewById(R.id.cremail)
        password=findViewById(R.id.crpassword)
        cpass=findViewById(R.id.crcpassword)
        signbtn=findViewById(R.id.signupuser)

        requestQueue = Volley.newRequestQueue(this)

        sharedPreferences  =this.getSharedPreferences(signupdata,Context.MODE_PRIVATE)

       progressdialog = ProgressDialog(this)
        signbtn.setOnClickListener {
            CreateAccount()

        }


// this is the for the ACtion bar

    }
    fun Login(view: View) {
        val intent = Intent(this,Login::class.java)
        startActivity(intent)
    }

    private fun CreateAccount() {

        if(email.text.toString().isEmpty())
        {
            Toast.makeText(this,"Email is empty",Toast.LENGTH_LONG).show()
            email.requestFocus()
        }
        else if(password.text.toString().isEmpty())
        {
            Toast.makeText(this,"Password is empty",Toast.LENGTH_LONG).show()
            password.requestFocus()
        }
        else if(cpass.text.toString().isEmpty())
        {
            cpass.requestFocus()

            Toast.makeText(this,"Confirm-Password is empty",Toast.LENGTH_LONG).show()
        }
        else if(!cpass.text.toString().equals(password.text.toString()))
        {
    Toast.makeText(this,"Password did not matched",Toast.LENGTH_LONG).show()

        }
        else{
            progressdialog.setTitle("Please wait")
            progressdialog.setMessage("Creating Your Account ..")
            progressdialog.setCancelable(false)
            progressdialog.show()

            Toast.makeText(this,"Working on progress",Toast.LENGTH_LONG).show()
            val  urls="http://ninfocom.co.in/restpass/api/signup.php"

            val stringRequest = object : StringRequest(Request.Method.POST, "http://ninfocom.co.in/restpass/api/signup.php",
                Response.Listener<String> { response ->


                    val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                    editor.putString("email",email.text.toString())
                    editor.apply()
                    editor.commit()
                    email.setText("")
                    password.setText("")
                    cpass.setText("")
                    progressdialog.dismiss()

                    Toast.makeText(this,"$response",Toast.LENGTH_LONG).show()

                },
                Response.ErrorListener {
                        volleyError -> Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
                    progressdialog.dismiss()

                }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params.put("email", email.text.toString().trim())
                    params.put("password", password.text.toString().trim())
                    return params
                }
            }
            requestQueue?.add(stringRequest)

        }
}
}