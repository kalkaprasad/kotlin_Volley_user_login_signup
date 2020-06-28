package com.kotlin.Volley

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.HashMap

@Suppress("DEPRECATION")
class Login : AppCompatActivity() {


    lateinit var email: EditText
    lateinit var password: EditText

    var requestQueues: RequestQueue? = null  // this is the declaire the request que

    lateinit var progresdialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.lemail)
        password = findViewById(R.id.lpassword)
        requestQueues = Volley.newRequestQueue(this)
        progresdialog = ProgressDialog(this)


    }

    fun Loginuser(view: View) {

        if (email.text.toString().isEmpty()) {
            email.setError("Email is empty")
            email.requestFocus()

        } else if (password.text.toString().isEmpty()) {
            password.setError("Password is empty")
            password.requestFocus()

        } else {

            progresdialog.setTitle("Loding")
            progresdialog.setMessage("Checking Your Credintials")
            progresdialog.setCancelable(false)
            progresdialog.show()
            var urls = "http://ninfocom.co.in/restpass/api/login.php"

            var stringrequest =
                object : StringRequest(Request.Method.POST, urls, Response.Listener<String>
                { response ->

                    if (response.contentEquals("Data Matched")) {
                        progresdialog.dismiss()
                        Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show()
                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()


                    } else {


                        progresdialog.dismiss()
                        Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show()
                    }


                }, Response.ErrorListener { error ->

                    Toast.makeText(this, "error " + error, Toast.LENGTH_LONG).show()
                }) {
                    @Throws(AuthFailureError::class)
                    override fun getParams(): MutableMap<String, String> {
                        val params = HashMap<String, String>()
                        params.put("email", email.text.toString().trim())
                        params.put("password", password.text.toString().trim())
                        return params
                    }
                }
            requestQueues?.add(stringrequest)
        }
    }
}