package com.sample.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sample.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                ArtSpace()
            }
        }
    }
}

@Composable
fun ArtSpace() {
    val minValue = 1
    val maxValue = 3
    var currentIndex by remember { mutableStateOf(1) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (currentIndex) {
            1 -> ArtDisplay(
                painter = painterResource(id = R.drawable.paint_first),
                title = stringResource(R.string.art_title_first),
                desc = stringResource(R.string.art_desc_first),
                modifier = Modifier.weight(1F)
            )

            2 -> ArtDisplay(
                painter = painterResource(id = R.drawable.paint_second),
                title = stringResource(R.string.art_title_second),
                desc = stringResource(R.string.art_desc_second),
                modifier = Modifier.weight(1F)
            )

            3 -> ArtDisplay(
                painter = painterResource(id = R.drawable.paint_third),
                title = stringResource(R.string.art_title_third),
                desc = stringResource(R.string.art_desc_third),
                modifier = Modifier.weight(1F)
            )
        }
        DisplayController(
            previous = { if (currentIndex > minValue) currentIndex-- },
            next = { if (currentIndex < maxValue) currentIndex++ }
        )
    }
}

@Composable
fun ArtDisplay(
    modifier: Modifier = Modifier,
    painter: Painter,
    title: String,
    desc: String
) {
    ArtworkWall(
        painter = painter,
        modifier = modifier.fillMaxSize()
    )
    ArtworkDescriptor(
        title = title,
        desc = desc
    )
}

@Composable
fun ArtworkWall(modifier: Modifier = Modifier, painter: Painter) {
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_32)))
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_16))
                .border(
                    width = dimensionResource(id = R.dimen.width_32),
                    color = Color.White,
                    shape = RectangleShape
                )
                .shadow(elevation = dimensionResource(id = R.dimen.elevation_8))
        )
    }
}

@Composable
fun ArtworkDescriptor(title: String, desc: String) {
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_32)))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(id = R.dimen.padding_16)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_16))
        )

        Text(
            text = desc,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 14.sp,
            textAlign = TextAlign.Justify,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_16))
        )
    }
}

@Composable
fun DisplayController(
    previous: () -> Unit,
    next: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_16))
    ) {
        Button(
            onClick = previous,
            modifier = Modifier.weight(1F)
        ) {
            Text(text = stringResource(R.string.btn_previous))
        }
        Spacer(modifier = Modifier.weight(weight = 0.1F))
        Button(
            onClick = next,
            modifier = Modifier.weight(1F)
        ) {
            Text(text = stringResource(R.string.btn_next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceAppTheme {
        ArtSpace()
    }
}