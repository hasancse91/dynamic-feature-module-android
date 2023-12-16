package com.hellohasan.hasanerrafkhata

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hellohasan.hasanerrafkhata.R

@Composable
fun DownloadModuleConfirmationDialog(
    dialogState: MutableState<Boolean>,
    onClickConfirm: () -> Unit
) {
    if (!dialogState.value) return

    AlertDialog(
        icon = {
            Image(
                painter = painterResource(R.drawable.play_store),
                contentDescription = "Google play store",
                modifier = Modifier.height(70.dp),
            )
        },
        title = { Text("Download Customer Support Module") },
        text = { Text("Your requested customer support module need to be downloaded from Google Play Store. Download size is about 18 MB.") },
        onDismissRequest = { dialogState.value = false },
        confirmButton = {
            TextButton(
                onClick = {
                    onClickConfirm.invoke()
                    dialogState.value = false
                }
            ) {
                Text("Download")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { dialogState.value = false }
            ) {
                Text("Cancel")
            }
        }
    )

}