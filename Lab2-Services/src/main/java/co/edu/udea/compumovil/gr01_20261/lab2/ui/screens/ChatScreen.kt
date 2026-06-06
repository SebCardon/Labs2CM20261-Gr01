package co.edu.udea.compumovil.gr01_20261.lab2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.edu.udea.compumovil.gr01_20261.lab2.R
import co.edu.udea.compumovil.gr01_20261.lab2.data.model.ChatMessage
import co.edu.udea.compumovil.gr01_20261.lab2.ui.viewmodel.ChatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    conversationId: String,
    viewModel: ChatViewModel = viewModel(),
    onBackClick: () -> Unit
) {
    val allMessages by viewModel.messages.collectAsState()
    val chatMessages = allMessages.take(10)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.chat)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .semantics { contentDescription = "Lista de mensajes del chat" },
                reverseLayout = true,
                contentPadding = PaddingValues(16.dp)
            ) {
                items(chatMessages.reversed()) { message ->
                    MessageBubble(message = message)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Text(
                text = stringResource(R.string.sync_info),
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun MessageBubble(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message.avatarInitial,
                color = Color.White,
                style = MaterialTheme.typography.labelMedium
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = message.user ?: "",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold
            )
            Surface(
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 12.dp,
                    bottomEnd = 12.dp,
                    bottomStart = 12.dp
                ),
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier
                    .widthIn(max = 280.dp)
                    .semantics { contentDescription = "Mensaje de ${message.user}: ${message.message}" }
            ) {
                Text(
                    text = message.message ?: "",
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(
                text = message.timestamp ?: "",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
        }
    }
}
