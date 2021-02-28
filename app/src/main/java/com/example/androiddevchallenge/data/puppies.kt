/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.domain.AnimalGender
import com.example.androiddevchallenge.domain.Puppy
import kotlin.random.Random

private const val PUPPY_COUNT = 20

private const val DESCRIPTION = "I'm one of a kind, got everybody in love\n" +
    "And I don't have to try, I just do what I does\n" +
    "Don't have to tell me, I already know\n" +
    "They all want me"

private val NAMES = listOf(
    "Alexis", "Ace", "Alice", "Aero", "Cookie", "Butch", "Cricket", "Buzz",
    "Harley", "Flash", "Harper", "Frankie", "Hazel", "Freddy", "Morgan", "Pepper",
    "Moxie", "Priscilla", "Scooby", "Raven", "Scooter"
)

private val BREEDS = listOf(
    "Labrador", "German Shepherd Dog", "French Bulldog", "Bulldogs", "Poodles",
    "Beagles", "Rottweilers", "Corgis", "Yorkshire Terriers"
)

private val COLORS = listOf(
    "black", "brown", "white", "red", "gold", "cream", "grey"
)

private val URLS = listOf(
    "https://images.dog.ceo/breeds/spaniel-irish/n02102973_3642.jpg",
    "https://images.dog.ceo/breeds/finnish-lapphund/mochilamvan.jpg",
    "https://images.dog.ceo/breeds/mastiff-english/4.jpg",
    "https://images.dog.ceo/breeds/bullterrier-staffordshire/n02093256_6177.jpg",
    "https://images.dog.ceo/breeds/cotondetulear/100_2013.jpg",
    "https://images.dog.ceo/breeds/schnauzer-miniature/n02097047_951.jpg",
    "https://images.dog.ceo/breeds/mexicanhairless/n02113978_304.jpg",
    "https://images.dog.ceo/breeds/sheepdog-english/n02105641_11651.jpg",
    "https://images.dog.ceo/breeds/lhasa/n02098413_21040.jpg",
    "https://images.dog.ceo/breeds/terrier-russell/jack1.jpg",
    "https://images.dog.ceo/breeds/poodle-toy/n02113624_983.jpg",
    "https://images.dog.ceo/breeds/mountain-bernese/n02107683_2043.jpg",
    "https://images.dog.ceo/breeds/bullterrier-staffordshire/n02093256_5654.jpg",
    "https://images.dog.ceo/breeds/saluki/n02091831_3152.jpg",
    "https://images.dog.ceo/breeds/terrier-toy/n02087046_3608.jpg",
    "https://images.dog.ceo/breeds/redbone/n02090379_5393.jpg",
    "https://images.dog.ceo/breeds/poodle-toy/n02113624_1220.jpg",
    "https://images.dog.ceo/breeds/whippet/n02091134_1131.jpg",
    "https://images.dog.ceo/breeds/pomeranian/n02112018_6676.jpg",
    "https://images.dog.ceo/breeds/terrier-irish/n02093991_1978.jpg"
)

val puppies = (0..PUPPY_COUNT).map {
    Puppy(
        id = it.toLong(),
        name = NAMES.random(),
        breed = BREEDS.random(),
        gender = AnimalGender.values().random(),
        age = (0..5).random(),
        color = COLORS.random(),
        description = DESCRIPTION,
        weight = Random.nextDouble(3.0, 12.0),
        height = Random.nextDouble(0.3, 1.5),
        url = URLS.random()
    )
}
