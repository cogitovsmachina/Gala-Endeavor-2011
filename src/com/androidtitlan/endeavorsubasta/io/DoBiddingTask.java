package com.androidtitlan.endeavorsubasta.io;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.androidtitlan.galaendeavor.pojo.ResponseFromHttpPost;

public class DoBiddingTask extends
		AsyncTask<String, String, ResponseFromHttpPost> {
	private String mUser;
	private Activity mActivity;
	private String mPrice;
	private String mProduct;

	/**
	 * constructor
	 */
	public DoBiddingTask(String user, String price, String product,
			Activity activity) {
		mUser = user;
		mProduct = product;
		mPrice = price;
		mActivity = activity;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected ResponseFromHttpPost doInBackground(String... params) {
		ResponseFromHttpPost response = null;
		try {
			response = WebServices.sendBidding(mUser, mPrice, mProduct);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * on getting result
	 */

	protected void onPostExecute(ResponseFromHttpPost response) {
		// AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
		try {
			if (response.getStatusCode() == 200) {
				/*
				 * builder.setMessage("Se ha enviado la oferta al servidor")
				 * .setCancelable(false) .setNegativeButton("Ok", new
				 * DialogInterface.OnClickListener() { public void
				 * onClick(DialogInterface dialog, int id) { dialog.cancel(); }
				 * });
				 */
				Toast.makeText(mActivity,
						"Se ha enviado la oferta al servidor",
						Toast.LENGTH_LONG).show();
			}
			if (response.getStatusCode() == 400) {
				/*
				 * builder.setMessage(
				 * "Tu oferta no ha podido ser enviada, intenta nuevamente")
				 * .setCancelable(false) .setNegativeButton("Ok", new
				 * DialogInterface.OnClickListener() { public void
				 * onClick(DialogInterface dialog, int id) { dialog.cancel(); }
				 * });
				 */
				Toast.makeText(
						mActivity,
						"Tu oferta no ha podido ser enviada, intenta nuevamente",
						Toast.LENGTH_LONG).show();
			}
			if (response.getStatusCode() == 500) {
				/*
				 * builder.setMessage(
				 * "El usuario no existe verifica tu nombre de usuario")
				 * .setCancelable(false) .setNegativeButton("Ok", new
				 * DialogInterface.OnClickListener() { public void
				 * onClick(DialogInterface dialog, int id) { dialog.cancel(); }
				 * });
				 */
				Toast.makeText(mActivity,
						"El usuario no existe verifica tu nombre de usuario",
						Toast.LENGTH_LONG).show();
			}
		} catch (NullPointerException e) {
			/*
			 * builder.setMessage("No hay conexion al servidor")
			 * .setCancelable(false) .setNegativeButton("Ok", new
			 * DialogInterface.OnClickListener() { public void
			 * onClick(DialogInterface dialog, int id) { dialog.cancel(); } });
			 */
			Toast.makeText(mActivity, "No hay conexion al servidor",
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		/*
		 * AlertDialog alertDialog = builder.create(); alertDialog.show();
		 * return;
		 */
	}
}