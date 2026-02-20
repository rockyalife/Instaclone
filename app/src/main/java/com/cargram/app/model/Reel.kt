package com.cargram.app.model

data class Reel(
    val id: String,
    val user: User,
    val thumbnailUrl: String = "",
    val videoUrl: String = "",
    val caption: String,
    val carTag: String = "",
    val likesCount: Int,
    val commentsCount: Int,
    val isLiked: Boolean = false
)
