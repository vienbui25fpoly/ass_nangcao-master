package com.cao.nang.myapplication;


import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

public class News extends AppCompatActivity {
    String url = "http://www.soha.vn//";
    ProgressBar progressBar;
    WebView webView;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new HelpClient());

        webView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view , int progress){
                frameLayout.setVisibility(View.VISIBLE);
                progressBar.setProgress(progress);
                setTitle("Loading.....");
                if (progress == 100){
                    frameLayout.setVisibility(View.GONE);
                    setTitle(view.getTitle());
                }
                super.onProgressChanged(view ,progress);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(false);
        webView.loadUrl(url);
        progressBar.setProgress(0);
    }

    private class HelpClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            frameLayout.setVisibility(View.VISIBLE);
            return true ;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (webView.canGoBack()){
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
