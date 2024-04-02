package org.d3if3073.mobpro1.ui.screen

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3073.mobpro1.R
import org.d3if3073.mobpro1.ui.theme.Mobpro1Theme
import org.w3c.dom.Text


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
    var nameError by remember { mutableStateOf(false) }

    var kopi by remember { mutableStateOf(false) }
    var jus by remember { mutableStateOf(false) }
    var coklat by remember { mutableStateOf(false) }
    var susu by remember { mutableStateOf(false) }
    var milo by remember { mutableStateOf(false) }
    var josu by remember { mutableStateOf(false) }
    var bengbeng by remember { mutableStateOf(false) }

    var quantity by remember { mutableStateOf(0) }
    val price = calculatePrice(kopi, jus,coklat, susu, milo, josu, bengbeng, quantity )
    var outputtext by remember { mutableStateOf("") }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = { Text("Pesan Kopi") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        BasicTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            decorationBox = { innerTextField ->
                if (name.isEmpty()) {
                    Text("Masukkan nama anda", fontSize = 16.sp)
                }
                innerTextField()
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
            {
                Checkbox(checked = kopi, onCheckedChange = { kopi = it })
                Text("Kopi")
                Checkbox(checked = jus, onCheckedChange = { jus = it })
                Text("Jus")
                Checkbox(checked = coklat, onCheckedChange = { jus = it })
                Text("Coklat")
                Checkbox(checked = susu, onCheckedChange = { jus = it })
                Text("Susu")
                Checkbox(checked = milo, onCheckedChange = { jus = it })
                Text("Milo")
                Checkbox(checked = josu, onCheckedChange = { jus = it })
                Text("Josu")
                Checkbox(checked = bengbeng, onCheckedChange = { jus = it })
                Text("Bengbeng")
            }
        QuantitySelector(
            quantity,
            onQuantityChange = { quantity = it },
            kopiChecked = kopi,
            jusChecked = jus,
            coklatChecked = coklat,
            susuChecked = susu,
            miloChecked = milo,
            josuChecked = josu,
            bengbengChecked = bengbeng,

        )

        Spacer(modifier = Modifier.height(16.dp))


            Text(
                "HARGA $price",
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp)
            )
        Button(
            onClick = {
                nameError = (name == "" || name == "0")
                if (nameError) {
                    outputtext = "Mohon masukkan nama anda dengan benar."
                } else {
                    val kopiText = if (kopi) " kopi" else ""
                    val jusText = if (jus) " jus" else ""
                    val coklatText = if (coklat) " jus" else ""
                    val susuText = if (susu) " jus" else ""
                    val miloText = if (milo) " jus" else ""
                    val josuText = if (josu) " jus" else ""
                    val bengbengText = if (bengbeng) " jus" else ""
                    outputtext = "Pesanan atas nama $name untuk $quantity $kopiText$jusText$coklatText$susuText$miloText$josuText$bengbengText dengan total harga $price telah berhasil!"
                }

            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("PESAN SEKARANG")
        }
        Text(
            text = outputtext,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}



    @Composable
    fun QuantitySelector(quantity: Int, onQuantityChange: (Int) -> Unit, kopiChecked: Boolean, jusChecked: Boolean, coklatChecked: Boolean, susuChecked: Boolean,miloChecked: Boolean,josuChecked: Boolean,bengbengChecked: Boolean,) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = { if ((quantity > 0) && (kopiChecked || jusChecked || coklatChecked || susuChecked || miloChecked || josuChecked || bengbengChecked)) onQuantityChange(quantity - 1) },
                enabled = (kopiChecked || jusChecked || coklatChecked || susuChecked || miloChecked || josuChecked || bengbengChecked),
                modifier = Modifier.padding(end = 30.dp)
            ) {
                Text(" - ")
            }
            Text("$quantity", fontSize = 18.sp, modifier = Modifier.widthIn(min = 40.dp))
            Button(
                onClick = { if (kopiChecked || jusChecked || coklatChecked || susuChecked || miloChecked || josuChecked || bengbengChecked) onQuantityChange(quantity + 1) },
                enabled = (kopiChecked || jusChecked || coklatChecked || susuChecked || miloChecked || josuChecked || bengbengChecked)
            ) {
                Text(" + ")
            }
        }
    }


fun calculatePrice(kopi: Boolean, jus: Boolean, coklat: Boolean, susu: Boolean, milo: Boolean, josu: Boolean, bengbeng: Boolean, quantity: Int): Int {
    val kopiPrice = 1000
    val jusPrice = 1500
    val coklatPrice = 1600
    val susuPrice = 1700
    val miloPrice = 1800
    val josuPrice = 1900
    val bengbengPrice = 2000

    val additionalPrice = when {
        kopi -> kopiPrice
        jus -> jusPrice
        coklat -> coklatPrice
        susu -> susuPrice
        milo -> miloPrice
        josu -> josuPrice
        bengbeng -> bengbengPrice
        else -> 0
    }

    return (quantity * additionalPrice)
}

