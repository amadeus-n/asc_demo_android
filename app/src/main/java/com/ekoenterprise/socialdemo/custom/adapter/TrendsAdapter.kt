package com.ekoenterprise.socialdemo.custom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ekoenterprise.socialdemo.custom.model.Member
import com.ekoenterprise.socialdemo.R
import kotlinx.android.synthetic.main.trends_card.view.*

class TrendsAdapter(private val items: ArrayList<Member>,
                    private val listener: ListListener
): RecyclerView.Adapter<TrendsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.trends_card, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { listener.onItemClick() }
    }

    class ViewHolder(itemsView: View): RecyclerView.ViewHolder(itemsView) {
        fun bind(item: Member) {
            itemView.apply {
                val name = item.name
                btn_card.text = name
            }
        }
    }

}