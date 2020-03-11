package com.secureapps.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "startTime") val startTime: String,
    @ColumnInfo(name = "endTime") val endTime: String,
    @ColumnInfo(name = "remindMe") val remindMe: Int,
    @ColumnInfo(name = "color") val color: String,
    @ColumnInfo(name = "completed") val completed: Int
) {}