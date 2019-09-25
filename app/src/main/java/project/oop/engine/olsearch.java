package project.oop.engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class olsearch extends AppCompatActivity {
WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olsearch);

        mWebView = findViewById(R.id.wv);
        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mWebView.getSettings();
        //webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl("https://codersera.tech/hoik");
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(olsearch.this,NavAct.class));
        finish();
    }
}
