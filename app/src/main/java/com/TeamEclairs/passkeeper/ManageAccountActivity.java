package com.TeamEclairs.passkeeper;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ManageAccountActivity extends Activity {

	EditText username,password,email,secureText;
	Button cancel,change;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_account);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1589FF")));
		
		
		username=(EditText)findViewById(R.id.usernameText);
		password=(EditText)findViewById(R.id.passwordText);
		email=(EditText)findViewById(R.id.emailText);
		secureText=(EditText)findViewById(R.id.secureText);
		cancel=(Button)findViewById(R.id.cancelButton);
		change=(Button)findViewById(R.id.changeButton);
		
		Context context = getApplicationContext();
    	SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    	username.setText(String.valueOf(sharedPref.getString("USERNAME", null)));
    	password.setText(String.valueOf(sharedPref.getInt("PASSWORD", 0)));
    	email.setText(String.valueOf(sharedPref.getString("EMAIL", null)));
    	secureText.setText(String.valueOf(sharedPref.getString("SECURE_TEXT", null)));
    	
    	cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
    	
    	change.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(username.getText().toString().compareTo("")==0)
				{
					Toast.makeText(getApplicationContext(),"Enter Username",1000).show();
				}
				else if(password.getText().toString().length()!=4)
				{
					Toast.makeText(getApplicationContext(),"Password Length should be of 4-Digits",1000).show();
				}
				else if(secureText.getText().toString().compareTo("")==0)
				{
					Toast.makeText(getApplicationContext(),"Enter Security Text",1000).show();
				}
				else if(isFullName(username.getText().toString().trim())!=true)
				{
					Toast.makeText(getApplicationContext(),"Enter Fullname as well as Lastname",1000).show();
				}
				else if(isEmailId(email.getText().toString().trim())!=true)
				{
					Toast.makeText(getApplicationContext(),"Enter valid Email id",1000).show();
				}
				else
				{
				Context context = getApplicationContext();
		    	SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
		    	SharedPreferences.Editor editor = sharedPref.edit();
        	    editor.putInt("ACCOUNT_EXISTS",2222);
				editor.putString("USERNAME", username.getText().toString());
				editor.putInt("PASSWORD", Integer.parseInt(password.getText().toString()));
				editor.putString("EMAIL", email.getText().toString());
				editor.putString("SECURE_TEXT", secureText.getText().toString());
				Toast.makeText(getApplicationContext(),"Values Updated !!",Toast.LENGTH_SHORT).show();
				editor.commit();
			    finish();
				}
			}
		});
		
		
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
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	
	}
}
