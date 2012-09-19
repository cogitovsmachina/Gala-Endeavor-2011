package com.androidtitlan.endeavorsubasta.io;

import static com.androidtitlan.endeavorsubasta.util.Resources.URL_SUBASTA;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.androidtitlan.galaendeavor.pojo.PullProductos;
import com.androidtitlan.galaendeavor.pojo.ResponseFromHttpPost;
import com.google.gson.Gson;

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

	public static ResponseFromHttpPost createUser(String usuario,
			String nombre, String mesa) throws IOException {

		ResponseFromHttpPost ansHttpPost = null;

		List<NameValuePair> params = new ArrayList<NameValuePair>(2);

		params.add(new BasicNameValuePair("usuario", usuario));
		params.add(new BasicNameValuePair("nombre", nombre));
		params.add(new BasicNameValuePair("mesa", mesa));

		ansHttpPost = doHttpPost(URL_SUBASTA + "creaUsuario/", params);

		return ansHttpPost;
		// ( ansHttpPost.getData() );
	}

	public static ResponseFromHttpPost sendBidding(String usuario,
			String producto, String precio) throws IOException {
		ResponseFromHttpPost response = null;

		List<NameValuePair> params = new ArrayList<NameValuePair>(1);

		params.add(new BasicNameValuePair("usuario", usuario));
		params.add(new BasicNameValuePair("producto", producto));
		params.add(new BasicNameValuePair("precio", precio));

		response = doHttpPost(URL_SUBASTA + "pujaProducto/", params);

		return response;

	}

	public static PullProductos pullProductos() throws IOException {

		String ansHttpGet = null;
		Gson gson = new Gson();

		PullProductos productos = null;
		ansHttpGet = inputStreamToString(doHttpGet(URL_SUBASTA
				+ "pullProducto/"));
		Log.e("***", "answer: " + ansHttpGet);
		if (ansHttpGet != null) {
			productos = gson.fromJson(ansHttpGet, PullProductos.class);
		}

		return productos;
	}
}
