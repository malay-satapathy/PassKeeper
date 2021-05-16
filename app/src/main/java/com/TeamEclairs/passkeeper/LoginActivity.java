package com.TeamEclairs.passkeeper;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class LoginActivity extends Activity {

	private static String PINString = null;
	EditText pl;
	ImageButton lb;
	TextView fp;
	private boolean doubleBackToExitPressedOnce;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1589FF")));
		pl=(EditText)findViewById(R.id.loginText);
		lb=(ImageButton)findViewById(R.id.loginButton);
		fp=(TextView)findViewById(R.id.forgetPasswordTextButton);
		
		lb.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Context context = getApplicationContext();
            	SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            	int password=sharedPref.getInt("PASSWORD", 0);
            	
            	if(pl.getText().toString().compareTo("")!=0)
            	{
				if(Integer.parseInt(pl.getText().toString())==password)
				{
					Intent i =new Intent(getApplicationContext(),MainActivity.class);
					startActivity(i);
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Invalid PIN\n\nTry Again", Toast.LENGTH_SHORT).show();
				}
            	}
            	else
            	{
            		Toast.makeText(getApplicationContext(), "Enter your PIN to Sign In", Toast.LENGTH_SHORT).show();
            	}
			}
		});
		
		fp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Context context = getApplicationContext();
            	final SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            	final String secureText=sharedPref.getString("SECURE_TEXT",null);
            	 final int randomPIN = (int)(Math.random()*9000)+1000;
                 PINString = String.valueOf(randomPIN);
            	 final AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
	 	    	 
	             // Setting Dialog Title
	             alertDialog.setTitle("Verify your identity ?");

	             // Setting Dialog Message
	             alertDialog.setMessage("Enter your Security Text below");
	             final EditText input = new EditText(LoginActivity.this);  
	             LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
	                                   LinearLayout.LayoutParams.MATCH_PARENT,
	                                   LinearLayout.LayoutParams.MATCH_PARENT);
	             input.setLayoutParams(lp);
	             alertDialog.setView(input);

	             // Setting Icon to Dialog
	             alertDialog.setIcon(android.R.drawable.ic_menu_add);

	             
	             alertDialog.setPositiveButton("Check", new DialogInterface.OnClickListener() {
	                 public void onClick(DialogInterface dialog, int which) {
	                	 
	                	 if(input.getText().toString().compareTo(secureText)==0)
	                	 {
	                		 Toast.makeText(getApplicationContext(), "Your Pin is "+PINString+"\n\n\nCaution: Do not share it with anyone, and change it after login",Toast.LENGTH_LONG).show();
	                		 Context context = getApplicationContext();
	                     	 SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
	                     	Editor editor = sharedPref.edit();
	                     	editor.putInt("PASSWORD",randomPIN);
	                     	editor.commit();
	                	 }
	                	 else
	                	 {
	                		 Toast.makeText(getApplicationContext(), "Security Check failed !\nPlease try again.", Toast.LENGTH_SHORT).show();
	                	 }
	                 
	                 }

					
	             });

	             // Showing Alert Message
	             alertDialog.show();
	             
	            
            	 
			}
		});
		
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		pl.setText(null);
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
