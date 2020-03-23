package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginTop
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var patern: Regex = "^(?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d].{6,}\$".toRegex();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button_login.setOnClickListener {
            login();


        }
        spinner.setOnClickListener{
            if (spinner1.visibility==View.GONE) {
                spinner1.visibility = View.VISIBLE
                val param = spinner1.layoutParams as LinearLayout.LayoutParams
                param.setMargins(25,25,25,25)
                spinner1.layoutParams = param
            }else
                spinner1.visibility=View.GONE
        }
        spinner1.setOnClickListener{
            if (spinner.visibility==View.GONE)
                spinner.visibility=View.VISIBLE
            else
                spinner.visibility=View.GONE
        }
    }

    fun login() {

        if (!validate()) {
            onLoginFailed()
            return
        } else
            onLoginSuccess()

        button_login!!.isEnabled = false


    }


    fun onLoginSuccess() {
        button_login!!.isEnabled = true
        Toast.makeText(this@MainActivity, "Login Success", Toast.LENGTH_LONG).show()
    }

    fun onLoginFailed() {
        Toast.makeText(baseContext, "Login failed", Toast.LENGTH_LONG).show()
        button_login!!.isEnabled = true
    }

    fun validate(): Boolean {
        var valid = true

        val email = edittext_email!!.text.toString()
        val password = edittext_password!!.text.toString()

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this@MainActivity, "enter a valid email address", Toast.LENGTH_LONG).show()
            valid = false
        } else {
            edittext_email!!.error = null
        }

        if (password.isEmpty() || password.length < 6 || password.matches(patern)) {
            Toast.makeText(this@MainActivity, "More tham 6 alphanumeric characters", Toast.LENGTH_LONG).show()
            valid = false
        } else {
            edittext_password!!.error = null
        }

        return valid
    }


}
