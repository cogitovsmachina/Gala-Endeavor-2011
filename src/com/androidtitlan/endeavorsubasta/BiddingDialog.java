package com.androidtitlan.endeavorsubasta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class BiddingDialog extends Activity {
	public static final String SEARCH_QUERY_RESULT_FROM_DIALOG = "SEARCH_DIALOG";
	private Button searchBtn;
	private Button cancelBtn;
	private EditText searchEdit;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.custom_dialog);
	searchBtn = (Button)findViewById(R.id.search);
	searchBtn.setOnClickListener(new OnClickListener(){
		public void onClick(View v) {
			returnSearchQuery();
		}
	});
	
	cancelBtn = (Button)findViewById(R.id.cancel);
	cancelBtn.setOnClickListener(new OnClickListener(){
		public void onClick(View v) {
			cancelDialog();
		}
	});
	
	searchEdit = (EditText)findViewById(R.id.biddername);
	searchEdit.setHint("Ejemplo: Vince Garc’a");
	}
	
	private void returnSearchQuery(){
	Intent resultIntent = new Intent(this, BiddingDialog.class);
	resultIntent.putExtra(SEARCH_QUERY_RESULT_FROM_DIALOG, searchEdit.getText().toString());
	setResult(Activity.RESULT_OK, resultIntent);
	finish();
	}
	
	private void cancelDialog(){
	finish();
	}

}
