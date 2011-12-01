package com.androidtitlan.endeavorsubasta;

import com.androidtitlan.endeavorsubasta.io.UpdateService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class SplashActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	      setContentView(R.layout.splash);
	      startService(new Intent(this, UpdateService.class));
	      Animation anim = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splashin);
	      final Animation anim2 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splashout);
	      final LinearLayout splash=(LinearLayout)findViewById(R.id.splash);
	      splash.startAnimation(anim);	      
	      splash.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				splash.startAnimation(anim2);
				startActivity(new Intent(SplashActivity.this, EndeavorSubastaActivity.class));
			}
		});
	}

	@Override
	protected void onStop() {
		finish();
		super.onStop();
	}
	
}
