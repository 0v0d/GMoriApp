package com.example.gmoriapp.view.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gmoriapp.view.user.UserListScaffold

@Composable
fun UserListItemSkeleton(
    modifier: Modifier = Modifier
) {
    UserListScaffold(modifier = modifier) {
        Column(modifier = Modifier.weight(1f)) {
            Row {
                val shimmerBrush = ShimmerEffect.createShimmerBrush()
                SkeletonAvatar(shimmerBrush)
                Spacer(modifier = Modifier.width(15.dp))
                SkeletonName(shimmerBrush)
            }
        }
        ChevronIcon()
    }
}

@Composable
private fun SkeletonAvatar(
    brush: Brush,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(brush)
    )
}

@Composable
private fun SkeletonName(
    brush: Brush,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(70.dp)
            .height(30.dp)
            .padding(top = 20.dp)
            .background(brush)
    )
}

object ShimmerEffect {
    @Composable
    fun createShimmerBrush(): Brush {
        val transition = rememberInfiniteTransition(label = "")
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1200,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            ),
            label = "",
        )

        return remember(translateAnimation.value) {
            Brush.linearGradient(
                colors = listOf(
                    Color.LightGray.copy(alpha = 0.6f),
                    Color.LightGray.copy(alpha = 0.2f),
                    Color.LightGray.copy(alpha = 0.6f)
                ),
                start = Offset(translateAnimation.value - 1000f, 0f),
                end = Offset(translateAnimation.value, 0f)
            )
        }
    }
}
