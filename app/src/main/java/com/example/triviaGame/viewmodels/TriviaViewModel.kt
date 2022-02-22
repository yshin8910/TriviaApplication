package com.example.triviaGame.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope

import com.example.triviaGame.database.TriviaDao
import com.example.triviaGame.database.GlobalUser
import com.example.triviaGame.entities.PlayerEntity
import com.example.triviaGame.homePage.LeaderboardEntry
import kotlinx.coroutines.launch

class TriviaViewModel(
    val database: TriviaDao,
    application: Application
): AndroidViewModel(application) {

    //launch coroutine to insert a new player
    fun insertPlayer(playerEntity: PlayerEntity): Boolean {
        var result: Boolean = false
        viewModelScope.launch {
            result = insertPlayerInDatabase(playerEntity)
        }
        return result
    }

    fun getScoresFor(playerEntity: PlayerEntity):
            List<PlayerEntity> {
        var result = listOf(PlayerEntity())
        viewModelScope.launch {
            result = getAllInDatabase()
        }
        if (result.isNotEmpty()) {
            val players:ArrayList<PlayerEntity> = arrayListOf()
            var i = 0
            for (entry in result) {
                if (entry.username == playerEntity.username) {
                    players.add(entry)
                    i++
                }
            }
            return players
        }
        return listOf(PlayerEntity())
    }

    fun deleteAllAccounts() {
        viewModelScope.launch {
            deleteAllAccountsInDatabase()
        }
    }

    private suspend fun insertPlayerInDatabase(playerEntity:
                                               PlayerEntity): Boolean {
        return database.insert(playerEntity) > 0
    }

    //returns true if an account with the given username is already
    //present in the database
    fun containsAccount(playerEntity: PlayerEntity): Boolean {
        var result = listOf(PlayerEntity())
        viewModelScope.launch {
            result = getAllInDatabase()
        }
        if (result.isNotEmpty()) {
            for (entry in result) {
                if (entry.username == playerEntity.username)
                    return true
            }
        }
        return false
    }

    //returns true if both username and password of account match
    fun checkLogin(playerEntity: PlayerEntity): Boolean {
        var result = listOf<PlayerEntity>()
        viewModelScope.launch {
            result = getAllInDatabase()
        }
        if (result.isNotEmpty()) {
            for (entry in result) {
                if (entry.username == playerEntity.username
                    && entry.password == playerEntity.password)
                    return true
            }
        }
        return false
    }

    fun getAccount(username: String, password: String): PlayerEntity? {
        var result = listOf<PlayerEntity>()
        viewModelScope.launch {
            result = getAllInDatabase()
        }
        if (result.isNotEmpty()) {
            for (entry in result) {
                if (entry.username.equals(username)
                    && entry.password.equals(password))
                    return entry
            }
        }

        return null
    }


    fun getCurrentUser(): PlayerEntity {
        var result = listOf(PlayerEntity())
        var user = PlayerEntity()
        viewModelScope.launch {
            result = getAllInDatabase()
        }
        if (result.isNotEmpty()) {
            for (entry in result) {
                if (entry.currentAccount)
                    user = entry
            }
        }
        return user
    }

    fun setCurrentUser(playerEntity: PlayerEntity) {
        viewModelScope.launch {
            removeAccount(playerEntity)
            playerEntity.currentAccount = true
            insertPlayer(playerEntity)
        }
    }

    fun removeAccount(playerEntity: PlayerEntity): Boolean {
        var result = listOf<PlayerEntity>()
        viewModelScope.launch {
            result = getAllInDatabase()
        }
        if (result.isNotEmpty()) {
            for (entry in result) {
                if (entry.username == playerEntity.username &&
                    entry.password == playerEntity.password) {
                    viewModelScope.launch {
                        deleteVacationInDatabase(playerEntity)
                    }
                }
            }
        }
        var result2 = listOf<PlayerEntity>()
        viewModelScope.launch {
            result2 = getAllInDatabase()
        }
        return result.size != result2.size
    }

    fun removeCurrentUser() {
        var result = listOf(PlayerEntity())
        var user:PlayerEntity
        viewModelScope.launch {
            result = getAllInDatabase()
        }
        if (result.isNotEmpty()) {
            for (entry in result) {
                if (entry.currentAccount) {
                    removeAccount(entry)
                    user = entry
                    user.currentAccount = false
                    insertPlayer(user)
                }
            }
        }
    }

    fun addHealthcareScore() {
        var user = GlobalUser.user!!
        user.healthScore = user.healthScore + 1
        Log.d("HEALTHSCORE", "Adding is now ${user.healthScore} for user ${user.username}")
        database.updateScores(user)
    }


    fun addFinanceScore() {
        var user = GlobalUser.user!!
        user.financialScore = user.financialScore + 1
        Log.d("HEALTHSCORE", "Adding is now ${user.financialScore} for user ${user.username}")
        database.updateScores(user)
    }


    fun addCSScore() {
        var user = GlobalUser.user!!
        user.securityScore = user.securityScore + 1
        Log.d("HEALTHSCORE", "Adding is now ${user.securityScore} for user ${user.username}")
        database.updateScores(user)
    }

    fun get_cs_leaders(): List<LeaderboardEntry> {
        var leaders = database.getCSScore()
        return leaders
    }

    fun get_financial_leaders(): List<LeaderboardEntry> {
        var leaders = database.getFinancesScore()
        return leaders
    }

    fun get_healthcare_leaders(): List<LeaderboardEntry> {
        var leaders = database.getHealthcareScore()
        return leaders
    }


    private suspend fun deleteAllAccountsInDatabase(): Boolean {
        return database.clear() > 0
    }

    private suspend fun deleteVacationInDatabase(vacationEntity: PlayerEntity): Int {
        return database.removePlayer(vacationEntity)
    }

    private suspend fun getAllInDatabase(): List<PlayerEntity> {
        return database.getAll()
    }
}