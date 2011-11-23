package com.androidtitlan.endeavorsubasta;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.androidtitlan.endeavorsubasta.ui.Dialog;

public class ProductActivity extends Activity {
	
	private static final int MY_CUSTOM_DIALOG = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
<<<<<<< HEAD
		/*
		 * TextViews dinamicos de Nombre del ultimo bidder y precio actual
		 */
		final TextView ofertante;
		final TextView precioActual;
		/*
		 * Inflado especifico del layout para cada producto
		 */
		int selectedProduct = savedInstanceState.getInt("Product");
		switch(selectedProduct){
		case 1:
			/*setContentView(R.layout.product_one);
			ofertante=(TextView)findViewById(R.id.bidder_1);
			precioActual=(TextView)findViewById(R.id.price_1);
			*/
			break;
		case 2:
			/*setContentView(R.layout.product_two);
			ofertante=(TextView)findViewById(R.id.bidder_2);
			precioActual=(TextView)findViewById(R.id.price_2);
			*/
			break;
		case 3:
			/*setContentView(R.layout.product_three);
			ofertante=(TextView)findViewById(R.id.bidder_3);
			precioActual=(TextView)findViewById(R.id.price_3);
			*/
			break;
		case 4:
			/*setContentView(R.layout.product_four);
			ofertante=(TextView)findViewById(R.id.bidder_4);
			precioActual=(TextView)findViewById(R.id.price_4);
			*/
			break;
		case 5:
			/*setContentView(R.layout.product_five);
			ofertante=(TextView)findViewById(R.id.bidder_5);
			precioActual=(TextView)findViewById(R.id.price_5);
			*/
			break;
		case 6:
			/*setContentView(R.layout.product_six);
			ofertante=(TextView)findViewById(R.id.bidder_6);
			precioActual=(TextView)findViewById(R.id.price_6);
			*/
			break;
		case 7:
			/*setContentView(R.layout.product_seven);
			ofertante=(TextView)findViewById(R.id.bidder_7);
			precioActual=(TextView)findViewById(R.id.price_7);
			*/
			break;
		case 8:
			/*setContentView(R.layout.product_eight);
			ofertante=(TextView)findViewById(R.id.bidder_8);
			precioActual=(TextView)findViewById(R.id.price_8);
			*/
			break;
		case 9:
			/*setContentView(R.layout.product_nine);
			ofertante=(TextView)findViewById(R.id.bidder_9);
			precioActual=(TextView)findViewById(R.id.price_9);
			*/
			break;
		}
		/*
		 * TODO:Realizar conexion al server y obtener String de nombre del bidder y precio actual
		 */
		String bidderName;
		int    price;
		price=1000000;
		bidderName="El exitoso Enrique Díaz";
		//ofertante.setText(bidderName);
		//precioActual.setText(Integer.toString(price));
=======
		
		//TODO: Implement the needed logic to inflate the layout for each product
//		int resultFromActivity = savedInstanceState.getInt("Product");
		
		
		setContentView(R.layout.product_one);

>>>>>>> 32e643920fe5f292d1eeba3fea26099304f4d647
	}
	
	@Override
	protected void onStart() {
	    super.onStart();
	    ActionBar actionBar = this.getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) {
		case (MY_CUSTOM_DIALOG): {
			if (resultCode == Activity.RESULT_OK) {
				Log.d("ANDROID_DIALOG","Coming back from BiddingDialog...");
				String fullName = data.getStringExtra(BiddingDialog.NAME_RESULT_FROM_DIALOG);
				Log.d("ANDROID_DIALOG", "User entered: " + fullName);
//				Toast.makeText(this, "Gracias, " +fullName, Toast.LENGTH_SHORT).show();

		}
			break;
		}
		
		}
	}
		
	 /**
     * Inflating the Action Bar menu with MenuInflater instance and using the method inflate.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_menu_items, menu);
        return true;
    }
    
    /**
     * This is a method binded with the Button Onclick property
     */
    public void openBiddingDialog(View v){
    	Intent intent = new Intent(this, BiddingDialog.class);
    	startActivityForResult(intent, MY_CUSTOM_DIALOG);    
    	}
    
    
    /**
     * Listener for the ActionBar Menu.
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        	case android.R.id.home:
	            Intent intent = new Intent(this, EndeavorSubastaActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	            return true;
        	case R.id.menu_help:
            	Dialog.showHelpMessage(this);
            	return true;
            case R.id.menu_about:
            	Dialog.showAboutMessage(this);
            	return true;
        	default:
                return super.onOptionsItemSelected(item);
        }
    }  
}
