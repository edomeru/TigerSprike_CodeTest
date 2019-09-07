package com.app.tiger.tigerspike_code_test

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.tiger.tigerspike_code_test.DataModel.JsonPicturesModel
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity(),  RecyclerViewHolder.ItemClickListener{


    private var recyclerview: RecyclerView? = null
    private var picFeed: JsonPicturesModel? = null
    private var picsAdapter: PicsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerview = findViewById(R.id.recyclerView) as RecyclerView

        this.recyclerview!!.layoutManager = GridLayoutManager(this,2)
        fetchJson()
    }

    fun fetchJson() {
        println("Attempting to Fetch JSON")

        val request = Request.Builder().url(Constants.url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
             var  b =   body!!.replace("jsonFlickrFeed(","")
             var c=   b.substring(0, b.count() - 1)
                println("Successful request  $c")

                val gson = GsonBuilder().create()


                picFeed = gson.fromJson(c, JsonPicturesModel::class.java)


                println("gson request ${picFeed}")
                picsAdapter =
                    PicsAdapter(
                        this@MainActivity!!,
                        this@MainActivity!!,
                        picFeed!!
                    )

                this@MainActivity!!.runOnUiThread(
                    {
                        recyclerview!!.adapter = picsAdapter

                    })

            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }
        })
    }

    override fun onItemClick(view: View, position: Int) {

//        val intent = Intent(this, Activity_WebV::class.java)
//        intent.putExtra("Position", position)
//        startActivity(intent)
    }

    class PicFeed(val post: List<Items>)

    class Items(val media: List<M>)

    class M(val m: String)
}
