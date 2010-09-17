package com.bowdoin;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class Dining extends Activity {
    /** Called when the activity is first created. */
	WebView mWebView;
	@Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dining);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
 
        mMeal = "Breakfast";
        mHall = "48";
        
        mBreakfast = (Button) findViewById(R.id.breakfast);
        mBreakfast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	mMeal = "Breakfast";
            	updateView();
            }
        });
        
        mLunch = (Button) findViewById(R.id.lunch);
        mLunch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	mMeal = "Lunch";
            	updateView();
            }
        });
        
        mDinner = (Button) findViewById(R.id.dinner);
        mDinner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	mMeal = "Dinner";
            	updateView();
            }
        });
        
        mBrunch = (Button) findViewById(R.id.brunch);
        mBrunch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	mMeal = "Brunch";
            	updateView();
            }
        });
        
        mMoulton = (Button) findViewById(R.id.moulton);
        mMoulton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	mHall = "48";
            	updateView();
            }
        });
        
        mThorn = (Button) findViewById(R.id.thorn);
        mThorn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	mHall = "49";
            	updateView();
            	
            }
        });

        updateView();
        
    }
	
	private void clearColor() {
		mBreakfast.setTextColor(Color.WHITE);
		mLunch.setTextColor(Color.WHITE);
		mDinner.setTextColor(Color.WHITE);
		mBrunch.setTextColor(Color.WHITE);
		mThorn.setTextColor(Color.WHITE);
		mMoulton.setTextColor(Color.WHITE);
		mBreakfast.setBackgroundResource(R.drawable.black_white_gradient);
		mLunch.setBackgroundResource(R.drawable.black_white_gradient);
		mDinner.setBackgroundResource(R.drawable.black_white_gradient);
		mBrunch.setBackgroundResource(R.drawable.black_white_gradient);
		mMoulton.setBackgroundResource(R.drawable.black_white_gradient);
		mThorn.setBackgroundResource(R.drawable.black_white_gradient);
	}
	
	private void updateView() {
		clearColor();
        setColor();
        loadUrl();
	}

	private void setColor() {
		if ( mMeal == "Breakfast") {
			mBreakfast.setBackgroundResource(R.drawable.white_black_gradient);
		}
		if ( mMeal == "Lunch") {
			mLunch.setBackgroundResource(R.drawable.white_black_gradient);
		}
		if ( mMeal == "Dinner") {
			mDinner.setBackgroundResource(R.drawable.white_black_gradient);
		}
		if ( mMeal == "Brunch") {
			mBrunch.setBackgroundResource(R.drawable.white_black_gradient);
		}
		if ( mHall == "48") {
			mMoulton.setBackgroundResource(R.drawable.white_black_gradient);
		}
		if ( mHall == "49") {
			mThorn.setBackgroundResource(R.drawable.white_black_gradient);
		}
	}
	private void loadUrl() {
		String Url = "http://www.bowdoin.edu/atreus/views?unit=" .concat(mHall).concat("&meal=").concat(mMeal);
		mWebView.clearView();
		mWebView.loadData("Loading...", "text/html", "");
		mWebView.refreshDrawableState();
		mWebView.loadUrl(Url);
	}
	
	protected void onSaveInstanceState(Bundle outState){
		outState.putString("mMeal", mMeal);
		outState.putString("mHall", mHall);
	}
	
	protected void onRestoreInstanceState(Bundle inState){
		mMeal = inState.getString("mMeal");
		mHall = inState.getString("mHall");
		updateView();
	}
	
	private Button mBreakfast;
	private Button mLunch;
	private Button mDinner;
	private Button mBrunch;
	private Button mMoulton;
	private Button mThorn;
	private String mMeal;
	private String mHall;
}