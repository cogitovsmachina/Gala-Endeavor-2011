package com.androidtitlan.endeavorsubasta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class BiddingDialog extends Activity {
	public static final String NAME_RESULT_FROM_DIALOG = "FULLNAME_DIALOG";
	private EditText nameEdit;
	private CheckBox acceptedTermsAndConditions;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.custom_dialog);
	nameEdit = (EditText)findViewById(R.id.biddername);
	acceptedTermsAndConditions = (CheckBox)findViewById(R.id.termsandconditions);
	}
	
    /**
     * This is a method binded with the Button Onclick property
     */
	public void returnNamefromBiddingDialog(View v){
		if( (!acceptedTermsAndConditions.isChecked())){
			Toast.makeText(this, "Please accept Terms and Conditions", Toast.LENGTH_SHORT).show();
			if(nameEdit.getText().toString().equals("")){
				Toast.makeText(this, "Please add your name", Toast.LENGTH_SHORT).show();
			}
		}
		else{
			Intent resultIntent = new Intent(this, BiddingDialog.class);
			resultIntent.putExtra(NAME_RESULT_FROM_DIALOG, nameEdit.getText().toString());
			setResult(Activity.RESULT_OK, resultIntent);
			finish();
		}	
	}
	
	/**
     * This is a method binded with the Button Onclick property
     */
	public void cancelBiddingDialog(View v){
	finish();
	}

}
