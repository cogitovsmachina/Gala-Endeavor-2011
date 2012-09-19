package com.androidtitlan.endeavorsubasta;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidtitlan.endeavorsubasta.io.UpdateService;
import com.androidtitlan.endeavorsubasta.ui.HomeActivity;

public class SplashActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		ActionBar ab = getActionBar();
		ab.hide();
		setContentView(R.layout.splash);
		startService(new Intent(this, UpdateService.class));
	}

	public void goToHomeActivity(View v) {
		startActivity(new Intent(this, HomeActivity.class));
	}

	@Override
	protected void onStop() {
		finish();
		super.onStop();
	}

}
