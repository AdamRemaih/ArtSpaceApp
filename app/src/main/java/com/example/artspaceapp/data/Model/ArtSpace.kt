package com.example.artspaceapp.data.Model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ArtSpace (
    @StringRes val stringResourceId: Int,
    @StringRes val descriptionResourceId: Int,
    @DrawableRes val imageResourceId: Int
)
