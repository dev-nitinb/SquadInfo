package com.example.squadinfo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.squadinfo.R
import com.example.squadinfo.model.PlayerInfo

class SquadRecyclerViewAdapter(private val mContext: Context, private val alPlayer: ArrayList<PlayerInfo>): RecyclerView.Adapter<SquadRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(mContext).inflate(R.layout.item_player_name,parent,false)
       return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return alPlayer.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(alPlayer[position])
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvPlayerName=itemView.findViewById<AppCompatTextView>(R.id.tvPlayerName)
        fun bindView(player:PlayerInfo){
            var nameDisplayed=player.fullName
            if(player.iscaptain){
                nameDisplayed += "(c) "
            }
            if(player.iskeeper){
                nameDisplayed += "(wk)"
            }
            tvPlayerName.text=nameDisplayed
        }
    }
}