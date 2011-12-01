package com.androidtitlan.endeavorsubasta.io;

import com.androidtitlan.galaendeavor.pojo.ResponseFromHttpPost;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

public class DoBiddingTask extends AsyncTask<String, String, ResponseFromHttpPost> {
	private ProgressDialog dialog;
	private Activity activity;
	private String mUser;
	private String mNombre;
	private String mMesa;
	private Activity mActivity;
	private String mBid;
	private String mPrice;
	private String mProduct;
	
	/**
	 * constructor
	 */
	public DoBiddingTask(String user, String price, String product, Activity activity){
		mUser = user;
		mProduct = product;
		mPrice = price;
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
    	if (response.getStatusCode() == 200) {
			Toast.makeText(mActivity, "Se ha enviado la oferta al servidor.",
					Toast.LENGTH_LONG).show();
		}
		if (response.getStatusCode() == 400) {
			Toast.makeText(
					mActivity,
					"Tu oferta no ha podido ser enviada, intenta nuevamente.",
					Toast.LENGTH_LONG).show();
		}
		if (response.getStatusCode() == 500) {
			Toast.makeText(mActivity,
					"El usuario no existe verifica tu nombre de usuario.",
					Toast.LENGTH_LONG).show();
    }
    }
}