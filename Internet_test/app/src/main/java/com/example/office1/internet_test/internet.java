package com.example.office1.internet_test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class internet extends Activity {

    private EditText txtUrl;
    private Button goBtn;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);

        txtUrl = (EditText) findViewById(R.id.input_txt);
        goBtn = (Button) findViewById(R.id.go);
        webView =(WebView) findViewById(R.id.web);

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowser();
            }
        });

        txtUrl.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    openBrowser();
                    return true;
                }
                return false;
            }
        });
    }

    private void openBrowser(){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://"+txtUrl.getText().toString());
//        Uri uri = Uri.parse(txtUrl.getText().toString());
//        Intent i = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(i);
    }
}
