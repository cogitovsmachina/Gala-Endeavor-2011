package com.androidtitlan.endeavorsubasta.io;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

public class DoBiddingTask extends AsyncTask<String, String, String> {
	private ProgressDialog dialog;
	private Activity activity;
	private String mUser;
	private String mNombre;
	private String mMesa;
	private Activity mActivity;
	private String mBid;
	
	/**
	 * constructor
	 */
	public DoBiddingTask(String user, String bid){
		mUser = user;
		mBid = bid;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}


	/**
	 * background
	 */

	@Override
    protected String doInBackground(String... params) {
		try {
			WebServices.sendBidding(mUser, mBid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
            

    /**
     * on getting result
     */
    @Override
    protected void onPostExecute(String result) {
//    	Toast.makeText(mActivity, "Se ha enviado tu registro al servidor, gracias.", Toast.LENGTH_LONG).show();
    }


}