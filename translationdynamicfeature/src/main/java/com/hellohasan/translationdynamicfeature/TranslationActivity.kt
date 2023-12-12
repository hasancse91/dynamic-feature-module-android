package com.hellohasan.translationdynamicfeature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hellohasan.translationdynamicfeature.ui.theme.HasanerRafkhataTheme

class TranslationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HasanerRafkhataTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TranslationWidget()
                }
            }
        }
    }
}

@Composable
fun TranslationWidget() {
    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(R.drawable.a),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(70.dp),
            contentScale = ContentScale.Crop
        )
        Image(
            painterResource(R.drawable.b),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(70.dp),
            contentScale = ContentScale.Crop
        )
        Image(
            painterResource(R.drawable.c),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(70.dp),
            contentScale = ContentScale.Crop
        )
        Image(
            painterResource(R.drawable.d),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(70.dp),
            contentScale = ContentScale.Crop
        )
        Image(
            painterResource(R.drawable.e),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(70.dp),
            contentScale = ContentScale.Crop
        )
        Image(
            painterResource(R.drawable.f),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(70.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "This is Translation Dynamic Feature Module.")
        Text(text = "6 images size about: 18 MB", fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HasanerRafkhataTheme {
        TranslationWidget()
    }
}