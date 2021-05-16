package com.TeamEclairs.passkeeper;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewActivity extends Activity {

	String []  data = {"Gmail","Facebook","Yahoo","Twitter","Flipkart","Myntra","Amazon","Bank"};
	EditText username,url,password,notes;
	Button save,cancel;
	AutoCompleteTextView accountType;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1589FF")));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		username=(EditText)findViewById(R.id.usernameText);
		url=(EditText)findViewById(R.id.urlText);
		password=(EditText)findViewById(R.id.passwordText);
		notes=(EditText)findViewById(R.id.noteText);
		save=(Button)findViewById(R.id.saveButton);
		cancel=(Button)findViewById(R.id.cancelButton);
		accountType = (AutoCompleteTextView)findViewById(R.id.accountTypeText);
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
		
		save.setOnClickListener(new View.OnClickListener() {
			
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
			        
			        /**
			         * CRUD Operations
			         * */
			        // Inserting Contacts
			        Log.d("Insert: ", "Inserting ..");
			        db.addPassword(new Password(accountType.getText().toString(), username.getText().toString(),url.getText().toString(),password.getText().toString(),notes.getText().toString()));
			 
			        // Reading all contacts
			        Log.d("Reading: ", "Reading all contacts..");
			        List<Password> password = db.getAllPassword();       
			 
			        for (Password pw : password) {
			            String log = "Account Type: "+pw.getAccountType()+" ,Username: " + pw.getUsername() + " ,URL: " + pw.getUrl() + " ,Password: " + pw.getPassword() + " ,Notes: " + pw.getNotes();
			              
			            // Writing Contacts to log
			        Log.d("Name: ", log);
			        setResult(100);
			        finish();
			        }
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
