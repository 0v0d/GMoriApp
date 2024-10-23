package com.example.gmoriapp.view.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gmoriapp.model.User
import com.example.gmoriapp.view.component.ChevronIcon
import com.example.gmoriapp.view.component.UserImage
import com.example.gmoriapp.view.component.UserName


@Composable
fun UserListContent(
    users: List<User>,
    navigateToUserDetail: (User) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(users) { user ->
            UserListItem(
                user = user,
                onClick = { navigateToUserDetail(user) }
            )
        }
    }
}


@Composable
fun UserListItem(
    user: User,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    UserListScaffold(
        modifier = modifier.clickable(onClick = onClick)
    ) {
        UserInfo(user = user, modifier = Modifier.weight(1f))
        ChevronIcon()
    }
}

@Composable
fun UserListScaffold(
    modifier: Modifier = Modifier,
    content: @Composable (RowScope.() -> Unit)
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

@Composable
private fun UserInfo(
    user: User,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row {
            UserImage(imageUrl = user.imageUrl)
            Spacer(modifier = Modifier.width(15.dp))
            UserName(name = user.name)
        }
    }
}

