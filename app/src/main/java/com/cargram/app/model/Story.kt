package com.cargram.app.model

data class Story(
    val id: String,
    val user: User,
    val imageUrl: String = "",
    val isViewed: Boolean = false,
    val isOwn: Boolean = false
)
