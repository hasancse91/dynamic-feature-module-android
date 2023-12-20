package com.hellohasan.hasanerrafkhata

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DynamicModuleDownloadButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.baseline_support_agent_24),
                "Customer Support",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Customer Support", style = TextStyle(color = Color.White, fontSize = 16.sp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDynamicModuleDownloadButton() {
    DynamicModuleDownloadButton {  }
}