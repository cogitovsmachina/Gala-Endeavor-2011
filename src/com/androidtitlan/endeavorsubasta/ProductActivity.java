package com.androidtitlan.endeavorsubasta;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.FullNameStyle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ProductActivity extends Activity {
	
	private static final int MY_CUSTOM_DIALOG = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.productlayout);
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
            
        	case R.id.menu_about:
            	showAboutMessage(this);
            	return true;
        	default:
                return super.onOptionsItemSelected(item);
        }
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
    
}
