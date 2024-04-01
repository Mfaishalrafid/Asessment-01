package org.d3if3073.mobpro1.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3073.mobpro1.R
import org.d3if3073.mobpro1.navigation.Screen
import org.d3if3073.mobpro1.ui.theme.Mobpro1Theme

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun HausScreenPreview() {
    Mobpro1Theme {
        MainScreen(rememberNavController())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HausScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.haus))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
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
    ) { padding ->
        Text(
            text = stringResource(R.string.copyright),
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        )
    }
}

@Composable
fun HausApp() {
    var name by remember { mutableStateOf("") }
    var toppingCream by remember { mutableStateOf(false) }
    var toppingChocolate by remember { mutableStateOf(false) }
    var quantity by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BasicTextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Masukkan nama anda...") }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Tambahkan Topping:")
            Checkbox(
                checked = toppingCream,
                onCheckedChange = { toppingCream = it },
                colors = CheckboxDefaults.colors(Color.Black)
            )
            Text("Krim")
            Checkbox(
                checked = toppingChocolate,
                onCheckedChange = { toppingChocolate = it },
                colors = CheckboxDefaults.colors(Color.Black)
            )
            Text("Coklat")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("JUMLAH:")
            IconButton(
                onClick = { quantity-- },
                enabled = quantity > 1
            ) {
                Icon(imageVector = Icons.Default.Remove, contentDescription = null)
            }
            Text("$quantity")
            IconButton(
                onClick = { quantity++ }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }

        Text("HARGA: ${quantity * 10} USD")

        Button(
            onClick = { /* Pesan kopi */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text("PESAN SEKARANG", color = Color.White)
        }
    }
}

@Composable
fun MainScreen() {
    Surface {
        HausApp()
    }
}