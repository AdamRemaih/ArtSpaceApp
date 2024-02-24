package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.data.DataResource
import com.example.artspaceapp.data.Model.ArtSpace
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}
@Composable
fun ArtSpaceApp() {
    val artspaceList = DataResource().loadArtSpace()
    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        if (artspaceList.isNotEmpty()) {
            Column (
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ArtSpacePage(artspace = artspaceList[currentIndex])

            }

            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ){

                Button(onClick = {
                    currentIndex = if (currentIndex > 0)
                    {
                        currentIndex - 1
                    }
                    else artspaceList.size - 1
                },
                    modifier = Modifier.widthIn(min = 64.dp, max = 120.dp)
                ) {
                    Text("Previous", modifier = Modifier.padding(8.dp, 0.dp, 8.dp,0.dp))
                }

                Spacer(Modifier.width(60.dp))

                Button(onClick = {
                    currentIndex = if (currentIndex < artspaceList.size - 1)
                    {
                        currentIndex + 1
                    }
                    else 0
                },
                    modifier = Modifier.widthIn(min = 64.dp, max = 120.dp)
                ) {
                    Text("Next", modifier = Modifier.padding(20.dp, 0.dp, 20.dp,0.dp))
                }
            }
        } else {
            Text("No art spaces available")
        }
    }
}

@Composable
fun ArtSpacePage(artspace: ArtSpace) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            shadowElevation = 20.dp,
            color = MaterialTheme.colorScheme.surface

        ) {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .padding(30.dp)

            ) {
                Image(
                    painter = painterResource(id = artspace.imageResourceId),
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 70.dp, end = 16.dp)
                .background(Color(0xFFE0E0E0))
        ) {
            Column(
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 10.dp)
            ){
                Text(
                    text = stringResource(id = artspace.stringResourceId),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Light,
                )

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = stringResource(id = artspace.descriptionResourceId),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,

                )
            }



        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePagePreview() {
    ArtSpaceAppTheme {
        val previewArtSpace = ArtSpace(
            stringResourceId = R.string.artspace1,
            descriptionResourceId = R.string.artspace1Description,
            imageResourceId = R.drawable.burjkhalifa
        )
        ArtSpacePage(artspace = previewArtSpace)
    }
}