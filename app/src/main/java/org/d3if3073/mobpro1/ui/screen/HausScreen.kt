package org.d3if3073.mobpro1.ui.screen

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3073.mobpro1.R
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
    ) { padding ->
        HausScreen(Modifier.padding(padding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HausScreen(modifier: Modifier) {
    var name by remember { mutableStateOf("") }
    var cream by remember { mutableStateOf(false) }
    var chocolate by remember { mutableStateOf(false) }
    var quantity by remember { mutableStateOf(0) }
    val price = calculatePrice(cream, chocolate, quantity)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
            TopAppBar(
                title = { Text("Pesan Kopi") }
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            BasicTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                decorationBox = { innerTextField ->
                    if (name.isEmpty()) {
                        Text("Masukan nama anda", fontSize = 16.sp)
                    }
                    innerTextField()
                }
            )
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = cream, onCheckedChange = { cream = it })
            Text("Krim")
            Checkbox(checked = chocolate, onCheckedChange = { chocolate = it })
            Text("Coklat")
        }
        QuantitySelector(
            quantity,
            onQuantityChange = { quantity = it },
            creamChecked = cream, chocolateChecked = chocolate
        )
        Text(
            "HARGA $price",
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = { /* Handle order placement */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("PESAN SEKARANG")
        }
    }
}



@Composable
fun QuantitySelector(quantity: Int, onQuantityChange: (Int) -> Unit, creamChecked: Boolean, chocolateChecked: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Button(onClick = { if ((quantity > 0) && (creamChecked || chocolateChecked)) onQuantityChange(quantity - 1) }, enabled = (creamChecked || chocolateChecked)) {
            Text(" - ")
        }
        Text("$quantity", fontSize = 18.sp, modifier = Modifier.widthIn(min = 40.dp))
        Button(onClick = { if (creamChecked || chocolateChecked) onQuantityChange(quantity + 1) }, enabled = (creamChecked || chocolateChecked)) {
            Text(" + ")
        }
    }
}


fun calculatePrice(cream: Boolean, chocolate: Boolean, quantity: Int): Int {
    val creamPrice = 1000
    val chocolatePrice = 1500

    val additionalPrice = if (cream) creamPrice else if (chocolate) chocolatePrice else 0

    return (quantity * additionalPrice)
}

