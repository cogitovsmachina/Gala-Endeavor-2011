package com.androidtitlan.galaendeavor.pojo;

public class Responses {

	ResponseFromHttpPost responseCreateUser = null;
	ResponseFromHttpPost responseBidding = null;
	
	
	
	public Responses(ResponseFromHttpPost responseCreateUser, ResponseFromHttpPost responseBidding) {
		this.responseCreateUser = responseCreateUser;
		this.responseBidding = responseBidding;
	}
	
	public ResponseFromHttpPost getResponseCreateUser() {
		return responseCreateUser;
	}
	public void setResponseCreateUser(ResponseFromHttpPost responseCreateUser) {
		this.responseCreateUser = responseCreateUser;
	}
	
	public ResponseFromHttpPost getResponseBidding() {
		return responseBidding;
	}
	public void setResponseBidding(ResponseFromHttpPost responseBidding) {
		this.responseBidding = responseBidding;
	}
	
	
}
