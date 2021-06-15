package com.ekoenterprise.socialdemo.custom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ekoenterprise.socialdemo.R
import com.ekoenterprise.socialdemo.custom.model.Content
import kotlinx.android.synthetic.main.content_recycler.view.*


class ContentAdapter(
    private val items: ArrayList<Content>,
    private val listener: ListListener
) : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.content_recycler, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { listener.onItemClick() }
    }

    class ViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        fun bind(item: Content) {
            itemView.apply {
                val name = item.name
                content_title.text = name

                val description = item.description
                content_desc.text = description

                val views = item.views
                content_views.text = views.toString() + " peoples are discussing this."

                val imageResourse = item.imgSource
                val id = resources.getIdentifier("@drawable/$imageResourse",null, context!!.packageName)
                content_thumdnail.setImageResource(id)
            }
        }
    }

}