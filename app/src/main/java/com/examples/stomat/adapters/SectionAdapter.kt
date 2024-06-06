package com.examples.stomat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examples.stomat.Const
import com.examples.stomat.R
import com.examples.stomat.model.Category

class SectionAdapter(val callback: (category: Category) -> Unit) :
    RecyclerView.Adapter<SectionAdapter.AdvertVH>() {
    private var items: List<Category>? = null

    inner class AdvertVH(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.name)
        val image = view.findViewById<ImageView>(R.id.image)

        fun bind(category: Category) {
            name.text = category.name

            category.image?.let {
                val imagePath = "${Const.baseImageUrl}${it}"
                Glide.with(itemView.context)
                    .load(imagePath)
                    .into(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_section, parent, false)
        return AdvertVH(view)
    }

    override fun getItemCount() = items?.count() ?: 0

    override fun onBindViewHolder(holder: AdvertVH, position: Int) {
        items?.get(position)?.let { category ->
            holder.bind(category)
            holder.itemView.setOnClickListener {
                callback(category)
            }
        }
    }

    fun data(advertList: List<Category>) {
        items = advertList
        notifyDataSetChanged()
    }
}