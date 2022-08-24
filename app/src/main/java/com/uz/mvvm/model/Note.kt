package com.uz.mvvm.model


/**
 * Created by Eldor Turgunov on 24.08.2022.
 * Mvvm
 * eldorturgunov777@gmail.com
 */
data class Note(
    val title: String,
    val body: String,
    var id: Int,
    val userId: Int
)