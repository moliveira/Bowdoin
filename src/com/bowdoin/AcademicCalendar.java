package com.bowdoin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class AcademicCalendar extends Activity {
	WebView mWebView;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academic_calendar);
        
        mWebView = (WebView) findViewById(R.id.academic_webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        
        mYear = "2010";
        
        mYear2010 = (Button) findViewById(R.id.year_2010);
        mYear2010.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	mYear = "2010";
            	clearColor();
            	setColor();
            	loadUrl();
            }
        });
        
        mYear2011 = (Button) findViewById(R.id.year_2011);
        mYear2011.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	mYear = "2011";
            	clearColor();
            	setColor();
            	loadUrl();
            }
        });
        
        clearColor();
        setColor();
        loadUrl();
    }
    
    private void loadUrl() {
        AssetManager assetManager = getAssets();
        
        InputStream inputStream = null;
        try {
        	if ( mYear == "2010" ) {
        	    inputStream = assetManager.open("academic_year_2010");
        	} else if (mYear == "2011") {
        		inputStream = assetManager.open("academic_year_2011");
        	}
        } catch (IOException e) {
        	Log.e("tag", e.getMessage());
        }
        String s = readTextFile(inputStream);
        mWebView.clearView();
		mWebView.loadData("Loading...", "text/html", "");
		mWebView.refreshDrawableState();
        mWebView.loadData(s, "text/html", "utf-8");
    }
    
	private String readTextFile(InputStream inputStream) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte buf[] = new byte[1024];
		int len;
		try {
			while ((len = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, len);
			}
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {
		}
		return outputStream.toString();
	}
	
	private void clearColor() {
		mYear2010.setTextColor(Color.WHITE);
		mYear2011.setTextColor(Color.WHITE);
		mYear2010.setBackgroundResource(R.drawable.black_white_gradient);
		mYear2011.setBackgroundResource(R.drawable.black_white_gradient);
	}

	private void setColor() {
		if ( mYear == "2010") {
			mYear2010.setBackgroundResource(R.drawable.white_black_gradient);
		}
		if ( mYear == "2011") {
			mYear2011.setBackgroundResource(R.drawable.white_black_gradient);
		}
	}

	protected void onSaveInstanceState(Bundle outState){
		outState.putString("mYear", mYear);
	}
	
	protected void onRestoreInstanceState(Bundle inState){
		mYear = inState.getString("mYear");
		clearColor();
		setColor();
		loadUrl();
	}
	
	private String mYear;
	private Button mYear2010;
	private Button mYear2011;
}