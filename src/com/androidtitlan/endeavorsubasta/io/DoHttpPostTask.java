package com.androidtitlan.endeavorsubasta.io;

import com.androidtitlan.endeavorsubasta.util.Resources;
import com.androidtitlan.galaendeavor.pojo.ResponseFromHttpPost;
import com.androidtitlan.galaendeavor.pojo.Responses;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class DoHttpPostTask extends
		AsyncTask<String, String, Responses> {
	private ProgressDialog dialog;
	private String mNombre;
	private String mMesa;
	private Activity mActivity;
	private String mUsuario;
	private String mOferta;
	private String mProducto;

	/**
	 * constructor
	 */
	public DoHttpPostTask(String usuario, String nombre, String mesa,
			Activity activity) {
		mUsuario = usuario;
		mNombre = nombre;
		mMesa = mesa;
		mActivity = activity;
	}

	public DoHttpPostTask(String usuario, String nombre, String mesa,
			String oferta, String producto, Activity activity) {
		mUsuario = usuario;
		mNombre = nombre;
		mMesa = mesa;
		mOferta = oferta;
		mProducto = producto;
		mActivity = activity;

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	/**
	 * background
	 */

	@Override
	protected Responses doInBackground(String... params) {
		ResponseFromHttpPost responseCreateUser = null;
		ResponseFromHttpPost responseBidding = null;
		try {

			responseCreateUser = WebServices.createUser(mUsuario, mNombre, mMesa);
			if (responseCreateUser.getStatusCode() == 200) {
				responseBidding = WebServices.sendBidding(mUsuario, mProducto, mOferta);
			}

			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Responses(responseCreateUser, responseBidding);
	}

	/**
	 * on getting result
	 */

	protected void onPostExecute(Responses responses) {
		
		//Verificacion de los mensajes de error del webService de CrearUsuario
		
		if (responses.getResponseCreateUser().getStatusCode() == 200) {
			Toast.makeText(mActivity, "Se ha creado el usuario.",
					Toast.LENGTH_LONG).show();
		}
		else if (responses.getResponseCreateUser().getStatusCode() == 400) {
			Toast.makeText(
					mActivity,
					"Tu formulario no ha podido ser enviado, intenta nuevamente",
					Toast.LENGTH_LONG).show();
		}
		else if (responses.getResponseCreateUser().getStatusCode() == 500) {
			Toast.makeText(mActivity,
					"El usuario ya existe, intenta con otro usuario",
					Toast.LENGTH_LONG).show();

		}
		
		//Verificacion de los mensajes de error del webService de bidding
		
		if (responses.getResponseBidding().getStatusCode() != 200) {
			Toast.makeText(mActivity, "Ocurrio un erro al realizar la oferta.",
					Toast.LENGTH_LONG).show();
		}

		
		
	}

}