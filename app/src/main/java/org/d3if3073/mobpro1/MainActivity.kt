package org.d3if3073.mobpro1

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.d3if3073.mobpro1.navigation.SetupNavGraph
import org.d3if3073.mobpro1.ui.screen.MainScreen
import org.d3if3073.mobpro1.ui.theme.Mobpro1Theme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mobpro1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavGraph()
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String) {
//    MainScreen {modifier ->
//        Text(
//            text = "$name",
//            modifier = modifier
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun ScreenPreview() {
//    Mobpro1Theme {
//        MainScreen ()
//    }
//}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MainScreen(content: @Composable (Modifier) -> Unit) {
//    val context = LocalContext.current
//    Scaffold (
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(text = stringResource(id = R.string.app_name ))
//                },
//                colors = TopAppBarDefaults.mediumTopAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary,
//                )
//            )
//        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = {
//                    Toast.makeText(context, R.string.tambah_error, Toast.LENGTH_SHORT).show()
//                }
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.Add,
//                    contentDescription = stringResource(R.string.tambah_catatan),
//                    tint = MaterialTheme.colorScheme.primary
//                )
//            }
//        }
//    ) { padding ->
//        content(Modifier.padding(padding))
//    }
//}


