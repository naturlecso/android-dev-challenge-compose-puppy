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

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.domain.Puppy
import dev.chrisbanes.accompanist.coil.CoilImage
import java.util.Locale

@Composable
fun PuppyDetailsScreen(
    puppy: Puppy?,
    onBackPressed: () -> Unit
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.details_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.cd_back)
                        )
                    }
                }
            )
        }
    ) {
        puppy?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .scrollable(
                        state = rememberScrollState(),
                        orientation = Orientation.Vertical
                    )
            ) {
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )

                PuppyImage(
                    puppy = it,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "${it.breed} / ${it.gender.name}",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp)
                )

                Divider()

                PuppyDetails(puppy = it)

                PuppyDescription(puppy = it)
            }
        }
    }
}

@Composable
private fun PuppyImage(
    puppy: Puppy,
    modifier: Modifier = Modifier
) {
    CoilImage(
        data = puppy.url,
        contentDescription = puppy.name,
        requestBuilder = {
            transformations(CircleCropTransformation())
        },
        modifier = modifier
            .fillMaxWidth(fraction = 0.5f)
            .aspectRatio(1f)
            .padding(24.dp)
            .border(
                width = 4.dp,
                color = MaterialTheme.colors.secondary,
                shape = CircleShape
            )
    )
}

@Composable
private fun PuppyDetails(
    puppy: Puppy
) {
    Column(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ) {
        with(puppy) {
            PuppyDetail(name = stringResource(id = R.string.age), value = age.toString())
            PuppyDetail(name = stringResource(id = R.string.color), value = color)
            PuppyDetail(name = stringResource(id = R.string.weight), value = weight.roundTo(1), unit = "kg")
            PuppyDetail(name = stringResource(id = R.string.height), value = height.roundTo(1), unit = "m")
        }
    }
}

@Composable
private fun PuppyDetail(
    name: String,
    value: String,
    unit: String? = null
) {
    val formattedString = buildAnnotatedString {
        append(name)
        append(": ")
        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
        append(value)
        pop()

        unit?.let {
            append(" ")
            append(it)
        }
    }

    Text(
        text = formattedString,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Composable
private fun PuppyDescription(
    puppy: Puppy
) {
    Card(
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.about),
                style = MaterialTheme.typography.h6.copy(
                    color = MaterialTheme.colors.primary
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = puppy.description,
                style = MaterialTheme.typography.body2.copy(
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

private fun Number.roundTo(
    numFractionDigits: Int
) = "%.${numFractionDigits}f".format(this, Locale.getDefault())
