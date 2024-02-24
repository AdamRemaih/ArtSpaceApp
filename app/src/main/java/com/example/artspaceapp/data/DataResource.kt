package com.example.artspaceapp.data

import com.example.artspaceapp.R
import com.example.artspaceapp.data.Model.ArtSpace

class DataResource() {
        fun loadArtSpace(): List<ArtSpace> {
            return listOf<ArtSpace>(
                ArtSpace(R.string.artspace1, R.string.artspace1Description, R.drawable.burjkhalifa),
                ArtSpace(R.string.artspace2, R.string.artspace2Description, R.drawable.campnou),
                ArtSpace(R.string.artspace3, R.string.artspace3Description, R.drawable.baalbek),
            )
        }
}
