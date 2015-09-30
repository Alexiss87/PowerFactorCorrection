package com.gmail.simpson.o.alexis;


import android.os.Bundle;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.content.DialogInterface;
import android.content.Intent;




@SuppressWarnings("deprecation")
public class PfcTabActivity extends TabActivity {
	
	//private static final String TAG = "PfcTabActivity";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pfc_tab);
		// Show the Up button in the action bar.
		//setupActionBar();

		TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
			/**	          
	        // Tab for unity calculator 
	        TabSpec unity = tabHost.newTabSpec("Correct power facctor to unity");
	        // setting Title and Icon for the Tab
	        unity.setIndicator(getResources().getString(R.string.title_activity_pfc_tab));
	        Intent unityIntent = new Intent(this, PowerFactorCorrection.class);
	        unity.setContent(unityIntent);
	        **/
	         
	        // Tab below unity
	        TabSpec belowUnity = tabHost.newTabSpec("Correct power factor to below unity");        
	        belowUnity.setIndicator(getResources().getString(R.string.title_activity_correct_to_below_unity));
	        Intent belowUnityIntent = new Intent(this, PowerFactorCorrection.class);
	        belowUnity.setContent(belowUnityIntent);
	        
	        //Tab for pf calculation
	        TabSpec pfCalculation = tabHost.newTabSpec("Calculate pf");
	        pfCalculation.setIndicator(getResources().getString(R.string.title_Tab3));
	        Intent pfCalculationIntent = new Intent(this,PowerFactorCalculation.class);
	        pfCalculation.setContent(pfCalculationIntent);
	        
	        	       	         
	        // Adding all TabSpec to TabHost
	        
	       // tabHost.addTab(unity); 
	        tabHost.addTab(belowUnity);
	        tabHost.addTab(pfCalculation);
	        
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pf_correction_menu, menu);

		return true;
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item){
		switch(item.getItemId()) {
            case R.id.help:
                //show dailog message
                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(PfcTabActivity.this);

                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage(R.string.help_messages)
                        .setTitle(R.string.dialog_title);
                //create a dialog ok button
                builder.setPositiveButton(R.string.dialog_ok_btn, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });

                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                //show dialog
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }



		
	}



}
