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
import kotlinx.android.synthetic.main.activity_signup.*

class SignupAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)



        regSignUp.setOnClickListener {

            if (passReg.text.toString().equals(passConfirm.text.toString())) {

                var url =
                    "http://www.afsshoppingapp.com/appuser/add_user.php?usermobile=" + phoneReg.text.toString() + "&userpass=" + passReg.text.toString() + "&username=" + regName.text.toString()   + "&useradd=" + regAdd.text.toString() + "&useremail="+ regEmail.text.toString()
                var rq: RequestQueue = Volley.newRequestQueue(this)
                var sr= StringRequest(Request.Method.GET,url, Response.Listener { response ->

                    if (response.equals("0"))
                        Toast.makeText(this,"Mobile already Used", Toast.LENGTH_LONG).show()
                    else
                    {
                        var i = Intent(this, MainActivity::class.java)
                        startActivity(i)
                    }

                }, Response.ErrorListener { error ->

                    Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()

                })

                rq.add(sr)
            }

            else {
                Toast.makeText(this,"Password did not match", Toast.LENGTH_LONG).show()
            }
        }


    }
}