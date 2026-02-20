package com.cargram.app.model

data class User(
    val id: String,
    val username: String,
    val fullName: String,
    val avatarUrl: String = "",
    val bio: String = "",
    val carTitle: String = "",
    val postsCount: Int = 0,
    val followersCount: Int = 0,
    val followingCount: Int = 0,
    val isVerified: Boolean = false,
    val isFollowing: Boolean = false,
    val cars: List<String> = emptyList()
)
