package com.androidtitlan.endeavorsubasta.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.androidtitlan.endeavorsubasta.R;
import com.androidtitlan.endeavorsubasta.R.id;
import com.androidtitlan.endeavorsubasta.R.layout;
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

	private long userBid;
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
		userBid = extras.getLong("Bid");
		product = extras.getInt("Product");
	}

	/**
	 * This is a method binded with the Button Onclick property
	 */
	public void returnNamefromBiddingDialog(View v) {
		if (nameEdit.getText().toString().equals("")) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Por favor, escriba su nombre de usuario")
			.setCancelable(false)
			.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			AlertDialog alertDialog = builder.create();
			alertDialog.show();
			return;
		}
		userName = nameEdit.getText().toString();
		new DoBiddingTask(userName, Integer.toString(product),
				Long.toString(userBid), this).execute(Resources.URL_SUBASTA
				+ "pujaProducto/");
		Log.e(Resources.APP_NAME, "Puja enviada: "+userName+" pujo $"+Long.toString(userBid));

		finish();
	}


	public void registerNewUser(View v){
		if( (!acceptedTermsAndConditions.isChecked())){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Por favor, acepte los terminos y condiciones")
			.setCancelable(false)
			.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			}); 
			AlertDialog alertDialog = builder.create();
			alertDialog.show();
			return;
		}
		if (nameRegistroEdit.getText().toString().equals("")) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Por favor, escriba su nombre")
			.setCancelable(false)
			.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			AlertDialog alertDialog = builder.create();
			alertDialog.show();
			return;
		}
		if (userNameEdit.getText().toString().equals("")) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Por favor, escriba su nombre de usuario")
			.setCancelable(false)
			.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			AlertDialog alertDialog = builder.create();
			alertDialog.show();
			return;
		}
		if (tableNumberEdit.getText().toString().equals("")) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Por favor, escriba el numero de su mesa")
			.setCancelable(false)
			.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			AlertDialog alertDialog = builder.create();
			alertDialog.show();
			return;
		}

		// Assigning to variables
		userName = userNameEdit.getText().toString();
		fullName = nameRegistroEdit.getText().toString();
		tableNumber = tableNumberEdit.getText().toString();
		String tableNum=tableNumberEdit.getText().toString();
		float tNum=Float.parseFloat(tableNum);
		if(!((tNum<=70)&&(tNum>=1))){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Por favor, escriba un numero de mesa valido")
			.setCancelable(false)
			.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			AlertDialog alertDialog = builder.create();
			alertDialog.show();
			return;
		}
		//Assigning to variables
		userName = userNameEdit.getText().toString();
		fullName = nameRegistroEdit.getText().toString();
		tableNumber = tableNumberEdit.getText().toString();
		
		new DoHttpPostTask(userName, fullName, tableNumber, Long.toString(userBid), Integer.toString(product), this).execute(Resources.URL_SUBASTA);


		/*
		new DoHttpPostTask(userName, fullName, tableNumber, this)
				.execute(Resources.URL_SUBASTA + "/creaUsuario/");
		*/		
				

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
			nameEdit = (EditText) findViewById(R.id.biddername);
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
