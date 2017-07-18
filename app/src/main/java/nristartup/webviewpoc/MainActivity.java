package nristartup.webviewpoc;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            WebView webView = (WebView)findViewById(R.id.webview);

            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            webView.setWebViewClient(new WebViewClient(){
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    final Uri uri = request.getUrl();
                    view.loadUrl(uri.toString());
                    return super.shouldOverrideUrlLoading(view, request);
                }
                @SuppressWarnings("deprecation")
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
    //                view.loadUrl(url.toString());

                    Uri uri = Uri.parse(url);
                    if (uri.toString().startsWith("event:")) {
                        startActivity(new Intent(MainActivity.this, CalledActivity.class));
                    }
                    return false;
                }
            });

            webView.loadUrl("http://www.news196.com");
    }
}
