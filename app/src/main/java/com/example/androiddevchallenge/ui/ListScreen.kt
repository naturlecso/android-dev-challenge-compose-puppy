package com.example.androiddevchallenge.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
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
