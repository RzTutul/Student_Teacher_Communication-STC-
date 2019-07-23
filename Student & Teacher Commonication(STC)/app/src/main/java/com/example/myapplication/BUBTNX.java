package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class BUBTNX extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubtnx);
        setTitle("BUBT ANX");

        webView = findViewById(R.id.browser);
        WebSettings websetting = webView.getSettings();

        websetting.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("https://www.annex.bubt.edu.bd/");


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String text = bundle.getString("key");

            if (text.equals("teacherlist")) {
                setTitle("Teacher List");
                webView.loadUrl("https://www.bubt.edu.bd/home/faculty_member/33/");
            }
           if (text.equals("cgpa")) {
               setTitle("CPGA Calcultion");
               Toast.makeText(BUBTNX.this,text,Toast.LENGTH_SHORT).show();
                webView.loadUrl("http://az-res.blogspot.com/2016/11/cgpa-calculator-online.html");
            }

               if (text.equals("result")) {
               setTitle("Result");
               Toast.makeText(BUBTNX.this,text,Toast.LENGTH_SHORT).show();
                webView.loadUrl("https://www.bubt.edu.bd/home/result");
            }

           if (text.equals("bubtwebsite")) {
               setTitle("BUBT WebPage");
               Toast.makeText(BUBTNX.this,text,Toast.LENGTH_SHORT).show();
                webView.loadUrl("https://www.bubt.edu.bd/");
            }


        }

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }

    }
}
