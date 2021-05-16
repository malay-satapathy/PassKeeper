package com.TeamEclairs.passkeeper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends Activity {

	TextView ht;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		ht=(TextView)findViewById(R.id.HelpText);
		String msg="> PassKeeper allows you to efficiently store your passwords of various Account Types ( e.g. Gmail, Yahoo, Facebook and Bank etc ) for future incase you at some point forget a long used Account.\n\n> Use the 'Add New' in the drawer list or the '+' sign at the top of the main page to add your details and securely save it.\n\n> You can later update pre-added details and also delete if incase you dont need it.\n\n> The 'Text Word' you entered at the beginning of Signing Up into account, will be used as the security word for generating an auto-generated PIN, in case you forget your PIN. Write it somewhere or use a word that you can remember well.\n";
		ht.setText(msg);
	}

}
