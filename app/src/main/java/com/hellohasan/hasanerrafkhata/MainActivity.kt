package com.hellohasan.hasanerrafkhata

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hellohasan.hasanerrafkhata.ui.theme.HasanerRafkhataTheme
import java.util.Calendar

class MainActivity : ComponentActivity(), DynamicModuleListener {

    private val TRANSLATION_DYNAMIC_MODULE = "translationdynamicfeature"
    private lateinit var dynamicModuleDownloadUtil: DynamicModuleDownloadUtil
    private var logState = mutableStateOf("Activity Log:\n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dynamicModuleDownloadUtil = DynamicModuleDownloadUtil(baseContext, this)

        setContent {
            HasanerRafkhataTheme {

                Column(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DynamicModuleDownloadButton {
                        openDynamicModuleFeature()
                    }
                    Text(text = logState.value)
                }
            }
        }
    }

    private fun openDynamicModuleFeature() {
        logState.value += "Download button clicked\n"
        if (dynamicModuleDownloadUtil.isModuleDownloaded(TRANSLATION_DYNAMIC_MODULE)) {
            logState.value += "Module is already downloaded.\n"
            startTranslationActivity()
        } else {
            downloadDynamicModule()
        }
    }

    private fun startTranslationActivity() {
        val intent = Intent()
        intent.setClassName(
            "com.hellohasan.hasanerrafkhata",
            "com.hellohasan.translationdynamicfeature.TranslationActivity"
        )
        startActivity(intent)
    }

    private fun downloadDynamicModule() {
        logState.value += "Call for download dynamic module. ${getCurrentTimestamp()}\n"
        dynamicModuleDownloadUtil.downloadDynamicModule(TRANSLATION_DYNAMIC_MODULE)
    }

    override fun onDownloading() {
        logState.value += "Downloading... ${getCurrentTimestamp()}\n"
    }

    override fun onDownloadCompleted() {
        logState.value += "Module download completed. ${getCurrentTimestamp()}\n"
    }

    override fun onInstallSuccess() {
        logState.value += "Module install Success! ${getCurrentTimestamp()}\n"
        startTranslationActivity()
    }

    override fun onFailed() {
        logState.value += "Module download or installation failed. ${getCurrentTimestamp()}\n"
    }

    private fun getCurrentTimestamp(): String {
        val calendar = Calendar.getInstance()
        return "Time: ${calendar.get(Calendar.HOUR)}:${calendar.get(Calendar.MINUTE)}:${
            calendar.get(
                Calendar.SECOND
            )
        }"
    }
}

@Composable
fun DynamicModuleDownloadButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        content = { Text("Download Dynamic Feature Module") },
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
fun DynamicModuleDownloadButtonPreview() {
    HasanerRafkhataTheme {
        DynamicModuleDownloadButton {}
    }
}