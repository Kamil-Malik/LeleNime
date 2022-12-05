package com.lelestacia.lelenime.feature_anime.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lelestacia.lelenime.R

@Composable
fun DetailAnimeHeader(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    japaneseTitle: String,
    score: Double,
    scoredBy: Int,
    rank: Int,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Detail Anime Image",
            modifier = modifier
                .width(125.dp)
                .clip(RoundedCornerShape(15.dp))
        )
        Column(
            modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title, fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
            Text(
                text = japaneseTitle,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.DarkGray
            )
            Row(
                modifier = modifier
                    .fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = modifier
                        .padding(top = 8.dp)
                        .background(Color.DarkGray)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = score.toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = stringResource(
                            id = R.string.tv_reviewed_by, scoredBy.toString()
                        )
                    )
                }
                Text(
                    text = "#$rank",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_3)
fun Preview() {
    DetailAnimeHeader(
        imageUrl = "https://cdn.myanimelist.net/images/anime/1806/126216l.jpg",
        title = "Chainsaw-Man",
        japaneseTitle = "チェンソーマン",
        score = 8.83,
        scoredBy = 175387,
        rank = 26
    )
}