package com.hellohasan.hasanerrafkhata

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                ) {
                    DynamicModuleDownloadButton {
                        openDynamicModuleFeature()
                    }
                    LazyColumn {
                        item {
                            Text(
                                text = logState.value,
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun openDynamicModuleFeature() {
        if (dynamicModuleDownloadUtil.isModuleDownloaded(TRANSLATION_DYNAMIC_MODULE)) {
            logState.value += "${getCurrentTimestamp()}: Module is already downloaded.\n"
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
        logState.value += "${getCurrentTimestamp()}: Call for download.\n"
        dynamicModuleDownloadUtil.downloadDynamicModule(TRANSLATION_DYNAMIC_MODULE)
    }

    override fun onDownloading() {
        logState.value += "${getCurrentTimestamp()}: Downloading...\n"
    }

    override fun onDownloadCompleted() {
        logState.value += "${getCurrentTimestamp()}: Module download completed.\n"
    }

    override fun onInstallSuccess() {
        logState.value += "${getCurrentTimestamp()}: Module install Success!\n"
        startTranslationActivity()
    }

    override fun onFailed() {
        logState.value += "${getCurrentTimestamp()}: Module download or installation failed.\n"
    }

    private fun getCurrentTimestamp(): String {
        val calendar = Calendar.getInstance()
        return "${calendar.get(Calendar.HOUR).toString().padStart(2, '0')}:" +
                "${calendar.get(Calendar.MINUTE).toString().padStart(2, '0')}:" +
                calendar.get(Calendar.SECOND).toString().padStart(2, '0')
    }
}

@Composable
fun DynamicModuleDownloadButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        content = { Text("Download Dynamic Feature Module") },
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DynamicModuleDownloadButtonPreview() {
    HasanerRafkhataTheme {
        DynamicModuleDownloadButton {}
    }
}