package com.example.retrofit_app

data class PoetryApiDataItem(
    val author: String,
    val linecount: String,
    val lines: List<String>,
    val title: String
)