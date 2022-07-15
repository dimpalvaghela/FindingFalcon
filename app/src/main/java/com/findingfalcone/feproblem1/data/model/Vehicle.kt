package com.example.findingfalcone.domain.model

import com.findingfalcone.feproblem1.R

data class Vehicle(
    val name: String,
    val amount: Int,
    val maxDistance: Int,
    val speed: Int
)  {
    fun getImage() = when (name) {
        "Space pod" -> R.drawable.ic_space_icon
        "Space rocket" -> R.drawable.ic_space_icon
        "Space shuttle" -> R.drawable.ic_space_icon
        "Space ship" -> R.drawable.ic_space_icon
        else -> throw NoSuchElementException("unknown vehicle")
    }
}