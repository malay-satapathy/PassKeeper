package com.TeamEclairs.passkeeper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;

public class SplashActivity extends Activity {

	 private final int SPLASH_DISPLAY_LENGTH = 4000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		final Typewriter writer = new Typewriter(this);
        setContentView(writer);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Vanilla.ttf"); 
        //Add a character every 150ms
        writer.setCharacterDelay(290);
        writer.setTypeface(type);
        writer.setTextColor(Color.BLACK);
        writer.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1589FF")));
        writer.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        writer.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        writer.animateText("PassKeeper™");
        
		
		 new Handler().postDelayed(new Runnable(){
	            @Override
	            public void run() {
	            	
	            	Context context = getApplicationContext();
	            	SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
	            	int accountExits=sharedPref.getInt("ACCOUNT_EXISTS", 0);
	                if(accountExits == 2222)
	                {
	                	Intent i=new Intent(SplashActivity.this,LoginActivity.class);
	                	i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
	                    finish();
	                    startActivity(i);
	               
	                }
	                else
	                {
	                	Intent i=new Intent(SplashActivity.this,StartActivity.class);
	                	i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
	                    finish();
	                    startActivity(i);
	             
	                }
	            }
	        }, SPLASH_DISPLAY_LENGTH);
	}
	
	@Override
	public void onBackPressed() {
	}

}
