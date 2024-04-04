package org.d3if3073.mobpro1.model

import androidx.annotation.DrawableRes

data class Catatan(
    val nama: String,
    @DrawableRes val imageResId: Int,
    val judul: String,
    val catatan: String,
    val tanggal: String
)
