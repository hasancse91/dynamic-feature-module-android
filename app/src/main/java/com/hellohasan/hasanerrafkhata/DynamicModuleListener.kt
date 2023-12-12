package com.hellohasan.hasanerrafkhata

interface DynamicModuleListener {
    fun onDownloading()
    fun onDownloadCompleted()
    fun onInstallSuccess()
    fun onFailed()
}