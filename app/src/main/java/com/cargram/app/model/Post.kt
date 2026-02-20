package com.cargram.app.model

data class Post(
    val id: String,
    val user: User,
    val imageUrl: String = "",
    val caption: String,
    val hashtags: List<String> = emptyList(),
    val location: String = "",
    val likesCount: Int,
    val commentsCount: Int,
    val timestamp: String,
    val isLiked: Boolean = false,
    val isSaved: Boolean = false,
    val carModel: String = "",
    val carCategory: String = ""
)
