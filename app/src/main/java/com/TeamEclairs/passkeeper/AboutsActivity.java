package com.TeamEclairs.passkeeper;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;

public class AboutsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1589FF")));
		
		
		final Typewriter writer = new Typewriter(this);
        setContentView(writer);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Vanilla.ttf"); 
        //Add a character every 150ms
        writer.setCharacterDelay(100);
        writer.setTypeface(type);
        writer.setBackgroundColor(Color.parseColor("#C2DFFF"));
        writer.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        writer.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        writer.animateText("PassKeeper™\n\n\n\nDeveloped by:\n Malay Satapathy");
        
		
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
