package com.example.gmoriapp.view.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gmoriapp.model.User
import com.example.gmoriapp.view.component.UserImage
import com.example.gmoriapp.view.component.UserName

@Composable
fun UserDetailView(
    user: User,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp) ,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        UserImage(
            imageUrl = user.imageUrl,
            size = 150
        )
        Spacer(modifier = Modifier.height(15.dp))
        UserName(name = user.name)

        if (user.description.isNotEmpty()) {
            Text(
                text = user.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp),
            )
        }
    }
}
