package com.androidtitlan.galaendeavor.pojo;

import java.io.InputStream;

public class ResponseFromHttpPost {
	public ResponseFromHttpPost(InputStream data, int statusCode) {
		this.data = data;
		this.statusCode = statusCode;
	}

	private InputStream data;
	private int statusCode;

	public InputStream getData() {
		return data;
	}

	public void setData(InputStream data) {
		this.data = data;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
