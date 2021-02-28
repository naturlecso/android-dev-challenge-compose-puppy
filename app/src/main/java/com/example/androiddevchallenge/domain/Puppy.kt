package com.example.androiddevchallenge.domain

data class Puppy(
    val id: Long,
    val name: String,
    val breed: String,
    val gender: AnimalGender,
    val age: Int,
    val color: String,
    val description: String,
    val weight: Double,
    val height: Double,
    val url: String
)

enum class AnimalGender {
    Female, Male
}
