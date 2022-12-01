package com.example.luckynum

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.luckynum.databinding.RecyclerviewItemsBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recyclerview_items.view.*

class numberAdapter( var items:ArrayList<Int>): RecyclerView.Adapter<numberAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerviewItemsBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: numberAdapter.ViewHolder, position: Int) {
        val number:Int=items.get(position)
        holder.roomText.text=number.toString()

        if(position%2==0){
            holder.llMain.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.Gray))
        }else{
            holder.llMain.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.LightGray))

        }
    }
    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(binding: RecyclerviewItemsBinding):RecyclerView.ViewHolder(binding.root){
        val roomText=binding.roomtext
        val llMain=binding.llMain
    }
}

