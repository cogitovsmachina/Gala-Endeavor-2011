package com.androidtitlan.endeavorsubasta;

import java.text.NumberFormat;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidtitlan.endeavorsubasta.io.UpdateService;
import com.androidtitlan.endeavorsubasta.ui.BiddingDialog;
import com.androidtitlan.endeavorsubasta.ui.Dialog;
import com.androidtitlan.endeavorsubasta.ui.HomeActivity;
import com.androidtitlan.endeavorsubasta.ui.VerticalSeekBar;
import com.androidtitlan.endeavorsubasta.ui.VerticalSeekBar.OnSeekBarChangeListener;

public class ProductDetailActivity extends Activity {

	/**
	 * TextViews dinamicos de Nombre del ultimo bidder y precio actual
	 */
	TextView ofertante = null;
	TextView precioActual = null;
	TextView precioInicial = null;
	VerticalSeekBar vsk = null;
	TextView seekBarSlave = null;
	TextView yourBid = null;
	ImageView seekBarTextImage = null;
	long actualPrice, userBid;
	String bidderName;

	private static final int MAXIMUM_BID_AMOUNT = 5000;
	private int activeProduct;
	Messenger mService = null;
	boolean mIsBound;
	final Messenger mMessenger = new Messenger(new IncomingHandler());
	Timer tmr = new Timer();

	String fromService;

	class IncomingHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UpdateService.MSG_SET_STRING_VALUE:
				fromService = msg.getData().getString("Bid");
				StringTokenizer tk = new StringTokenizer(fromService, "%$%");
				String sActualPrice = tk.nextToken();
				bidderName = tk.nextToken();
				long tempActualPrice = (long) Float.parseFloat(sActualPrice);
				if (tempActualPrice != 0) {
					actualPrice = tempActualPrice;
					precioActual.setText(darFormato(sActualPrice) + " USD");
					ofertante.setText(bidderName);
				}
				break;
			case UpdateService.MSG_SET_BOOL_VALUE:
				// setContentView(R.layout.end_layout);
			default:
				super.handleMessage(msg);
			}
		}
	}

	private ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			mService = new Messenger(service);
			try {
				Message msg = Message.obtain(null,
						UpdateService.MSG_REGISTER_CLIENT);
				msg.replyTo = mMessenger;
				mService.send(msg);
			} catch (RemoteException e) {
			}
		}

		public void onServiceDisconnected(ComponentName className) {
			mService = null;
		}
	};

	/**
	 * Dirty hack to get ActionBar filled with a tile programatically
	 * 
	 * */
	private void setActionBarCustomBackground() {
		final ActionBar actionBar = getActionBar();
		BitmapDrawable background = new BitmapDrawable(
				BitmapFactory.decodeResource(getResources(),
						R.drawable.actionbar_tile));
		background.setTileModeX(android.graphics.Shader.TileMode.REPEAT);
		actionBar.setBackgroundDrawable(background);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarCustomBackground();
		doBindService();
		tmr.schedule(new TimerTask() {
			public void run() {
				sendInts();
			}
		}, 500);
		/**
		 * Inflado especifico del layout para cada producto
		 */
		Bundle extras = getIntent().getExtras();
		activeProduct = extras.getInt("Product");
		startUI(1, activeProduct);
	}

	private void startUI(int x, int selectedProduct) {
		switch (selectedProduct) {
		case 1:
			setContentView(R.layout.product_one);
			setTitle(R.string.product_title1);
			seekBarTextImage = (ImageView) findViewById(R.id.image_textodeslice1);
			vsk = (VerticalSeekBar) findViewById(R.id.seekbar1);
			seekBarSlave = (TextView) findViewById(R.id.seekBarSlave1);
			yourBid = (TextView) findViewById(R.id.offer1);
			precioInicial = (TextView) findViewById(R.id.initial_price_1);
			ofertante = (TextView) findViewById(R.id.bidder_1);
			precioActual = (TextView) findViewById(R.id.price_1);
			activeProduct = 1;
			break;
		case 2:
			setContentView(R.layout.product_two);
			setTitle(R.string.product_title2);
			seekBarTextImage = (ImageView) findViewById(R.id.image_textodeslice2);
			vsk = (VerticalSeekBar) findViewById(R.id.seekbar2);
			seekBarSlave = (TextView) findViewById(R.id.seekBarSlave2);
			yourBid = (TextView) findViewById(R.id.offer2);
			precioInicial = (TextView) findViewById(R.id.initial_price_2);
			ofertante = (TextView) findViewById(R.id.bidder_2);
			precioActual = (TextView) findViewById(R.id.price_2);
			activeProduct = 2;
			break;
		case 3:
			setContentView(R.layout.product_three);
			setTitle(R.string.product_title3);
			seekBarTextImage = (ImageView) findViewById(R.id.image_textodeslice3);
			vsk = (VerticalSeekBar) findViewById(R.id.seekbar3);
			seekBarSlave = (TextView) findViewById(R.id.seekBarSlave3);
			yourBid = (TextView) findViewById(R.id.offer3);
			precioInicial = (TextView) findViewById(R.id.initial_price_3);
			ofertante = (TextView) findViewById(R.id.bidder_3);
			precioActual = (TextView) findViewById(R.id.price_3);
			activeProduct = 3;
			break;
		case 4:
			setContentView(R.layout.product_four);
			setTitle(R.string.product_title4);
			seekBarTextImage = (ImageView) findViewById(R.id.image_textodeslice4);
			vsk = (VerticalSeekBar) findViewById(R.id.seekbar4);
			seekBarSlave = (TextView) findViewById(R.id.seekBarSlave4);
			yourBid = (TextView) findViewById(R.id.offer4);
			precioInicial = (TextView) findViewById(R.id.initial_price_4);
			ofertante = (TextView) findViewById(R.id.bidder_4);
			precioActual = (TextView) findViewById(R.id.price_4);
			activeProduct = 4;
			break;
		case 5:
			setContentView(R.layout.product_five);
			setTitle(R.string.product_title5);
			seekBarTextImage = (ImageView) findViewById(R.id.image_textodeslice5);
			vsk = (VerticalSeekBar) findViewById(R.id.seekbar5);
			seekBarSlave = (TextView) findViewById(R.id.seekBarSlave5);
			yourBid = (TextView) findViewById(R.id.offer5);
			precioInicial = (TextView) findViewById(R.id.initial_price_5);
			ofertante = (TextView) findViewById(R.id.bidder_5);
			precioActual = (TextView) findViewById(R.id.price_5);
			activeProduct = 5;
			break;
		case 6:
			setContentView(R.layout.product_six);
			setTitle(R.string.product_title6);
			seekBarTextImage = (ImageView) findViewById(R.id.image_textodeslice6);
			vsk = (VerticalSeekBar) findViewById(R.id.seekbar6);
			seekBarSlave = (TextView) findViewById(R.id.seekBarSlave6);
			yourBid = (TextView) findViewById(R.id.offer6);
			precioInicial = (TextView) findViewById(R.id.initial_price_6);
			ofertante = (TextView) findViewById(R.id.bidder_6);
			precioActual = (TextView) findViewById(R.id.price_6);
			activeProduct = 6;
			break;
		case 7:
			setContentView(R.layout.product_seven);
			setTitle(R.string.product_title7);
			seekBarTextImage = (ImageView) findViewById(R.id.image_textodeslice7);
			vsk = (VerticalSeekBar) findViewById(R.id.seekbar7);
			seekBarSlave = (TextView) findViewById(R.id.seekBarSlave7);
			yourBid = (TextView) findViewById(R.id.offer7);
			precioInicial = (TextView) findViewById(R.id.initial_price_7);
			ofertante = (TextView) findViewById(R.id.bidder_7);
			precioActual = (TextView) findViewById(R.id.price_7);
			activeProduct = 7;
			break;
		case 8:
			setContentView(R.layout.product_eight);
			setTitle(R.string.product_title8);
			seekBarTextImage = (ImageView) findViewById(R.id.image_textodeslice8);
			vsk = (VerticalSeekBar) findViewById(R.id.seekbar8);
			seekBarSlave = (TextView) findViewById(R.id.seekBarSlave8);
			yourBid = (TextView) findViewById(R.id.offer8);
			precioInicial = (TextView) findViewById(R.id.initial_price_8);
			ofertante = (TextView) findViewById(R.id.bidder_8);
			precioActual = (TextView) findViewById(R.id.price_8);
			activeProduct = 8;
			break;
		case 9:
			setContentView(R.layout.product_nine);
			setTitle(R.string.product_title9);
			seekBarTextImage = (ImageView) findViewById(R.id.image_textodeslice9);
			vsk = (VerticalSeekBar) findViewById(R.id.seekbar9);
			seekBarSlave = (TextView) findViewById(R.id.seekBarSlave9);
			yourBid = (TextView) findViewById(R.id.offer9);
			precioInicial = (TextView) findViewById(R.id.initial_price_9);
			ofertante = (TextView) findViewById(R.id.bidder_9);
			precioActual = (TextView) findViewById(R.id.price_9);
			activeProduct = 9;
			break;
		case 10:
			setContentView(R.layout.product_ten);
			setTitle(R.string.product_title10);
			seekBarTextImage = (ImageView) findViewById(R.id.image_textodeslice9);
			vsk = (VerticalSeekBar) findViewById(R.id.seekbar9);
			seekBarSlave = (TextView) findViewById(R.id.seekBarSlave9);
			yourBid = (TextView) findViewById(R.id.offer9);
			precioInicial = (TextView) findViewById(R.id.initial_price_9);
			ofertante = (TextView) findViewById(R.id.bidder_9);
			precioActual = (TextView) findViewById(R.id.price_9);
			activeProduct = 10;
			break;

		case 11:
			setContentView(R.layout.product_eleven);
			setTitle(R.string.product_title11);
			seekBarTextImage = (ImageView) findViewById(R.id.image_textodeslice9);
			vsk = (VerticalSeekBar) findViewById(R.id.seekbar9);
			seekBarSlave = (TextView) findViewById(R.id.seekBarSlave9);
			yourBid = (TextView) findViewById(R.id.offer9);
			precioInicial = (TextView) findViewById(R.id.initial_price_9);
			ofertante = (TextView) findViewById(R.id.bidder_9);
			precioActual = (TextView) findViewById(R.id.price_9);
			activeProduct = 11;
			break;

		case 12:
			setContentView(R.layout.product_twelve);
			setTitle(R.string.product_title12);
			seekBarTextImage = (ImageView) findViewById(R.id.image_textodeslice9);
			vsk = (VerticalSeekBar) findViewById(R.id.seekbar9);
			seekBarSlave = (TextView) findViewById(R.id.seekBarSlave9);
			yourBid = (TextView) findViewById(R.id.offer9);
			precioInicial = (TextView) findViewById(R.id.initial_price_9);
			ofertante = (TextView) findViewById(R.id.bidder_9);
			precioActual = (TextView) findViewById(R.id.price_9);
			activeProduct = 12;
			break;
		case 13:
			setContentView(R.layout.product_thirteen);
			setTitle(R.string.product_title13);
			seekBarTextImage = (ImageView) findViewById(R.id.image_textodeslice9);
			vsk = (VerticalSeekBar) findViewById(R.id.seekbar9);
			seekBarSlave = (TextView) findViewById(R.id.seekBarSlave9);
			yourBid = (TextView) findViewById(R.id.offer9);
			precioInicial = (TextView) findViewById(R.id.initial_price_9);
			ofertante = (TextView) findViewById(R.id.bidder_9);
			precioActual = (TextView) findViewById(R.id.price_9);
			activeProduct = 13;
			break;
		case 14:
			setContentView(R.layout.product_fourteen);
			setTitle(R.string.product_title14);
			seekBarTextImage = (ImageView) findViewById(R.id.image_textodeslice9);
			vsk = (VerticalSeekBar) findViewById(R.id.seekbar9);
			seekBarSlave = (TextView) findViewById(R.id.seekBarSlave9);
			yourBid = (TextView) findViewById(R.id.offer9);
			precioInicial = (TextView) findViewById(R.id.initial_price_9);
			ofertante = (TextView) findViewById(R.id.bidder_9);
			precioActual = (TextView) findViewById(R.id.price_9);
			activeProduct = 14;
			break;
		case 15:
			setContentView(R.layout.product_fifteen);
			setTitle(R.string.product_title15);
			seekBarTextImage = (ImageView) findViewById(R.id.image_textodeslice9);
			vsk = (VerticalSeekBar) findViewById(R.id.seekbar9);
			seekBarSlave = (TextView) findViewById(R.id.seekBarSlave9);
			yourBid = (TextView) findViewById(R.id.offer9);
			precioInicial = (TextView) findViewById(R.id.initial_price_9);
			ofertante = (TextView) findViewById(R.id.bidder_9);
			precioActual = (TextView) findViewById(R.id.price_9);
			activeProduct = 15;
			break;
		case 16:
			setContentView(R.layout.product_sixteen);
			setTitle(R.string.product_title16);
			seekBarTextImage = (ImageView) findViewById(R.id.image_textodeslice9);
			vsk = (VerticalSeekBar) findViewById(R.id.seekbar9);
			seekBarSlave = (TextView) findViewById(R.id.seekBarSlave9);
			yourBid = (TextView) findViewById(R.id.offer9);
			precioInicial = (TextView) findViewById(R.id.initial_price_9);
			ofertante = (TextView) findViewById(R.id.bidder_9);
			precioActual = (TextView) findViewById(R.id.price_9);
			activeProduct = 16;
			break;
		}
		actualPrice = initialPrice(activeProduct);
		precioInicial.setText(setInitialPrice(activeProduct) + " USD");
		vsk.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onStopTrackingTouch(VerticalSeekBar seekBar) {
			}

			public void onStartTrackingTouch(VerticalSeekBar seekBar) {
			}

			public void onProgressChanged(VerticalSeekBar seekBar,
					int progress, boolean fromUser) {
				updateSeekBarSlave(progress);
			}
		});
		if (x == 1) {
			bidderName = "Nadie ha ofertado";
			precioActual.setText(setInitialPrice(activeProduct) + " USD");
			ofertante.setText(bidderName);
		}
		if (x == 2) {
			sendProdNumToService(activeProduct);
		}
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		doBindService();
		tmr.schedule(new TimerTask() {

			@Override
			public void run() {
				sendInts();
			}
		}, 500);
	}

	@Override
	protected void onStart() {
		super.onStart();
		ActionBar actionBar = this.getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	protected void onStop() {
		sendAliveToService(UpdateService.DEAD);
		doUnbindService();
		super.onStop();
	}

	/**
	 * Inflating the Action Bar menu with MenuInflater instance and using the
	 * method inflate.
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.product_menu_items, menu);
		return true;
	}

	/**
	 * This is a method binded with the Button Onclick property
	 */
	public void openBiddingDialog(View v) {
		if (userBid <= actualPrice) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Por favor, haga una oferta mayor a la actual")
					.setCancelable(false)
					.setNegativeButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			AlertDialog alertDialog = builder.create();
			alertDialog.show();
			return;
		}
		Intent intent = new Intent(this, BiddingDialog.class);
		intent.putExtra("Bid", userBid);
		intent.putExtra("Product", activeProduct);
		startUI(2, activeProduct);
		userBid = 0;
		startActivity(intent);
	}

	/**
	 * Listener for the ActionBar Menu.
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, HomeActivity.class);
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

	public String darFormato(String s) {
		String cleanString = s.toString().replaceAll("[$,.]", "");
		double parsed = Double.parseDouble(cleanString);
		String formated = NumberFormat.getCurrencyInstance().format(
				(parsed / 100));
		return formated;
	}

	public void showProductInfo(View v) {
		String title = null, contents = null;
		switch (activeProduct) {
		case (1):
			title = (String) getText(R.string.product_title1);
			contents = (String) getText(R.string.product_abstract1);
			break;
		case (2):
			title = (String) getText(R.string.product_title2);
			contents = (String) getText(R.string.product_abstract2);
			break;
		case (3):
			title = (String) getText(R.string.product_title3);
			contents = (String) getText(R.string.product_abstract3);
			break;
		case (4):
			title = (String) getText(R.string.product_title4);
			contents = (String) getText(R.string.product_abstract4);
			break;
		case (5):
			title = (String) getText(R.string.product_title5);
			contents = (String) getText(R.string.product_abstract5);
			break;
		case (6):
			title = (String) getText(R.string.product_title6);
			contents = (String) getText(R.string.product_abstract6);
			break;
		case (7):
			title = (String) getText(R.string.product_title7);
			contents = (String) getText(R.string.product_abstract7);
			break;
		case (8):
			title = (String) getText(R.string.product_title8);
			contents = (String) getText(R.string.product_abstract8);
			break;
		case (9):
			title = (String) getText(R.string.product_title9);
			contents = (String) getText(R.string.product_abstract9);
			break;
		case (10):
			title = (String) getText(R.string.product_title10);
			contents = (String) getText(R.string.product_abstract10);
			break;
		case (11):
			title = (String) getText(R.string.product_title11);
			contents = (String) getText(R.string.product_abstract11);
			break;
		case (12):
			title = (String) getText(R.string.product_title12);
			contents = (String) getText(R.string.product_abstract12);
			break;
		case (13):
			title = (String) getText(R.string.product_title13);
			contents = (String) getText(R.string.product_abstract13);
			break;
		case (14):
			title = (String) getText(R.string.product_title14);
		contents = (String) getText(R.string.product_abstract14);
			break;
		case (15):
			title = (String) getText(R.string.product_title15);
			contents = (String) getText(R.string.product_abstract15);
			break;
		case (16):
			title = (String) getText(R.string.product_title16);
			contents = (String) getText(R.string.product_abstract16);
			break;
		}
		Dialog.showMoreInfo(this, title, contents);
	}

	public String setInitialPrice(int product) {
		switch (product) {
		case 1:
			return darFormato("7000.00");
		case 2:
			return darFormato("1100.00");
		case 3:
			return darFormato("3500.00");
		case 4:
			return darFormato("1100.00");
		case 5:
			return darFormato("2550.00");
		case 6:
			return darFormato("10000.00");
		case 7:
			return darFormato("9000.00");
		case 8:
			return darFormato("11680.00");
		case 9:
			return darFormato("10220.00");
		case 10:
			return darFormato("8500.00");
		case 11:
			return darFormato("1230.00");
		case 12:
			return darFormato("900.00");
		case 13:
			return darFormato("2100.00");
		case 14:
			return darFormato("125500.00");
		case 15:
			return darFormato("1500.00");
		case 16:
			return darFormato("500.00");
		}
		return "???";
	}

	public long initialPrice(int product) {
		switch (product) {
		case 1:
			return 7855;
		case 2:
			return 8500;
		case 3:
			return 2500;
		case 4:
			return 12000;
		case 5:
			return 615;
		case 6:
			return 46531;
		case 7:
			return 1425;
		case 8:
			return 3600;
		case 9:
			return 3980;
		}
		return 0;
	}

	public void updateSeekBarSlave(int progress) {
		int bid = MAXIMUM_BID_AMOUNT * progress;
		String sBid = darFormato(Integer.toString(bid));
		seekBarSlave.setText("+" + sBid + " USD");
		seekBarSlave.setTextSize(20);
		seekBarTextImage.setVisibility(8);

		int textOffset = (int) (5.35 * progress);
		textOffset = 598 - textOffset;
		Resources r = getResources();
		float topPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				textOffset, r.getDisplayMetrics());
		float rightPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				6, r.getDisplayMetrics());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(0, (int) topPx, (int) rightPx, 0);
		seekBarSlave.setLayoutParams(lp);

		userBid = actualPrice + (bid / 100);
		yourBid.setText(darFormato(Long.toString(userBid * 100)) + " USD");
	}

	void doBindService() {
		bindService(new Intent(this, UpdateService.class), mConnection,
				Context.BIND_AUTO_CREATE);
		mIsBound = true;
	}

	void doUnbindService() {
		if (mIsBound) {
			if (mService != null) {
				try {
					Message msg = Message.obtain(null,
							UpdateService.MSG_UNREGISTER_CLIENT);
					msg.replyTo = mMessenger;
					mService.send(msg);
				} catch (RemoteException e) {
				}
			}
			unbindService(mConnection);
			mIsBound = false;
		}
	}

	private void sendAliveToService(int intvaluetosend) {
		if (mService != null) {
			try {
				Bundle b = new Bundle();
				b.putInt("Alive", intvaluetosend);
				Message msg = Message.obtain(null,
						UpdateService.MSG_SET_INT_VALUE);
				msg.setData(b);
				mService.send(msg);
			} catch (RemoteException e) {
			}
		}
	}

	private void sendProdNumToService(int intvaluetosend) {
		if (mService != null) {
			try {
				Bundle b = new Bundle();
				b.putInt("Product", intvaluetosend);
				Message msg = Message.obtain(null,
						UpdateService.MSG_SET_INT_VALUE);
				msg.setData(b);
				mService.send(msg);
			} catch (RemoteException e) {
			}
		}
	}

	public void sendInts() {
		sendAliveToService(UpdateService.ALIVE);
		sendProdNumToService(activeProduct);
	}
}
