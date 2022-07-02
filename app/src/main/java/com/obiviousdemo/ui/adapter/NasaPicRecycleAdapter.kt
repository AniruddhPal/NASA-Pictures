package com.obiviousdemo.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.obiviousdemo.R
import com.obiviousdemo.data.model.NasaPicModelItem

class NasaPicRecycleAdapter(val itemList: ArrayList<NasaPicModelItem>, val context: Context) :
    RecyclerView.Adapter<NasaPicRecycleAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_recycle, parent, false)


        return MyViewHolder(view);

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context)
            .load(itemList.get(position).url)
            .into(holder.imageViewUrl)
            .onLoadStarted(context.getDrawable(R.drawable.ic_placeholder_24))


        holder.textViewTitle.text = itemList.get(position).title
        Log.v("NasaPicRecycleAdapter", "Demo Date ::: " + itemList.get(position).date)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageViewUrl = itemView.findViewById<ImageView>(R.id.iv_item)
        var textViewTitle = itemView.findViewById<TextView>(R.id.txtview_title)

    }
}