package com.androidtitlan.endeavorsubasta;


import com.androidtitlan.endeavorsubasta.ui.Dialog;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class EndeavorSubastaActivity extends Activity {


	@Override
	  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
      }
	
	@Override
	protected void onStart() {
	    super.onStart();
	    ActionBar actionBar = this.getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	}
	    
    /**
     * This is a method binded with the Button Onclick property
     */
    public void goToProductActivity(View v){
    	startActivity(new Intent(this, ProductActivity.class));
    }
    
    /**
//   * Method to call a customized dialog.
//   */
//	private void startCustomDialog() {
//  	Intent intent = new Intent(this, SearchDialog.class);
//  	startActivityForResult(intent, MY_CUSTOM_DIALOG);
//  	}
    
    /**
     * Inflating the Action Bar menu with MenuInflater instance and using the method inflate.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu_items, menu);
        return true;
    }
    
    
    /**
     * Listener for the ActionBar Menu.
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_help:
            	Dialog.showHelpMessage(this);
            	return true;
            case R.id.menu_about:
            	Dialog.showAboutMessage(this);
            	return true;
        }
        return false;
    }
   
}