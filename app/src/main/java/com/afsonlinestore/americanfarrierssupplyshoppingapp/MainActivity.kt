package com.afsonlinestore.americanfarrierssupplyshoppingapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnSignUp.setOnClickListener{

            var i= Intent(this,signupAct::class.java)
            startActivity(i)
        }

        btnLogin.setOnClickListener {

            var url = "http://www.afsshoppingapp.com/appuser/login_user.php?usermobile=" + phoneLogin.text.toString() + "&userpass=" + passLogin.text.toString()
            var rq: RequestQueue = Volley.newRequestQueue(this)
            var sr= StringRequest(Request.Method.GET,url, Response.Listener { response ->

                if (response.equals("0"))
                    Toast.makeText(this,"Login Failed!", Toast.LENGTH_LONG).show()
                else
                {
                    var i = Intent(this, HomeAct::class.java)
                    startActivity(i)
                }

            }, Response.ErrorListener { error ->

                Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()

            })

            rq.add(sr)

        }

    }
}
