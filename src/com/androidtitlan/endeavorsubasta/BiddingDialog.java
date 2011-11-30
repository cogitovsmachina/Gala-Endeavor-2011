package com.androidtitlan.endeavorsubasta;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.androidtitlan.endeavorsubasta.io.DoBiddingTask;
import com.androidtitlan.endeavorsubasta.io.DoHttpPostTask;
import com.androidtitlan.endeavorsubasta.util.Resources;

public class BiddingDialog extends Activity {
	public static final String USERNAME_RESULT_FROM_DIALOG = "USERNAME";
	public static final String NEW_USERNAME_RESULT_FROM_DIALOG = "NEW_USERNAME";
	public static final String NEW_FULLNAME_RESULT_FROM_DIALOG = "NEW_FULLNAME";
	public static final String NEW_TABLE_RESULT_FROM_DIALOG = "NEW_TABLE";

	public static final int VISIBLE = 0;
	public static final int GONE = 8;
	
	private int userBid;
	private int product;

	private EditText nameEdit, nameRegistroEdit, userNameEdit, tableNumberEdit;
	private CheckBox acceptedTermsAndConditions;
	private boolean layoutIsLogin = true;
	private String fullName;
	private String tableNumber;
	private String userName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.custom_dialog);
		nameEdit = (EditText) findViewById(R.id.biddername);
		Bundle extras = getIntent().getExtras();
		userBid=extras.getInt("Bid");
		product=extras.getInt("Product");		
	}

	/**
	 * This is a method binded with the Button Onclick property
	 */
	public void returnNamefromBiddingDialog(View v) {
		if (nameEdit.getText().toString().equals("")) {
			Toast.makeText(this, "Por favor, escriba su nombre de usuario",
					Toast.LENGTH_SHORT).show();
			return;
		}
		userName = nameEdit.getText().toString();
		new DoBiddingTask(userName, Integer.toString(userBid)).execute(Resources.TEST_URL);
		finish();
	}

	public void registerNewUser(View v){
		if( (!acceptedTermsAndConditions.isChecked())){
<<<<<<< HEAD
			Toast.makeText(this, "Por favor, acepte los términos y condiciones", Toast.LENGTH_SHORT).show();
=======
			Toast.makeText(this, "Por favor, acepte los t�rminos y condiciones", Toast.LENGTH_SHORT).show();
>>>>>>> c837d92ebbcb8b9df5e7583e1318930c7c83c994
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
<<<<<<< HEAD
		String tableNum=tableNumberEdit.getText().toString();
		if(tableNum.equals("")){
			Toast.makeText(this, "Por favor, escriba el número de su mesa", Toast.LENGTH_SHORT).show();
			return;
		}
		float tNum=Float.parseFloat(tableNum);
		if(!((tNum<=70)&&(tNum>=1))){
			Toast.makeText(this, "Por favor, escriba un número de mesa válido", Toast.LENGTH_SHORT).show();
=======
		if(tableNumberEdit.getText().toString().equals("")){
			Toast.makeText(this, "Por favor, escriba el n�mero de su mesa", Toast.LENGTH_SHORT).show();
>>>>>>> c837d92ebbcb8b9df5e7583e1318930c7c83c994
			return;
		}
		//Assigning to variables
		userName = userNameEdit.getText().toString();
		fullName = nameRegistroEdit.getText().toString();
		tableNumber = tableNumberEdit.getText().toString();
<<<<<<< HEAD
		new DoHttpPostTask(userName, fullName, tableNumber, Integer.toString(userBid), Integer.toString(product), this).execute(Resources.URL_SUBASTA);
		Log.d("Client_HTTP", Resources.URL_SUBASTA+"creaUsuario/");
		//new DoHttpPostTask(userName, fullName, tableNumber, Integer.toString(userBid), this).execute(Resources.URL_SUBASTA+"creaUsuario/");
		/*
		 * Change to this in production server
		 */
//		new DoHttpPostTask(userName, fullName, tableNumber, "test", this).execute(Resources.URL_SUBASTA+"creaUsuario/");
=======
		String strCreaUsuario = Resources.URL_SUBASTA+"creaUsuario/";
		Log.e(strCreaUsuario, "--------");

		new DoHttpPostTask(userName, fullName, tableNumber, this).execute(Resources.URL_SUBASTA+"/creaUsuario/");
>>>>>>> c837d92ebbcb8b9df5e7583e1318930c7c83c994
		finish();
	}

	public void cambioEntreRegistroLogin(View v) {
		if (layoutIsLogin) {
			setContentView(R.layout.custom_dialog_register);
			nameRegistroEdit = (EditText) findViewById(R.id.registro_biddername);
			userNameEdit = (EditText) findViewById(R.id.registro_bidderusername);
			tableNumberEdit = (EditText) findViewById(R.id.registro_biddertable);
			acceptedTermsAndConditions = (CheckBox) findViewById(R.id.termsandconditions);
			layoutIsLogin = false;
		} else {
			setContentView(R.layout.custom_dialog);
			layoutIsLogin = true;
		}
	}

	/**
	 * This is a method binded with the Button Onclick property
	 */
	public void cancelBiddingDialog(View v) {
		finish();
	}

}
