package com.example.stomat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stomat.R
import com.example.stomat.model.Category

class CategoryAdapter(val callback: (parentId: Int) -> Unit): RecyclerView.Adapter<CategoryAdapter.CategoryVH>(){
    private var items:List<Category>? = null
    inner class CategoryVH(view: View): RecyclerView.ViewHolder(view){
        private val title = view.findViewById<TextView>(R.id.textView)
        fun bind(category: Category){
            title.text = category.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
        return CategoryVH(view)
    }

    override fun getItemCount() = items?.size ?: 0

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        items?.let {
            val item = it[position]
            holder.bind(item)
            holder.itemView.setOnClickListener {
                item.parentId?.let{callback(it)}
            }
        }
    }

    fun fillData(categories:List<Category>){
        items = categories
        notifyDataSetChanged()
    }
}