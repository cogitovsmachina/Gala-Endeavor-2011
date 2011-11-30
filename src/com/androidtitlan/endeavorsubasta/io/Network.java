package com.androidtitlan.endeavorsubasta.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.androidtitlan.galaendeavor.pojo.ResponseFromHttpPost;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Network {

	private static Context context;
	private InputStream inputStream;

	public Network(Context context) {
		this.context = context;
	}

	/*
	 * Method to build a InputStream to String
	 */
	protected static String inputStreamToString(InputStream in)
			throws IOException {
		StringBuilder out = new StringBuilder();

		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}

	protected InputStream doHttpGet(String urlString) throws IOException {

		InputStream inputStream = null;
		int response = -1;

		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Not an HTTP connection");

		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.setUseCaches(true);

			/*
			 * Object content = httpConn.getContent(); if( content instanceof
			 * Bitmap ){ Bitmap bitmap = (Bitmap)content; }
			 */

			response = httpConn.getResponseCode();
			if (response == HttpURLConnection.HTTP_OK) {
				inputStream = httpConn.getInputStream();
			}
		} catch (Exception ex) {
			throw new IOException("Error connecting" + ex);
		}
		return inputStream;
	}

	protected static ResponseFromHttpPost doHttpPost(String urlString,
			List<NameValuePair> nameValuePairs) throws IOException {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(urlString);

		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		HttpResponse response = httpclient.execute(httppost);

		InputStream out = response.getEntity().getContent();
		int statusCode = response.getStatusLine().getStatusCode();

		return new ResponseFromHttpPost(out, statusCode);
	}

	protected boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		boolean ans = false;
		final NetworkInfo network_info = cm.getActiveNetworkInfo();

		if (network_info != null && network_info.isConnected()) {
			Log.e("Testing Internet Connection", "We have internet");
			ans = true;
		} else {
			Log.e("Testing Internet Connection", "We dont have internet");
			ans = false;
		}

		return ans;

	}

}
