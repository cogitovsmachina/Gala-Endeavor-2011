package com.androidtitlan.endeavorsubasta.io;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.androidtitlan.endeavorsubasta.util.resources;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class DoHttpPostTask extends AsyncTask<String, String, String> {
	@Override
	protected void onPreExecute() {
		
		super.onPreExecute();
	}

	private HashMap<String, String> mData = null;// post data
	private String user;
	private String nombre;
	private String mesa;
	private String mUser;
	private String mNombre;
	private String mMesa;

	/**
	 * constructor
	 */
//	public DoHttpPostTask(HashMap<String, String> data) {
//		mData = data;
//	}
	public DoHttpPostTask(String user, String nombre, String mesa){
		mUser = user;
		mNombre = nombre;
		mMesa = mesa;
	}

	/**
	 * background
	 */

	@Override
    protected String doInBackground(String... params) {
		try {
			WebServices.createUser(mUser, mNombre, mMesa);
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
        // something...
    }
}