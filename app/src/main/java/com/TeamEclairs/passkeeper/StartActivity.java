package com.TeamEclairs.passkeeper;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class StartActivity extends Activity {

	ImageButton ib;
	private boolean doubleBackToExitPressedOnce;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		
		ib=(ImageButton)findViewById(R.id.imageButton);
		
		//Typeface type = Typeface.createFromAsset(getAssets(),"fonts/3Dumb.ttf"); 
		//tv.setTypeface(type);
		
	        
		ib.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(StartActivity.this,FirstLaunchActivity.class);
				i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(i);
				
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		 if (doubleBackToExitPressedOnce) {
		        super.onBackPressed();
		        finish();
		    	android.os.Process.killProcess(android.os.Process.myPid());
		    	System.exit(1);
		        return;
		    }

		    this.doubleBackToExitPressedOnce = true;
		    Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

		    new Handler().postDelayed(new Runnable() {

		        @Override
		        public void run() {
		            doubleBackToExitPressedOnce=false;                       
		        }
		    }, 2000);
	}
	
}
