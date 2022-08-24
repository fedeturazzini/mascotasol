package com.mascotasol.data.model

import java.io.Serializable

data class Animal(
    val id: Int? = 1,
    val name: String= "",
    val race: String = "",
    val sex: String = "",
    val age: Int = 1,
    val img: Int = 1,
    val description: String = ""
): Serializable
