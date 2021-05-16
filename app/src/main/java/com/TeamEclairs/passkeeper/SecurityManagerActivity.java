package com.TeamEclairs.passkeeper;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SecurityManagerActivity extends ListActivity {

	String [] mi = {"Manage Account","Delete Account","Clear All Data"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_security_manager);
		
	    getActionBar().setDisplayHomeAsUpEnabled(true);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1589FF")));
		ArrayAdapter<String> ad;
 		ad = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mi);
 		setListAdapter(ad);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if(position==0)
		{
			Intent i = new Intent(SecurityManagerActivity.this,ManageAccountActivity.class);
	    	startActivity(i);
		}
		else if(position==1)
		{
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(SecurityManagerActivity.this);
            alertDialog.setTitle("Confirm delete?");
            alertDialog.setMessage("Are you sure you want to delete all login data?\n\nCaution :All data will be lost and can't be retrieved");
            alertDialog.setIcon(android.R.drawable.ic_menu_close_clear_cancel);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                	Context context = getApplicationContext();
               	 SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
               	Editor editor = sharedPref.edit();
               	editor.clear();
               	Toast.makeText(getApplicationContext(), "Account Deleted",Toast.LENGTH_SHORT).show();
               	editor.commit();
            	Context context1 = getApplicationContext();
    			context1.deleteDatabase("passwordManager");
               	Intent i = new Intent(SecurityManagerActivity.this,SplashActivity.class);
               	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
               	finish();
               startActivity(i);
    	    	
               	
                } 
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});

            // Showing Alert Message
            alertDialog.show();
            
		}
		else if(position==2)
		{
			final AlertDialog.Builder alertDialog = new AlertDialog.Builder(SecurityManagerActivity.this);
            alertDialog.setTitle("Delete all data?");
            alertDialog.setMessage("Caution : Data once deleted can't be restored.");
            alertDialog.setIcon(android.R.drawable.stat_sys_warning);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                	Context context = getApplicationContext();
        			context.deleteDatabase("passwordManager");
        			Toast.makeText(getApplicationContext(), "Data deleted", Toast.LENGTH_SHORT).show();
        			onStart();
                } 
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});

            // Showing Alert Message
            alertDialog.show();
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
