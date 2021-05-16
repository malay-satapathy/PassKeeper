package com.TeamEclairs.passkeeper;

import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,SensorEventListener{

    
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    Intent i;
    EditText et;
    private boolean proximityHover=false;
    SensorManager sm;
    Sensor s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1589FF")));
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        registerForContextMenu(getListView());
       
        et = (EditText) findViewById(R.id.search);
        
        
        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		s = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		        
        
    }
    
    @Override
    protected void onStart() {
    	// TODO Auto-generated method stub
    	super.onStart();
    	 int i;
    	 ActionBar actionBar = getActionBar();
    	 Context context = getApplicationContext();
     	SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
     	String n=sharedPref.getString("USERNAME", null);
     	n=n.trim();
    	n=n.substring(0,n.indexOf(" "));
         actionBar.setTitle("Hi! "+n);
         
         
         
     	final DatabaseHandler db = new DatabaseHandler(getApplicationContext());
     	 final List<Password> password = db.getAllPassword();  
     	 final String[] disps = new String[password.size()];
     	 final String[] lvarr= new String[password.size()];
     	final String ACCOUNTTYPE[]= new String[password.size()];
    	final String USERNAME[]= new String[password.size()];
    	final String URL[]= new String[password.size()];
    	final String PASSWORD[]= new String[password.size()];
    	final String NOTES[]= new String[password.size()];
     	i=0;
     	 for (Password pw : password) {
              String log = "Account Type: "+pw.getAccountType()+"\nUsername: " + pw.getUsername() + "\nURL: " + pw.getUrl() + "\nPassword: " + pw.getPassword() + "\nNotes: " + pw.getNotes();
              disps[i]=log;
              String lv=pw.getAccountType()+"\n"+pw.getUsername();
              lvarr[i]=lv;
              ACCOUNTTYPE[i]=pw.getAccountType();
              USERNAME[i]=pw.getUsername();
              URL[i]=pw.getUrl();
              PASSWORD[i]=pw.getPassword();
              NOTES[i]=pw.getNotes();
              i++;
          }
     	 
     	final ArrayAdapter<String> ad;
 		ad = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lvarr);
 		setListAdapter(ad);
 		
 		et.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
            	ad.getFilter().filter(arg0.toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1,
                    int arg2, int arg3) {

            }

            public void afterTextChanged(Editable arg0) {

            }
        });
 		
 		ListView lv = getListView();
 		lv.setTextFilterEnabled(true);
 	   lv.setOnItemClickListener(new OnItemClickListener()
 	   {
 	      @Override
 	      public void onItemClick(AdapterView<?> adapter, View v, final int position,
 	            long arg3) 
 	      {
 	    	 AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
 	    	 
             // Setting Dialog Title
             alertDialog.setTitle("DETAILS");

             String d=disps[position];
             // Setting Dialog Message
             alertDialog.setMessage(d);

             // Setting Icon to Dialog
             alertDialog.setIcon(android.R.drawable.ic_menu_add);

             
             alertDialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int which) {
               
                 Intent i=new Intent(getApplicationContext(), UpdateActivity.class);
                 i.putExtra("ACCOUNTTYPE", (ACCOUNTTYPE[position]));
                 i.putExtra("USERNAME", (USERNAME[position]));
                 i.putExtra("URL", (URL[position]));
                 i.putExtra("PASSWORD", (PASSWORD[position]));
                 i.putExtra("NOTES", (NOTES[position]));
                 startActivity(i);
                 }
             });

             
             alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int which) {
                 onStart();
                 
                 }
             });

             
             alertDialog.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int which) {
                	 DatabaseHandler db = new DatabaseHandler(getApplicationContext());
    				 db.deletePassword(new Password(ACCOUNTTYPE[position], USERNAME[position],URL[position],PASSWORD[position],NOTES[position]));
    				 onStart();
                 }
             });

             // Showing Alert Message
             alertDialog.show();
 	      }
 	   });
 		

    	
    	
    }
    
    
    @Override
    protected void onResume() {
       super.onResume();
       ((BaseAdapter)getListAdapter()).notifyDataSetChanged();
       sm.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                i=new Intent(this,AddNewActivity.class);
                startActivityForResult(i, 100);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                i=new Intent(this,SettingsActivity.class);
                startActivity(i);
                break;
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	if(resultCode == 100){
    		int i;
        	DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        	List<Password> password =null;
        	password = db.getAllPassword();  
        	String disp[] = new String[100];
        	i=0;
        	 for (Password pw : password) {
                 String log = "Account Type: "+pw.getAccountType()+" ,Username: " + pw.getUsername() + " ,URL: " + pw.getUrl() + " ,Password: " + pw.getPassword() + " ,Notes: " + pw.getNotes();
                 disp[i]=log;
                 i++;
             }
            
       }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        Context context = getApplicationContext();
    	SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    	String n=sharedPref.getString("USERNAME", null);
    	n=n.trim();
    	n=n.substring(0,n.indexOf(" "));
        actionBar.setTitle("Hi! "+n);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_logout) {
        	i=new Intent(this,LoginActivity.class);
        	finish();
            startActivity(i);
           
        }
        else if(id== R.id.action_help)
        {
        	i=new Intent(this,HelpActivity.class);
            startActivity(i);
        }
        
        else if(id== R.id.action_exit)
        {
        	final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("Exit PassKeeper?");
            alertDialog.setIcon(android.R.drawable.stat_sys_warning);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                	 moveTaskToBack(true);
                	 finish();
                	android.os.Process.killProcess(android.os.Process.myPid());
                	System.exit(1);
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
        else if(id==R.id.action_add)
        {
        	Intent i = new Intent(MainActivity.this,AddNewActivity.class);
	    	startActivity(i);
        	
        }
        else if(id==R.id.action_refresh)
        {
        	onStart();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float status = event.values[0];
		if(event.sensor.getType()==Sensor.TYPE_PROXIMITY){

		 if (proximityHover && status==0) {
			 i=new Intent(this,LoginActivity.class);
	            startActivity(i);
	            finish();
		    }

		 if(status==0)
		 {
		    proximityHover=true;
		 }
		    new Handler().postDelayed(new Runnable() {

		        @Override
		        public void run() {
		            proximityHover=false;                      
		        }
		    }, 2000);
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		sm.unregisterListener(this);
		super.onPause();
	}

}
