package com.TeamEclairs.passkeeper;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends Activity {

	String []  data = {"GMail","Facebook","Yahoo","Twitter","Bank"};
	String ACCOUNTTYPE;
	String USERNAME;
	String URL;
	String PASSWORD;
	String NOTES;
	EditText username,url,password,notes;
	Button update,cancel;
	AutoCompleteTextView accountType;
	private Intent intent;
	private List<Password> password2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1589FF")));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		intent = getIntent();
	    Bundle bun = this.getIntent().getExtras();
	    ACCOUNTTYPE = bun.getString("ACCOUNTTYPE");
	    USERNAME = bun.getString("USERNAME");
	    URL = bun.getString("URL");
	    PASSWORD = bun.getString("PASSWORD");
	    NOTES = bun.getString("NOTES");
	    
	    username=(EditText)findViewById(R.id.usernameText);
		url=(EditText)findViewById(R.id.urlText);
		password=(EditText)findViewById(R.id.passwordText);
		notes=(EditText)findViewById(R.id.noteText);
		update=(Button)findViewById(R.id.updateButton);
		cancel=(Button)findViewById(R.id.cancelButton);
		accountType = (AutoCompleteTextView)findViewById(R.id.accountTypeText);
		
		accountType.setText(ACCOUNTTYPE);
		username.setText(USERNAME);
		url.setText(URL);
		password.setText(PASSWORD);
		notes.setText(NOTES);
	    
		url.setEnabled(false);
		
		ArrayAdapter<String> ad;
		ad = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,data);
		accountType.setAdapter(ad);
		
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		update.setOnClickListener(new View.OnClickListener() {
			
			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(username.getText().toString().compareTo("")==0 && password.getText().toString().compareTo("")==0 && url.getText().toString().compareTo("")==0 && accountType.getText().toString().compareTo("")==0)
				{
					Toast.makeText(getApplicationContext(),"Enter all * fields",Toast.LENGTH_SHORT).show();
				}
				else
				{
				 DatabaseHandler db = new DatabaseHandler(getApplicationContext());
				 
				 password2 = db.getAllPassword();
				 db.updatePassword(new Password(accountType.getText().toString(), username.getText().toString(),url.getText().toString(),password.getText().toString(),notes.getText().toString()));
				 finish();
				}
			}
		});
	   
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
