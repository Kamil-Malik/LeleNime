package com.lelestacia.lelenime.feature_anime.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lelestacia.lelenime.R
import com.lelestacia.lelenime.core.ui.theme.Navy

@Composable
fun AnimeCard(
    id: Int,
    title: String,
    episode: Int,
    score: Double,
    image: String,
    status: String,
    onClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 5.dp,
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(modifier = modifier
            .clickable { onClicked(id) }
            .background(
                if (isSystemInDarkTheme()) {
                    Navy
                } else {
                    Color.White
                }
            )) {
            AsyncImage(
                model = image,
                contentDescription = "Cover Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
            )
            Column(modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.padding(top = 4.dp)
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.rating,
                            if (score.toInt() == 0) {
                                "Unknown"
                            } else {
                                score.toString()
                            }
                        ),
                        fontWeight = FontWeight.Medium,
                    )
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Icon Rating",
                        modifier = modifier
                            .padding(horizontal = 4.dp)
                            .size(16.dp),
                        tint = Color.White
                    )
                }
                Text(
                    text = stringResource(
                        id = R.string.episode, if (episode == 0) {
                            "Unknown"
                        } else {
                            episode.toString()
                        }
                    ),
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = stringResource(id = R.string.status, status),
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}