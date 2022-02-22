package com.example.triviaGame.homePage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaGame.R
import com.example.triviaGame.entities.PlayerEntity
import com.example.triviaGame.sharedPreferences
import com.example.triviaGame.themeKey
import kotlinx.android.synthetic.main.leaderboard_item.view.*

class LeaderboardAdapter(private val leadersList: List<LeaderboardEntry>) : RecyclerView.Adapter<LeaderboardAdapter.LeaderboardviewHolder>() {
    class LeaderboardviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val person_name : TextView = itemView.tvPerson
        val person_score : TextView = itemView.tvScore
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardviewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.leaderboard_item, parent, false)
        return LeaderboardviewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LeaderboardviewHolder, position: Int) {
        val currentPlayer = leadersList[position]
        when (position) {
            0 ->  holder.itemView.setBackgroundResource(R.color.gold)
            1 ->  holder.itemView.setBackgroundResource(R.color.silver)
            2 ->  holder.itemView.setBackgroundResource(R.color.bronze)
            else ->  holder.itemView.setBackgroundResource(R.color.other)
        }
        holder.person_name.setText(currentPlayer.username)
        holder.person_score.setText(currentPlayer.score)

    }

    override fun getItemCount(): Int {
        return leadersList.size
    }


}