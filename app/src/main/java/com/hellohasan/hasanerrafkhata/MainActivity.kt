package com.hellohasan.hasanerrafkhata

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hellohasan.hasanerrafkhata.ui.DownloadModuleConfirmationDialog
import com.hellohasan.hasanerrafkhata.ui.theme.HasanerRafkhataTheme
import java.util.Calendar

class MainActivity : ComponentActivity(), DynamicModuleListener {

    private val CUSTOMER_SUPPORT_DYNAMIC_MODULE = "customer_support"
    private lateinit var dynamicModuleDownloadUtil: DynamicModuleDownloadUtil
    private var logState = mutableStateOf("Activity Log:\n")
    private var dialogState = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dynamicModuleDownloadUtil = DynamicModuleDownloadUtil(baseContext, this)

        setContent {
            HasanerRafkhataTheme {

                Column(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                ) {
                    Image(
                        painterResource(R.drawable.ecommerce),
                        contentDescription = "eCommerce app banner",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                    Text(
                        "eCommerce App",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                    )
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
                    DownloadModuleConfirmationDialog(dialogState, ::downloadDynamicModule)
                }
            }
        }
    }


    private fun openDynamicModuleFeature() {
        if (dynamicModuleDownloadUtil.isModuleDownloaded(CUSTOMER_SUPPORT_DYNAMIC_MODULE)) {
            logState.value += "${getCurrentTimestamp()}: Module is already downloaded.\n"
            startTranslationActivity()
        } else {
            dialogState.value = true
        }
    }

    private fun startTranslationActivity() {
        val intent = Intent()
        intent.setClassName(
            "com.hellohasan.hasanerrafkhata",
            "com.hellohasan.customer_support.CustomerSupportActivity"
        )
        startActivity(intent)
    }

    private fun downloadDynamicModule() {
        logState.value += "${getCurrentTimestamp()}: Call for download.\n"
        dynamicModuleDownloadUtil.downloadDynamicModule(CUSTOMER_SUPPORT_DYNAMIC_MODULE)
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
    IconButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
            .clip(CircleShape)
            .background(colorScheme.primary),
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
fun DynamicModuleDownloadButtonPreview() {
    HasanerRafkhataTheme {
        DynamicModuleDownloadButton {}
    }
}