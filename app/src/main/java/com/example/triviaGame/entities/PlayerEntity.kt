package com.example.triviaGame.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trivia_game")
data class PlayerEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "username")
    val username: String = "",

    @ColumnInfo(name = "password")
    val password: String = "",

    @ColumnInfo(name = "health_score")
    var healthScore:Int = 0,

    @ColumnInfo(name = "financial_score")
    var financialScore:Int = 0,

    @ColumnInfo(name = "security_score")
    var securityScore:Int = 0,

    @ColumnInfo(name = "current_account")
    var currentAccount:Boolean = false
    )

