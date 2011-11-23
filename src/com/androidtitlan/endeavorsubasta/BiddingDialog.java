package com.androidtitlan.endeavorsubasta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class BiddingDialog extends Activity {
	public static final String USERNAME_RESULT_FROM_DIALOG = "USERNAME";
	public static final String NEW_USERNAME_RESULT_FROM_DIALOG = "NEW_USERNAME";
	public static final String NEW_FULLNAME_RESULT_FROM_DIALOG = "NEW_FULLNAME";
	public static final String NEW_TABLE_RESULT_FROM_DIALOG = "NEW_TABLE";
	
	public static final int VISIBLE = 0;
	public static final int GONE = 8;
	
	private EditText nameEdit, nameRegistroEdit, userNameEdit, tableNumberEdit;
	private CheckBox acceptedTermsAndConditions;
	private LinearLayout registro, login;
	private Button cambiador;
	private boolean layoutIsLogin=true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.custom_dialog);
	nameEdit = (EditText)findViewById(R.id.biddername);
	nameRegistroEdit = (EditText)findViewById(R.id.registro_biddername);
	userNameEdit = (EditText)findViewById(R.id.registro_bidderusername);
	tableNumberEdit = (EditText)findViewById(R.id.registro_biddertable);
	acceptedTermsAndConditions = (CheckBox)findViewById(R.id.termsandconditions);
	
	registro=(LinearLayout)findViewById(R.id.registro);
	login=(LinearLayout)findViewById(R.id.login);
	cambiador=(Button)findViewById(R.id.midButton);
	}
	
    /**
     * This is a method binded with the Button Onclick property
     */
	public void returnNamefromBiddingDialog(View v){
		if(nameEdit.getText().toString().equals("")){
				Toast.makeText(this, "Por favor, escriba su nombre de usuario", Toast.LENGTH_SHORT).show();
				return;
		}
		
		Intent resultIntent = new Intent(this, BiddingDialog.class);
		resultIntent.putExtra(USERNAME_RESULT_FROM_DIALOG, nameEdit.getText().toString());
		setResult(Activity.RESULT_OK, resultIntent);
		finish();	
	}
	
	public void registerNewUser(View v){
		if( (!acceptedTermsAndConditions.isChecked())){
			Toast.makeText(this, "Por favor, acepte los términos y condiciones", Toast.LENGTH_SHORT).show();
			return;
		}
		if(nameRegistroEdit.getText().toString().equals("")){
			Toast.makeText(this, "Por favor, escriba su nombre", Toast.LENGTH_SHORT).show();
			return;
		}
		if(userNameEdit.getText().toString().equals("")){
			Toast.makeText(this, "Por favor, escriba su nombre de usuario", Toast.LENGTH_SHORT).show();
			return;
		}
		if(tableNumberEdit.getText().toString().equals("")){
			Toast.makeText(this, "Por favor, escriba el número de su mesa", Toast.LENGTH_SHORT).show();
			return;
		}
		Intent resultIntent = new Intent(this, BiddingDialog.class);
		resultIntent.putExtra(NEW_USERNAME_RESULT_FROM_DIALOG, userNameEdit.getText().toString());
		resultIntent.putExtra(NEW_FULLNAME_RESULT_FROM_DIALOG, nameRegistroEdit.getText().toString());
		resultIntent.putExtra(NEW_TABLE_RESULT_FROM_DIALOG, tableNumberEdit.getText().toString());
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}
	
	public void cambioEntreRegistroLogin(View v){
		if(layoutIsLogin){
			registro.setVisibility(VISIBLE);
			login.setVisibility(GONE);
			layoutIsLogin=false;
		}else{
			registro.setVisibility(GONE);
			login.setVisibility(VISIBLE);
			layoutIsLogin=true;
		}
	}
	
	/**
     * This is a method binded with the Button Onclick property
     */
	public void cancelBiddingDialog(View v){
	finish();
	}

}
