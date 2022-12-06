package com.lelestacia.lelenime.feature_anime.ui.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lelestacia.lelenime.core.ui.theme.Navy
import com.lelestacia.lelenime.core.ui.theme.Orange

@Composable
fun CustomizedSearchField(
    value: String,
    onValueChanged: (String) -> Unit,
    onSearchIconClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val innerColor = when (isSystemInDarkTheme()) {
        true -> Color.White
        false -> Color.Black
    }
    val backGroundColor = when (isSystemInDarkTheme()) {
        true -> Navy
        false -> Orange
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = value,
            modifier = modifier
                .weight(1f)
                .clip(RoundedCornerShape(15.dp)),
            onValueChange = { newValue ->
                onValueChanged(newValue)
            },
            label = {
                Text(text = "Title of the Anime")
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = innerColor,
                unfocusedLabelColor = innerColor,
                focusedLabelColor = innerColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = backGroundColor
            ),
            textStyle = TextStyle.Default.copy(
                fontWeight = FontWeight.Medium
            )
        )
        OutlinedButton(
            onClick = onSearchIconClicked,
            modifier = modifier
                .height(IntrinsicSize.Max)
                .clip(RoundedCornerShape(15.dp)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backGroundColor
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Search, contentDescription = "Button Search",
                tint = when(isSystemInDarkTheme()) {
                    true -> Color.White
                    false -> Color.Black
                }
            )
        }
    }
}
