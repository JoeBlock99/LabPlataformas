package com.example.proyectmanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class ProyectoAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proyecto)
        val url = (this.application as MyApplication).getProyectos().getPlink()
        val webView = findViewById<WebView>(R.id.webRepositorios)
        webView.webViewClient = MyBrowser()
        webView.loadUrl(url)
    }
    private inner class MyBrowser : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }
}
