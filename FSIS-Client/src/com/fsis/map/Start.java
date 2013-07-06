package com.fsis.map;

import com.fsis.map.R;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Start extends Activity {
    /** Called when the activity is first created. */
	
	private boolean active = true;
	private final int splashTime = 5000;
	MediaPlayer startupMusic;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
		//startupMusic = MediaPlayer.create(Start.this, 0x7f040000);
		//startupMusic.start();
		
	    Thread splashTread = new Thread() {
	        @Override
	        public void run() {
	            try {
	                int waited = 0;
	                while(active && (waited < splashTime)) {
	                    sleep(100);
	                    if(active) {
	                        waited += 100;
	                    }
	                }
	            } catch(InterruptedException e) {} 
	            finally {
	                finish();
	                startActivity(new Intent("com.fsis.map.MapManager"));
	                //stop();
	            }
	        }
	    };
	    splashTread.start();
	}
        
   
}