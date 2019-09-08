package com.app.tiger.tigerspike_code_test.Activity

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.app.tiger.tigerspike_code_test.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detailview.view.*
import kotlinx.android.synthetic.main.list_item_chat2.view.*

class DetailViewActivity : AppCompatActivity(){
    var actionbar: ActionBar? = null
    var title: String = ""
    var desc: String = ""
    var link: String = ""
    var author: String = ""
    var dateTaken: String = ""
    var picture: String = ""
    var pictureView: ImageView? = null
    var descText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailview)

        pictureView = findViewById(R.id.imageViewPic) as ImageView
        descText = findViewById(R.id.descText) as TextView
        //actionbar
        actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)


        //get position from main activity
        val bundle :Bundle ?=intent.extras
        if (bundle!=null){
            this.title = bundle.getString("Title")
            this.desc = bundle.getString("Desc")
            this.link = bundle.getString("Link")
            this.author = bundle.getString("Author")
            this.dateTaken = bundle.getString("DateTaken")
            this.picture = bundle.getString("Picture")

            descText?.text = Html.fromHtml(desc)
            Glide.with(this).load(picture).into(pictureView!!.imageViewPic)
            Log.d("Handlers", "Called on main thread $desc")

        }


    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}