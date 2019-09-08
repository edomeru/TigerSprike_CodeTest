package com.app.tiger.tigerspike_code_test.Adapter

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import com.app.tiger.tigerspike_code_test.DataModel.Item
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_chat2.view.*


class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //Listener
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)

    }


    fun setData(hobby: Item, pos: Int, activity: AppCompatActivity) {


        hobby?.let {
              itemView.nme.text = hobby.title
            Glide.with(activity).load(hobby.media.m).into(itemView.iv_user_photo_cv)
        }?: run {

        }
    }
}