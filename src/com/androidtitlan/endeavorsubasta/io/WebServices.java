package com.androidtitlan.endeavorsubasta.io;

import static com.androidtitlan.endeavorsubasta.util.Resources.URL_SUBASTA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.androidtitlan.endeavorsubasta.util.Resources;

//TODO: Implement GSON library to receive data from server
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;

public class WebServices extends Network {

	private SharedPreferences preferences;

	public WebServices(Context context) {
		super(context);
		this.preferences = PreferenceManager
				.getDefaultSharedPreferences(context);
	}

	public static String createUser(String user, String nombre, String mesa, String bid)
			throws IOException {

		String ansHttpPost = null;

		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	

		params.add(new BasicNameValuePair("user", user));
		params.add(new BasicNameValuePair("nombre", nombre));
		params.add(new BasicNameValuePair("mesa", mesa));
		params.add(new BasicNameValuePair("bid", bid));
				
//		ansHttpPost = inputStreamToString(doHttpPost(URL_SUBASTA + "creaUsuario/", params));
		ansHttpPost = inputStreamToString(doHttpPost(Resources.TEST_URL, params));
		return ansHttpPost;
	}
	
	public static String sendBidding(String user, String bid) throws IOException{
		String response = null;
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		
		params.add(new BasicNameValuePair("user", user));
		params.add(new BasicNameValuePair("bid", bid));
		
		/*
		 * Change for server ip
		 */
		response = inputStreamToString(doHttpPost(Resources.TEST_URL, params));
		
		return response;
		
	}
}
