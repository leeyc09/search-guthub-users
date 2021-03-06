package ru.alekseyk.testskblab.presentation.models

import java.io.Serializable

data class RepositoryModel(
    val description: String?,
    val fullName: String,
    val id: Int,
    val name: String,
    val ownerId: Int,
    val ownerLogin: String,
    val ownerAvatarUrl: String,
    val ownerUrl: String,
    val isFavorite: Boolean,
    val stargazersCount: Int,
    val forksCount: Int,
    val createdAt: String
) : Serializable