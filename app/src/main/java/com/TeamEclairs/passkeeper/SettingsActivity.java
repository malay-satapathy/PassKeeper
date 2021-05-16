package com.TeamEclairs.passkeeper;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SettingsActivity extends ListActivity {

	String [] mi = {"Security","Abouts"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1589FF")));
		
		ArrayAdapter<String> ad;
 		ad = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mi);
 		setListAdapter(ad);
 		
 		getActionBar().setDisplayHomeAsUpEnabled(true);
 		
 		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if(position==0)
		{
			Intent i = new Intent(SettingsActivity.this,SecurityManagerActivity.class);
	    	startActivity(i);
		}
		else if(position==1)
		{
			Intent i = new Intent(SettingsActivity.this,AboutsActivity.class);
	    	startActivity(i);
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
