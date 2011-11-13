package com.androidtitlan.endeavorsubasta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class BiddingDialog extends Activity {
	public static final String NAME_RESULT_FROM_DIALOG = "FULLNAME_DIALOG";
	private EditText nameEdit;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.custom_dialog);
	nameEdit = (EditText)findViewById(R.id.biddername);
	}
	
    /**
     * This is a method binded with the Button Onclick property
     */
	public void returnNamefromBiddingDialog(View v){
	Intent resultIntent = new Intent(this, BiddingDialog.class);
	resultIntent.putExtra(NAME_RESULT_FROM_DIALOG, nameEdit.getText().toString());
	setResult(Activity.RESULT_OK, resultIntent);
	finish();
	}
	
	/**
     * This is a method binded with the Button Onclick property
     */
	public void cancelBiddingDialog(View v){
	finish();
	}

}
