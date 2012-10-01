package com.androidtitlan.endeavorsubasta.io;

import com.androidtitlan.galaendeavor.pojo.ResponseFromHttpPost;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

public class DoBiddingTask extends
		AsyncTask<String, String, ResponseFromHttpPost> {
	private String mUser;
	private Activity mActivity;
	private String mPrice;
	private String mProduct;
	private final ProgressDialog dialog = new ProgressDialog(mActivity);

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
		dialog.setMessage("Pujando ahora, por favor espera...");
		dialog.show();
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
		try{
		if (response.getStatusCode() == 200) {
			this.dialog.dismiss();
			Toast.makeText(mActivity, "Se ha enviado la oferta al servidor",
					Toast.LENGTH_LONG).show();
		}}
		catch (Exception e) {
			Toast.makeText(mActivity, "No hay conexion al servidor",
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
			
		}


		// AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
//		try {
//			if (response.getStatusCode() == 200) {
				// builder.setMessage("Se ha enviado la oferta al servidor")
				// .setCancelable(false)
				// .setNegativeButton("Ok",
				// new DialogInterface.OnClickListener() {
				// public void onClick(DialogInterface dialog,
				// int id) {
				// dialog.cancel();
				// }
				// });
				// Toast.makeText(mActivity,
				// "Se ha enviado la oferta al servidor",
				// Toast.LENGTH_LONG).show();
//			}
//			if (response.getStatusCode() == 400) {
				/*
				 * builder.setMessage(
				 * "Tu oferta no ha podido ser enviada, intenta nuevamente")
				 * .setCancelable(false) .setNegativeButton("Ok", new
				 * DialogInterface.OnClickListener() { public void
				 * onClick(DialogInterface dialog, int id) { dialog.cancel(); }
				 * });
				 */
//				Toast.makeText(
//						mActivity,
//						"Tu oferta no ha podido ser enviada, intenta nuevamente",
//						Toast.LENGTH_LONG).show();
//			}
//			if (response.getStatusCode() == 500) {
				/*
				 * builder.setMessage(
				 * "El usuario no existe verifica tu nombre de usuario")
				 * .setCancelable(false) .setNegativeButton("Ok", new
				 * DialogInterface.OnClickListener() { public void
				 * onClick(DialogInterface dialog, int id) { dialog.cancel(); }
				 * });
				 */
//				Toast.makeText(mActivity,
//						"El usuario no existe verifica tu nombre de usuario",
//						Toast.LENGTH_LONG).show();
//			}
//		} catch (NullPointerException e) {
			/*
			 * builder.setMessage("No hay conexion al servidor")
			 * .setCancelable(false) .setNegativeButton("Ok", new
			 * DialogInterface.OnClickListener() { public void
			 * onClick(DialogInterface dialog, int id) { dialog.cancel(); } });
			 */
//			Toast.makeText(mActivity, "No hay conexion al servidor",
//					Toast.LENGTH_LONG).show();
//			e.printStackTrace();
//		}
		/*
		 * AlertDialog alertDialog = builder.create(); alertDialog.show();
		 * return;
		 */
	}

	@Override
	protected void onProgressUpdate(String... values) {
		AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

		super.onProgressUpdate(values);
	}
}