package com.gmail.simpson.o.alexis;

;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appbrain.AdService;
import com.appbrain.AppBrain;
import com.appjolt.winback.Winback;

//import com.startapp.android.publish.StartAppAd;
//import com.startapp.android.publish.StartAppSDK;

//import java.util.Random;


public class PowerFactorCalculation extends Activity {
	
	private EditText truePower_et;
	private EditText apparentPower_et;
	private EditText powerFactor_et;
	private Button calculateBtn;
	private Button clearBtn;
	private Double truePower;
	private Double apparentPower;
	private Double powerFactor;

    private final String STARTAPPDeveloper_ID = "102835337";
    private final String STARTAPP_App_ID = "203253004";
    //private StartAppAd startAppAd;
	
	//private static final String TAG = "PowerFactorClass";	 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        //StartAppSDK.init(this, STARTAPPDeveloper_ID, STARTAPP_App_ID, false);
        AppBrain.init(this);
        setContentView(R.layout.activity_power_factor_calculation);

        if (android.os.Build.VERSION.SDK_INT < 21  ) {
            // Appjolt - Show EULA only in Google Play Installs (and Debug mode)
            if (Winback.isGooglePlayInstall(this)) {
                Winback.showEULA(this);
            }
            // Appjolt - Init SDK
            Winback.init(this);
        }

       // StartAppAd.showSlider(this);
       // startAppAd = new StartAppAd(this);
		
		truePower_et = (EditText)findViewById(R.id.True_power_Et);
		apparentPower_et = (EditText)findViewById(R.id.Apparent_power_Et);
		powerFactor_et = (EditText)findViewById(R.id.Power_factor_Et_3);
		calculateBtn = (Button)findViewById(R.id.Calculate_pf_btn);
		clearBtn = (Button)findViewById(R.id.clear_pf_btn);
		
		calculateBtn.setOnClickListener(new View.OnClickListener() {	
			
			@Override
			public void onClick(View v) {
				//show dialog/toast if all editTexts are empty 
				if(truePower_et.getText().toString().length()== 0&&
						apparentPower_et.getText().toString().length()== 0&&
						powerFactor_et.getText().toString().length()== 0){
					//show toast or dialog 
					Toast.makeText(PowerFactorCalculation.this, R.string.Message_fill_in_txtbox,
							Toast.LENGTH_SHORT).show();
										
				}//show dialog if all editText are full 
				else if (truePower_et.getText().toString().length()!= 0&&
						apparentPower_et.getText().toString().length()!= 0&&
						powerFactor_et.getText().toString().length()!= 0){
					//show another Dialog
					Toast.makeText(PowerFactorCalculation.this, R.string.Message_clear_one_txtBox,
							Toast.LENGTH_SHORT).show();								
					
				}//calculate true power
				else if (truePower_et.getText().toString().length()== 0 &&
						apparentPower_et.getText().toString().length()!= 0 &&
						powerFactor_et.getText().toString().length()!= 0 ){
					
					apparentPower= Double.parseDouble(apparentPower_et.getText().toString());
					powerFactor= Double.parseDouble(powerFactor_et.getText().toString());
					//validate
					if (powerFactor > 1){
						//show toast
						Toast.makeText(PowerFactorCalculation.this, R.string.Messsage_powerFactor_greater_1,
								Toast.LENGTH_SHORT).show();
						
					}else{
						//calculat true power here
						truePower = Calculation.calculateTruePower(apparentPower, powerFactor);
						String result =(String.format("%.2f",truePower));
						truePower_et.setText(result);
						
					}
					
				}else if (apparentPower_et.getText().toString().length()== 0 &&
						powerFactor_et.getText().toString().length()!= 0 &&
						truePower_et.getText().toString().length()!= 0){
					//calculate apparent power
						truePower= Double.parseDouble(truePower_et.getText().toString());
						powerFactor= Double.parseDouble(powerFactor_et.getText().toString());
						
						if (powerFactor > 1){
							//show toast
							Toast.makeText(PowerFactorCalculation.this, R.string.Message_truePower_greater_Apparent_power,
									Toast.LENGTH_SHORT).show();
							
						}else{
							apparentPower = Calculation.calculateApparentPower(truePower, powerFactor);
							String result =(String.format("%.2f",apparentPower));
							apparentPower_et.setText(result);
							
						}
					
					
				}else if (powerFactor_et.getText().toString().length()== 0&&
						apparentPower_et.getText().toString().length()!= 0 &&
						truePower_et.getText().toString().length()!= 0){
						//calculate powerFactor
					    truePower=Double.parseDouble(truePower_et.getText().toString());
					    apparentPower= Double.parseDouble(apparentPower_et.getText().toString());
						if (apparentPower < truePower){
							//show dialog
							Toast.makeText(PowerFactorCalculation.this, R.string.Message_truePower_greater_Apparent_power,
									Toast.LENGTH_SHORT).show();							
							
						}else{
							powerFactor = Calculation.calculatePowerFactor(truePower, apparentPower);
							String result =(String.format("%.2f",powerFactor));
							powerFactor_et.setText(result);
						}
				}
			}			
		
			
			
	});
		
		clearBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				truePower_et.getText().clear();
				apparentPower_et.getText().clear();
				powerFactor_et.getText().clear();
				
			}
		});
		
	}
	

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.power_factor_calculation, menu);
		return true;
	}*/
    @Override
    protected void onResume() {
        super.onResume();
       // startAppAd.onResume();
    }

    @Override
    public void onBackPressed() {

        /*Random random = new Random();
        int chance = random.nextInt(2);
        if (chance == 0) {
            startAppAd.onBackPressed();
        }else{

                AppBrain.getAds().showInterstitial(this);

        }*/
        AppBrain.getAds().showOfferWall(this);
        super.onBackPressed();
    }

    @Override
    public void onPause() {
        super.onPause();
       // startAppAd.onPause();
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_2, menu);
        AdService ads = AppBrain.getAds();
        MenuItem item = menu.add(ads.getOfferWallButtonLabel(this));
        ads.setOfferWallMenuItemClickListener(this, item);
        return super.onCreateOptionsMenu(menu);
        //return true;
    }
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item){
        switch(item.getItemId()) {

            case R.id.rate:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.gmail.simpson.o.alexis&hl=en"));
                startActivity(browserIntent);
                //Toast.makeText(this, "Thank you very much", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }




    }

}
