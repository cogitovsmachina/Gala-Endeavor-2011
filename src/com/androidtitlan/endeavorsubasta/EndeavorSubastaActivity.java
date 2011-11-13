package com.androidtitlan.endeavorsubasta;


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
	
//	@Override
//    public void onCreate(Bundle savedInstanceState) {	
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_custom_dialog); 
//    }
	    
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
            case R.id.menu_about:
            	showAboutMessage(this);
            	return true;
        }
        return false;
    }

    /**
     * Show About this app.
     * @param activity Activity started from.
     */
    
    public static void showAboutMessage(final Activity activity) {
            
        AlertDialog.Builder about = new AlertDialog.Builder(activity)
                .setTitle(R.string.about_title)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setMessage(R.string.about_text)
                .setPositiveButton(R.string.accept,
                            new android.content.DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
        about.show();

    }
    
    
//   
}