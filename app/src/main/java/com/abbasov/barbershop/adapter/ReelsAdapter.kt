package com.abbasov.barbershop.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abbasov.barbershop.databinding.ItemReelsBinding
import com.abbasov.barbershop.models.User2
import com.squareup.picasso.Picasso

class ReelsAdapter (val context: Context,  val list:List<User2>): RecyclerView.Adapter<ReelsAdapter.Vh>(){
    inner class Vh(var item: ItemReelsBinding): RecyclerView.ViewHolder(item.root){

        fun onBind(user2: User2, position: Int){

            Picasso.get().load(user2.image).into(item.image)



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReelsAdapter.Vh {
        return Vh(ItemReelsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ReelsAdapter.Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int = list.size

}