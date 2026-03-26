package com.example.meuprimeiroprojeto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeNavigationAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "screen_a") {

        composable("screen_a") {
            ScreenA(navController)
        }

        composable("screen_b?message={message}") { backStackEntry ->
            val message = backStackEntry.arguments?.getString("message")
            ScreenB(navController, message)
        }
    }
}

@Composable
fun ScreenA(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) //
            .padding(16.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Tela A",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate("screen_b?message=Olá da Tela A!")
            },
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Ir com mensagem")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                navController.navigate("screen_b")
            },
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Ir sem mensagem")
        }
    }
}

@Composable
fun ScreenB(navController: NavController, message: String?) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) //
            .padding(16.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Tela B",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (message != null) {
            Text("Mensagem: $message")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { navController.popBackStack() },
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Voltar")
        }
    }
}

data class Message(
    val author: String,
    val body: String,
    val image: Int
)

@Composable
fun MessageCard(msg: Message) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Row(modifier = Modifier.padding(12.dp)) {

            Image(
                painter = painterResource(id = msg.image),
                contentDescription = "Foto",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {

                Text(
                    text = msg.author,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = msg.body,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun ComposeNavigationAppTheme(content: @Composable () -> Unit) {

    val colors = lightColorScheme(
        primary = Color(0xFF6750A4),
        secondary = Color(0xFF03DAC5),
        background = Color(0xFFE3F2FD), //
        surface = Color.White,
        onPrimary = Color.White
    )

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}