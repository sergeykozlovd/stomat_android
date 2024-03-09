package com.example.stomat.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stomat.Const
import com.example.stomat.R
import com.example.stomat.model.Advert

class AdvertAdapter: RecyclerView.Adapter<AdvertAdapter.Qww>() {
    private var items: List<Advert>? = null
    inner class Qww(view: View):RecyclerView.ViewHolder(view){
        val title = view.findViewById<TextView>(R.id.title)
        val image = view.findViewById<ImageView>(R.id.image)

        fun bind(advert: Advert){
            title.text = advert.title
            val imagePath = "${Const.baseUrl.replace("/api","/storage")}${advert.image}"
            Glide.with(itemView.context)
                .load( imagePath )
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Qww {

        val  view =  LayoutInflater.from(parent.context).inflate(R.layout.item_advert, parent, false)

        return Qww(view)
    }

    override fun getItemCount() = items?.count() ?: 0

    override fun onBindViewHolder(holder: Qww, position: Int) {
        items?.let {
            holder.bind(it[position])
        }

    }

    fun data(advertList : List<Advert>){

        items = advertList
        notifyDataSetChanged()


    }
}