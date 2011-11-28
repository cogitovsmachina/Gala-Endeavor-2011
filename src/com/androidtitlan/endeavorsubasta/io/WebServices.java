package com.androidtitlan.endeavorsubasta.io;

import static com.androidtitlan.endeavorsubasta.util.resources.URL_SUBASTA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.androidtitlan.endeavorsubasta.util.resources;

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

	public static String createUser(String user, String nombre, String mesa)
			throws IOException {

		String ansHttpPost = null;

		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		Log.d("Client_HTTP", "URL: " + URL_SUBASTA + "creaUsuario/");

		params.add(new BasicNameValuePair("user", user));
		params.add(new BasicNameValuePair("nombre", nombre));
		params.add(new BasicNameValuePair("mesa", mesa));
		Log.d("Client HTTP", "A punto de hacer la petici—n POST");
		
		
		ansHttpPost = inputStreamToString(doHttpPost(resources.TEST_URL, params));
//		ansHttpPost = inputStreamToString(doHttpPost(URL_SUBASTA
//				+ "creaUsuario/", params));
		Log.d("Client_HTTP", "petici—n POST ejecutada: " + ansHttpPost);

		return ansHttpPost;
	}
}
