package com.androidtitlan.endeavorsubasta.io;

import com.androidtitlan.galaendeavor.pojo.ResponseFromHttpPost;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

public class DoHttpPostTask extends
		AsyncTask<String, String, ResponseFromHttpPost> {
	private ProgressDialog dialog;
	private Activity activity;
	private String mUser;
	private String mNombre;
	private String mMesa;
	private Activity mActivity;
	private String mBid;
	private String mProduct;

	/**
	 * constructor
	 */
	public DoHttpPostTask(String user, String nombre, String mesa,
			Activity activity) {
		mUser = user;
		mNombre = nombre;
		mMesa = mesa;
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
	protected ResponseFromHttpPost doInBackground(String... params) {
		ResponseFromHttpPost response = null;
		try {
			response = WebServices.createUser(mUser, mNombre, mMesa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * on getting result
	 */

	protected void onPostExecute(ResponseFromHttpPost response) {
		if (response.getStatusCode() == 200) {
			Toast.makeText(mActivity, "Se ha creado el usuario.",
					Toast.LENGTH_LONG).show();
		}
		if (response.getStatusCode() == 400) {
			Toast.makeText(
					mActivity,
					"Tu formulario no ha podido ser enviado, intenta nuevamente",
					Toast.LENGTH_LONG).show();
		}
		if (response.getStatusCode() == 500) {
			Toast.makeText(mActivity,
					"El usuario ya existe, intenta con otro usuario",
					Toast.LENGTH_LONG).show();

		}
	}

}