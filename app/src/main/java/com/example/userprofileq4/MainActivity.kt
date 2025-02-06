package com.example.userprofileq4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                ) { innerPadding ->
                    UserProfile(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(Color.Black),
                    snackbarHostState = snackbarHostState
                    )
                }
            }
        }
    }
}

@Composable
fun UserProfile(modifier: Modifier = Modifier, snackbarHostState: SnackbarHostState) {
    val isFollowing = remember { mutableStateOf(false) }
    val snackbarMessage = if (isFollowing.value) {
        "Followed 1D"
    }
    else {
        "Unfollowed 1D"
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.defaultphoto),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.clip(RoundedCornerShape(4.dp)).size(125.dp)
            )
        }

        Column(
            modifier = modifier
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "User: 1D",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "City's breaking down on a camel's back",
                color = Color.White
            )
            Text(text = "")

            Button(
                onClick = {
                    isFollowing.value = !isFollowing.value
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1ED760))// Green color
            ) {
                Text(
                    text = if (isFollowing.value) "Following" else "Follow",
                    color = Color.White
                )
            }
            LaunchedEffect(isFollowing.value) {
                snackbarHostState.showSnackbar(snackbarMessage)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfilePreview() {
    MaterialTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        UserProfile(snackbarHostState = snackbarHostState)
    }
}
