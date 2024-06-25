package com.examples.stomat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.examples.stomat.Const
import com.examples.stomat.R
import com.examples.stomat.model.Advert

class CartAdapter(val callback:(advert:Advert) -> Unit):RecyclerView.Adapter<CartAdapter.CatalogViewHolder>() {
    private var items: List<Advert> ? = null

    inner class CatalogViewHolder(view: View):ViewHolder(view){
        val title = view.findViewById<TextView>(R.id.title)
        val count = view.findViewById<TextView>(R.id.count)
        val image = view.findViewById<ImageView>(R.id.image)
        fun bind(advert:Advert){
            title.text = advert.title
            //advert.c
            val imagePath = "${Const.baseImageUrl}${advert.image}"
            Glide.with(itemView.context)
                .load( imagePath )
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart,parent,false)
        return  CatalogViewHolder(view)
    }

    override fun getItemCount() = items?.size ?: 0

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        items?.get(position)?.let {advert ->
            holder.bind(advert)

            holder.itemView.setOnClickListener {
                callback(advert) }
        }
    }

    fun fillData(adverts: List<Advert>){
        items = adverts
        notifyDataSetChanged()
    }
}