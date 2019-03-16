package com.afsonlinestore.americanfarrierssupplyshoppingapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.textclassifier.TextClassification
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_items.*

class ItemsAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)


        var cat:String=intent.getStringExtra("cat")

        var url="http://www.afsshoppingapp.com/products/get_catitem.php?item_category=" +cat
        var list=ArrayList<Item>()

        var rq:RequestQueue= Volley.newRequestQueue(this)
        var jar=JsonArrayRequest(Request.Method.GET,url,null, Response.Listener { response ->


            for (x in 0..response.length()-1)
                list.add(Item(response.getJSONObject(x).getInt("id"),
                    response.getJSONObject(x).getString("item_name"),
                    response.getJSONObject(x).getDouble("item_price"),
                    response.getJSONObject(x).getString("item_photo")
                ))

            var adp=ItemAdapter(this,list)
            item_prod.layoutManager=LinearLayoutManager(this)
            item_prod.adapter=adp

        }, Response.ErrorListener { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()

        })
        rq.add(jar)

    }
}
