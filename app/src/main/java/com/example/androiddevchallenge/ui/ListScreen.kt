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
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.domain.Puppy
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PuppyListScreen(
    puppies: List<Puppy>,
    onSelect: (Puppy) -> Unit
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "\uD83D\uDC36",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }
    ) {
        PuppyList(
            puppies = puppies,
            onSelect = onSelect
        )
    }
}

@Composable
private fun PuppyList(
    puppies: List<Puppy>,
    onSelect: (Puppy) -> Unit
) {
    LazyColumn {
        items(puppies) {
            Column {
                PuppyListRow(
                    puppy = it,
                    onClick = { onSelect.invoke(it) }
                )

                Divider()
            }
        }
    }
}

@Composable
private fun PuppyListRow(
    puppy: Puppy,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        CoilImage(
            data = puppy.url,
            contentDescription = puppy.name,
            requestBuilder = {
                transformations(CircleCropTransformation())
            },
            modifier = Modifier
                .requiredSize(64.dp)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
        ) {
            Text(
                text = puppy.name,
                style = MaterialTheme.typography.h6
            )

            Text(
                text = "${puppy.breed} / ${puppy.gender.name} / ${ageRange(puppy.age)}"
            )
        }
    }
}

@Composable
private fun ageRange(age: Int) = when {
    age <= 1 -> R.string.age_puppy
    age <= 3 -> R.string.age_young
    else -> R.string.age_adult
}.let { stringResource(it) }
