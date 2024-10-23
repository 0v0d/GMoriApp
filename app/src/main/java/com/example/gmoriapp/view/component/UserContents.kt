package com.example.gmoriapp.view.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gmoriapp.R

@Composable
fun UserImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    size : Int = 80,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .error(R.drawable.noimage)
            .build(),
        contentDescription = null,
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
    )
}

@Composable
fun UserName(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = name,
        style = MaterialTheme.typography.titleLarge,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(top = 20.dp)
    )
}

@Composable
fun ChevronIcon(
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = Icons.Default.ChevronRight,
        contentDescription = "View Details",
        tint = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier
    )
}