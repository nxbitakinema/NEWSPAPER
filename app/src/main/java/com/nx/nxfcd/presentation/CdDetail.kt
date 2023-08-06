package com.nx.nxfcd.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nx.nxfcd.domain.model.Cd
import com.nx.nxfcd.core.Constants.EMPTY_STRING

@Composable
fun CdDetail(
    cd: Cd
) {

    var showDescription by remember { mutableStateOf(false) }

    val nxSize by animateDpAsState(
        targetValue = if (showDescription) 40.dp else 80.dp, label = EMPTY_STRING )

    Column(
        modifier = Modifier.clickable { showDescription = showDescription.not() }
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            AsyncImage(
                model = cd.image,
                contentDescription = null,
                modifier = Modifier.size(nxSize)
            )
            Spacer(
                modifier = Modifier.width(8.dp)
            )
            Column {
                cd.name?.let {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                cd.author?.let {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Light,
                        fontSize = 12.sp
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = showDescription
        ) {
            cd.description?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
                )
            }
        }
    }
}