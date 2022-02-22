package com.example.triviaGame.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


import androidx.room.*
import com.example.triviaGame.entities.PlayerEntity
import com.example.triviaGame.homePage.LeaderboardEntry


@Dao
interface TriviaDao {

    @Query("DELETE FROM TRIVIA_GAME")
    suspend fun clear(): Int

    @Delete
    suspend fun removePlayer(vacationEntity: PlayerEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: PlayerEntity): Long

    @Query("SELECT * FROM TRIVIA_GAME")
    fun getAll(): List<PlayerEntity>

    @Update
    fun updateScores(account: PlayerEntity)

    @Query("SELECT username, health_score AS score FROM TRIVIA_GAME ORDER BY security_score DESC LIMIT 5")
    fun getHealthcareScore(): List<LeaderboardEntry>

    @Query("SELECT username, financial_score AS score FROM TRIVIA_GAME ORDER BY financial_score DESC LIMIT 5")
    fun getFinancesScore(): List<LeaderboardEntry>

    @Query("SELECT username, security_score AS score FROM TRIVIA_GAME ORDER BY security_score DESC LIMIT 5")
    fun getCSScore(): List<LeaderboardEntry>


}