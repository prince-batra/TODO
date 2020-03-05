package com.secureapps.entity

data class Task(
    val title: String,
    val note: String,
    val startTime: Long,
    val endTime: Long,
    val remindMe: Boolean,
    val color: String,
    val completed: Int
) {}