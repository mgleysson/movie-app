package br.com.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import br.com.movieapp.core.presentation.ui.MainScreen
import br.com.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            MovieAppTheme {
                MainScreen(
                    navController = rememberNavController()
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    MovieAppTheme {
        MainScreen(
            navController = rememberNavController()
        )
    }
}