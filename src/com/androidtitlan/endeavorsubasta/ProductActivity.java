package com.androidtitlan.endeavorsubasta;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.androidtitlan.endeavorsubasta.ui.Dialog;

public class ProductActivity extends Activity {
	
	private static final int MY_CUSTOM_DIALOG = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//TODO: Implement the needed logic to inflate the layout for each product
//		int resultFromActivity = savedInstanceState.getInt("Product");
		
		
		setContentView(R.layout.product_one);

	}
	
	@Override
	protected void onStart() {
	    super.onStart();
	    ActionBar actionBar = this.getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) {
		case (MY_CUSTOM_DIALOG): {
			if (resultCode == Activity.RESULT_OK) {
				Log.d("ANDROID_DIALOG","Coming back from BiddingDialog...");
				String fullName = data.getStringExtra(BiddingDialog.NAME_RESULT_FROM_DIALOG);
				Log.d("ANDROID_DIALOG", "User entered: " + fullName);
//				Toast.makeText(this, "Gracias, " +fullName, Toast.LENGTH_SHORT).show();

		}
			break;
		}
		
		}
	}
		
	 /**
     * Inflating the Action Bar menu with MenuInflater instance and using the method inflate.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_menu_items, menu);
        return true;
    }
    
    /**
     * This is a method binded with the Button Onclick property
     */
    public void openBiddingDialog(View v){
    	Intent intent = new Intent(this, BiddingDialog.class);
    	startActivityForResult(intent, MY_CUSTOM_DIALOG);    
    	}
    
    
    /**
     * Listener for the ActionBar Menu.
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        	case android.R.id.home:
	            Intent intent = new Intent(this, EndeavorSubastaActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	            return true;
        	case R.id.menu_help:
            	Dialog.showHelpMessage(this);
            	return true;
            case R.id.menu_about:
            	Dialog.showAboutMessage(this);
            	return true;
        	default:
                return super.onOptionsItemSelected(item);
        }
    }  
}
