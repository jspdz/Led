package com.syd.led;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;


public class MainActivity extends Activity {
	private GpioJni mGpio = new GpioJni();
	private ToggleButton toggle;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggle = (ToggleButton) findViewById(R.id.toggleButton1);  
        toggle.setOnCheckedChangeListener(mtoggle);  
    	setLed(false);
 
       	Button button1 = (Button)findViewById(R.id.button1);
       	button1.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();
			}
			});
				
    }
    
    private ToggleButton.OnCheckedChangeListener mtoggle = new ToggleButton.OnCheckedChangeListener(){  
    	  
        @Override  
        public void onCheckedChanged(CompoundButton buttonView,  
                boolean isChecked) {  
            // TODO Auto-generated method stub  
            if(isChecked){  
            	setLed(true);
            }else{  
            	setLed(false);
            	}  
        }  
          
    }; 
    
    private void setLed(boolean led_state){
    	int value;
    	value = 0;
    	if(led_state == true){
    		value = 1;
    	}
		try {
	    	mGpio.gpioDeviceOpen();
	    	mGpio.setLED(value);
	    	mGpio.gpioDeviceClose();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("gpioDeviceOpen error...");
		}

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
