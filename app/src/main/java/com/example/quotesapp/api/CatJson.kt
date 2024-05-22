package com.example.quotesapp.api

data class CatJson(
    val v: Int,
    val id: String,
    val createdAt: String,
    val deleted: Boolean,
    val status: Status,
    val text: String,
    val type: String,
    val updatedAt: String,
    val user: String
)