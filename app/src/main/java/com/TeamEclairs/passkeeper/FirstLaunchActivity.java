package com.TeamEclairs.passkeeper;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FirstLaunchActivity extends Activity {
	
	EditText username,password,email,secureText;
    Button clear,register;
    private boolean doubleBackToExitPressedOnce;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_launch);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1589FF")));
		
		username=(EditText)findViewById(R.id.usernameText);
		password=(EditText)findViewById(R.id.passwordText);
		email=(EditText)findViewById(R.id.emailText);
		secureText=(EditText)findViewById(R.id.secureText);
		clear=(Button)findViewById(R.id.clearButton);
		register=(Button)findViewById(R.id.registerButton);
		
		Context context = getApplicationContext();
		final SharedPreferences sharedPref = context.getSharedPreferences(
		        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
		
		clear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				username.setText("");
				password.setText("");
				secureText.setText("");
				email.setText("");
				
				
			}
		});
		
		register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
	
				if(username.getText().toString().compareTo("")==0)
				{
					Toast.makeText(getApplicationContext(),"Enter Username",1000).show();
				}
				else if(isFullName(username.getText().toString().trim())!=true)
				{
					Toast.makeText(getApplicationContext(),"Enter Firstname as well as Lastname",1000).show();
				}
				else if(password.getText().toString().length()!=4)
				{
					Toast.makeText(getApplicationContext(),"Password Length should be of 4-Digits",1000).show();
				}
				else if(isEmailId(email.getText().toString().trim())!=true)
				{
					Toast.makeText(getApplicationContext(),"Enter valid Email id",1000).show();
				}
				else if(secureText.getText().toString().compareTo("")==0)
				{
					Toast.makeText(getApplicationContext(),"Enter Security Text",1000).show();
				}
				
				
				else
				{
					String dm="Username : "+username.getText().toString()+"\nPasssword : "+password.getText().toString()+"\nEmail : "+email.getText().toString()+"\nSecurity Text: "+secureText.getText().toString()+"\nNote:Remember the security text";
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(FirstLaunchActivity.this);
		 	    	 
		             // Setting Dialog Title
		             alertDialog.setTitle("Do you want to confirm?");

		             // Setting Dialog Message
		             alertDialog.setMessage(dm);

		             // Setting Icon to Dialog
		             alertDialog.setIcon(android.R.drawable.ic_menu_add);

		             
		             alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		                 public void onClick(DialogInterface dialog, int which) {
		                	 SharedPreferences.Editor editor = sharedPref.edit();
		                	    editor.putInt("ACCOUNT_EXISTS",2222);
								editor.putString("USERNAME", username.getText().toString());
								editor.putInt("PASSWORD", Integer.parseInt(password.getText().toString()));
								editor.putString("EMAIL", email.getText().toString());
								editor.putString("SECURE_TEXT", secureText.getText().toString());
								Toast.makeText(getApplicationContext(),"Successfully Registered !!",Toast.LENGTH_SHORT).show();
								editor.commit();
								Intent i=new Intent(FirstLaunchActivity.this,LoginActivity.class);
			                    startActivity(i);
			                    finish();
		                
		                 }
		             });

		             
		             alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
		                 public void onClick(DialogInterface dialog, int which) {
		                	 dialog.cancel();	                 
		                 }
		             });
 

		             // Showing Alert Message
		             alertDialog.show();
					
				}
			}

			private boolean isEmailId(String s) {
				// TODO Auto-generated method stub
				if(s.contains("@") &&  s.contains("."))
				{
					return true;
				}
				else
				{
				return false;
				}
			}

			private boolean isFullName(String s) {
				// TODO Auto-generated method stub
				if (s.contains(" ")) {
                      return true;
				}
				else
				{
				return false;
				}
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
