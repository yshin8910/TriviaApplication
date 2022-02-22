package com.example.triviaGame.database

import com.example.triviaGame.entities.PlayerEntity

class GlobalUser {
    companion object {
        var user: PlayerEntity? = null
    }
}