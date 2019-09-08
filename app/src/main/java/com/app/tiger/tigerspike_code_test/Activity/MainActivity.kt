package com.app.tiger.tigerspike_code_test.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.tiger.tigerspike_code_test.*
import com.app.tiger.tigerspike_code_test.Adapter.PicsAdapter
import com.app.tiger.tigerspike_code_test.Adapter.RecyclerViewHolder
import com.app.tiger.tigerspike_code_test.DataModel.JsonPicturesModel
import com.app.tiger.tigerspike_code_test.Utilities.Constants
import com.app.tiger.tigerspike_code_test.Utilities.Utility
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity(), RecyclerViewHolder.ItemClickListener {


    private var recyclerview: RecyclerView? = null
    private var picFeed: JsonPicturesModel? = null
    private var picsAdapter: PicsAdapter? = null
    private var jsonBody: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.recyclerView) as RecyclerView
        //Used GridLayout for RecyclerView
        this.recyclerview!!.layoutManager = GridLayoutManager(this,3)

        //function to fetch json Data
        fetchJson()
    }

    fun fetchJson() {
        println("Attempting to Fetch JSON")
        val request = Request.Builder().url(Constants.PICTURES.JSON_FORMAT).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
               //Remove jason brackets and unneccessary word
                jsonBody =  Utility.cleanJsonCode(body)
                val gson = GsonBuilder().create()


                picFeed = gson.fromJson(jsonBody, JsonPicturesModel::class.java)
                //send converted gson data to Adapter
                picsAdapter = PicsAdapter(this@MainActivity!!, this@MainActivity!!, picFeed!!)
                //send adapter to recyclerview on the Main thread
                this@MainActivity!!.runOnUiThread({ recyclerview!!.adapter = picsAdapter })

            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }
        })
    }

    //OnClickListener when user taps recyclerView it would go to the Datailed Page
    override fun onItemClick(view: View, position: Int) {




        val intent = Intent(this, DetailViewActivity::class.java)
        intent.putExtra("Title", picFeed!!.items.get(position).title)
        intent.putExtra("Desc", picFeed!!.items.get(position).description)
        intent.putExtra("Link", picFeed!!.items.get(position).link)
        intent.putExtra("Author", picFeed!!.items.get(position).author)
        intent.putExtra("DateTaken", picFeed!!.items.get(position).dateTaken)
        intent.putExtra("Picture", picFeed!!.items.get(position).media.m)
        startActivity(intent)

    }


}
