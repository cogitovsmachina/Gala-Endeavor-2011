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
import android.widget.Toast;

import com.androidtitlan.endeavorsubasta.io.UpdateService;
import com.androidtitlan.endeavorsubasta.ui.Dialog;
import com.androidtitlan.endeavorsubasta.ui.VerticalSeekBar;
import com.androidtitlan.endeavorsubasta.ui.VerticalSeekBar.OnSeekBarChangeListener;

public class ProductActivity extends Activity {
	
	/**
	 * TextViews dinamicos de Nombre del ultimo bidder y precio actual
	 */
	TextView ofertante=null;
	TextView precioActual=null;
	TextView precioInicial=null;
	VerticalSeekBar vsk = null;
	TextView seekBarSlave = null;
	TextView yourBid = null;
	ImageView seekBarTextImage = null;
	long actualPrice, userBid;
	String bidderName;
	
	private static final int MAXIMUM_BID_AMOUNT = 10000;
	private int activeProduct;
	Messenger mService = null;
    boolean mIsBound;
    final Messenger mMessenger = new Messenger(new IncomingHandler());
    Timer tmr=new Timer();
    
    String fromService;
    
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case UpdateService.MSG_SET_STRING_VALUE:
                fromService = msg.getData().getString("Bid");
                StringTokenizer tk = new StringTokenizer(fromService, "%$%");
        		String sActualPrice=tk.nextToken();
        		bidderName=tk.nextToken();
        		long tempActualPrice = (long)Float.parseFloat(sActualPrice);
        		if(tempActualPrice!=0){
        			actualPrice=tempActualPrice;
	                precioActual.setText(darFormato(sActualPrice)+" USD");
	        		ofertante.setText(bidderName);
        		}
                break;
            case UpdateService.MSG_SET_BOOL_VALUE:
            	setContentView(R.layout.end_layout);
            default:
                super.handleMessage(msg);
            }
        }
    }
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mService = new Messenger(service);
            try {
                Message msg = Message.obtain(null, UpdateService.MSG_REGISTER_CLIENT);
                msg.replyTo = mMessenger;
                mService.send(msg);
            } catch (RemoteException e) {
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            mService = null;
        }
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		doBindService();
		tmr.schedule(new TimerTask(){ public void run(){sendInts();}}, 500);
		/**
		 * Inflado especifico del layout para cada producto
		 */
		Bundle extras = getIntent().getExtras();
		int selectedProduct = extras.getInt("Product");
		switch(selectedProduct){
		case 1:
			setContentView(R.layout.product_one);
			setTitle("El Grande Reverso");
			seekBarTextImage=(ImageView)findViewById(R.id.image_textodeslice1);
			vsk=(VerticalSeekBar)findViewById(R.id.seekbar1);
			seekBarSlave=(TextView)findViewById(R.id.seekBarSlave1);
			yourBid=(TextView)findViewById(R.id.offer1);
			precioInicial=(TextView)findViewById(R.id.initial_price_1);
			ofertante=(TextView)findViewById(R.id.bidder_1);
			precioActual=(TextView)findViewById(R.id.price_1);
			activeProduct=1;
			break;
		case 2:
			setContentView(R.layout.product_two);
			setTitle("Collar Oro Rojo y Oro Blanco");
			seekBarTextImage=(ImageView)findViewById(R.id.image_textodeslice2);
			vsk=(VerticalSeekBar)findViewById(R.id.seekbar2);
			seekBarSlave=(TextView)findViewById(R.id.seekBarSlave2);
			yourBid=(TextView)findViewById(R.id.offer2);
			precioInicial=(TextView)findViewById(R.id.initial_price_2);
			ofertante=(TextView)findViewById(R.id.bidder_2);
			precioActual=(TextView)findViewById(R.id.price_2);
			activeProduct=2;
			break;
		case 3:
			setContentView(R.layout.product_three);
			setTitle("Collar Infinito Rosé");
			seekBarTextImage=(ImageView)findViewById(R.id.image_textodeslice3);
			vsk=(VerticalSeekBar)findViewById(R.id.seekbar3);
			seekBarSlave=(TextView)findViewById(R.id.seekBarSlave3);
			yourBid=(TextView)findViewById(R.id.offer3);
			precioInicial=(TextView)findViewById(R.id.initial_price_3);
			ofertante=(TextView)findViewById(R.id.bidder_3);
			precioActual=(TextView)findViewById(R.id.price_3);
			activeProduct=3;
			break;
		case 4:
			setContentView(R.layout.product_four);
			setTitle("Margie");
			seekBarTextImage=(ImageView)findViewById(R.id.image_textodeslice4);
			vsk=(VerticalSeekBar)findViewById(R.id.seekbar4);
			seekBarSlave=(TextView)findViewById(R.id.seekBarSlave4);
			yourBid=(TextView)findViewById(R.id.offer4);
			precioInicial=(TextView)findViewById(R.id.initial_price_4);
			ofertante=(TextView)findViewById(R.id.bidder_4);
			precioActual=(TextView)findViewById(R.id.price_4);
			activeProduct=4;
			break;
		case 5:
			setContentView(R.layout.product_five);
			seekBarTextImage=(ImageView)findViewById(R.id.image_textodeslice5);
			setTitle("Johnnie Walker Blue Label: King George V Edition");
			vsk=(VerticalSeekBar)findViewById(R.id.seekbar5);
			seekBarSlave=(TextView)findViewById(R.id.seekBarSlave5);
			yourBid=(TextView)findViewById(R.id.offer5);
			precioInicial=(TextView)findViewById(R.id.initial_price_5);
			ofertante=(TextView)findViewById(R.id.bidder_5);
			precioActual=(TextView)findViewById(R.id.price_5);
			activeProduct=5;
			break;
		case 6:
			setContentView(R.layout.product_six);
			setTitle("528iA Lujo (Automático)");
			seekBarTextImage=(ImageView)findViewById(R.id.image_textodeslice6);
			vsk=(VerticalSeekBar)findViewById(R.id.seekbar6);
			seekBarSlave=(TextView)findViewById(R.id.seekBarSlave6);
			yourBid=(TextView)findViewById(R.id.offer6);
			precioInicial=(TextView)findViewById(R.id.initial_price_6);
			ofertante=(TextView)findViewById(R.id.bidder_6);
			precioActual=(TextView)findViewById(R.id.price_6);
			activeProduct=6;
			break;
		case 7:
			setContentView(R.layout.product_seven);
			setTitle("Subasta de Oro");
			seekBarTextImage=(ImageView)findViewById(R.id.image_textodeslice7);
			vsk=(VerticalSeekBar)findViewById(R.id.seekbar7);
			seekBarSlave=(TextView)findViewById(R.id.seekBarSlave7);
			yourBid=(TextView)findViewById(R.id.offer7);
			precioInicial=(TextView)findViewById(R.id.initial_price_7);
			ofertante=(TextView)findViewById(R.id.bidder_7);
			precioActual=(TextView)findViewById(R.id.price_7);
			activeProduct=7;
			break;
		case 8:
			setContentView(R.layout.product_eight);
			setTitle("Subasta de Oro");
			seekBarTextImage=(ImageView)findViewById(R.id.image_textodeslice8);
			vsk=(VerticalSeekBar)findViewById(R.id.seekbar8);
			seekBarSlave=(TextView)findViewById(R.id.seekBarSlave8);
			yourBid=(TextView)findViewById(R.id.offer8);
			precioInicial=(TextView)findViewById(R.id.initial_price_8);
			ofertante=(TextView)findViewById(R.id.bidder_8);
			precioActual=(TextView)findViewById(R.id.price_8);
			activeProduct=8;
			break;
		case 9:
			setContentView(R.layout.product_nine);
			setTitle("Subasta de Oro");
			seekBarTextImage=(ImageView)findViewById(R.id.image_textodeslice9);
			vsk=(VerticalSeekBar)findViewById(R.id.seekbar9);
			seekBarSlave=(TextView)findViewById(R.id.seekBarSlave9);
			yourBid=(TextView)findViewById(R.id.offer9);
			precioInicial=(TextView)findViewById(R.id.initial_price_9);
			ofertante=(TextView)findViewById(R.id.bidder_9);
			precioActual=(TextView)findViewById(R.id.price_9);
			activeProduct=9;
			break;
		}
		actualPrice = initialPrice(activeProduct);
        precioInicial.setText(setInitialPrice(activeProduct)+" USD");
        precioActual.setText(setInitialPrice(activeProduct)+" USD");
        bidderName="Nadie ha ofertado";
		ofertante.setText(bidderName);
		vsk.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
			
			public void onStopTrackingTouch(VerticalSeekBar seekBar) {				
			}
			
			public void onStartTrackingTouch(VerticalSeekBar seekBar) {				
			}
			
			public void onProgressChanged(VerticalSeekBar seekBar, int progress, boolean fromUser) {
				updateSeekBarSlave(progress);
			}
		});
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
	protected void onStop(){
		sendAliveToService(UpdateService.DEAD);
		doUnbindService();
		super.onStop();
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
    	if(userBid<=actualPrice){
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Por favor, haga una oferta mayor a la actual")
			.setCancelable(false)
			.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
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
    	startActivity(intent);    
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
    
    public String darFormato(String s){
    	String cleanString = s.toString().replaceAll("[$,.]", "");
        double parsed = Double.parseDouble(cleanString);
        String formated = NumberFormat.getCurrencyInstance().format((parsed/100));
        return formated;
    }
    
    public void moreInfoOnProduct(View v){
    	String title=null, contents=null;
    	switch(activeProduct){
    	case(1):
    		title="El Grande Reverso";
    		contents="El Grande Reverso luce una edici�n in�dita de 30 x 48.5 mm y se caracteriza por su esfera plateada de espl�ndida sobriedad ornamentada con d�gitos tradicionales del estilo Art Deco y un doble acabado: mientras el rect�ngulo del centro presenta un motivo guilloqueado con Clous de Paris, la parte exterior enarbola un acabado satinado. Ésta delicada alternancia ornamental se repite en el contador del segundero pequeño situado a las 6 horas. El Grande Reverso está animado por el nuevo Calibre 976, visible a través del fondo de cristal de zafiro. Posee todas las características de los movimientos robustos y precisos desarrollados por la manufactura Jaeger-LeCoultre y está equipado con un volante de inercia variable que palpita con una frecuencia de 28,800 alternancias por hora.";
    		break;
    	case(2):
    		title="Collar Peyrelongue";
    		contents="Collar Oro Rojo y Blanco\n\nKilataje Oro: 18K\nKilataje Diamantes: 1.64 ct";
    		break;
    	case(3):
    		title="Collar Infinito Rosé";
    		contents="Collar de la Colección Infinito Elite Rosé\n\nPlata .925\nBaño de Oro Rosa de 22K\nZirconias montadas en Pavé";
    		break;
    	case(4):
    		title="Margie";
    		contents="La Margie es una motocicleta tipo Bobber de cuadro rígido, con suspensión frontal tipo Springer, asiento en piel terminado a mano, Motor 125 cc de 4 tiempos, de 4 velocidades y 7.5 caballos de fuerza y una velocidad de más de 100km/hr.\nLas Margies son motocicletas urbanas con cambio semiautomático en tipo Shifter y Jockey haciéndola totalmente clásica, esto aunado a una suspensión de resorte trasero fabricado en acero pulido y cromado montado en un asiento de inyección de poliuretano con base de gel para reducir el impacto en la columna.\nCuenta con llanta delantera de 18 pulgadas con freno de disco y atrás llanta de 15 Pulgadas con freno de tambor.\nEncendido eléctrico y de patada, batería de seis celdas libre de mantenimiento.";
    		break;
    	case(5):
    		title="Johnnie Walker Blue Label";
    		contents="Johnnie Walker Blue Label: King George V Edition, celebra la primera Orden Real dada a la compañia John Walker and Sons Ltda., para proveer Scotch whisky a la Casa Real Británica, en 1934. Haciendo honor a su herencia, ésta exquisita mezcla ha sido hecha a mano utilizando las técnicas de la época, y usando whiskies de las destilerías que operaban durante el reinado de George V.\n\nCaracterísticas:\n-Olfato: un aroma inicial profundamente ahumado seguido de una dulce y afrutada sensación (a manzanas, peras, bananas) propia de las maltas; que desemboca en una complejidad rica en frutos secos y especiados (uvas, pasas, higos, canela).\n-Paladar: suave, con una rica y afrutada dulzura. Calienta el paladar con sutiles sabores a madera de sándalo y humo.\n-Sabor: fuerte, complejo, rico y suave.\n-Sensación final: cálido en la boca con un prolongado final rico en turba.\n-Con Agua: libera nuevos sabores más frescos y afrutados, y deja una sensación ahumada en el paladar.";
    		break;
    	case(6):
    		title="528iA de Lujo";
    		contents="Alpine White/Leather Dakota Cinnamon Brown\nChasis:C859775\n\nValor comercial: 56,300 USD\n*El tiempo de entrega del coche será de al menos 15 días.";
    		break;
    	case(7):
    		title="Subasta de Oro";
    		contents="Collar de Oro Tipo Cartier\nKilataje: 14K.\nPeso: 28.3 grs.";
    		break;
    	case(8):
    		title="Subasta de Oro";
    		contents="Collar de Oro Torsal\nKilataje: 10K. Peso: 116 grs.";
    		break;
    	case(9):
    		title="Subasta de Oro";
    		contents="Collar de Oro Barbado\nKilataje: 10K.\nPeso: 120 grs.";
    		break;
    	}
    	Dialog.showMoreInfo(this, title, contents);
    }
    
    public String setInitialPrice(int product){
    	switch(product){
    	case 1:
    		return darFormato("7855.00");
    	case 2:
    		return darFormato("8500.00");
    	case 3:
    		return darFormato("2500.00");
    	case 4:
    		return darFormato("12000.00");
    	case 5:
    		return darFormato("615.00");
    	case 6:
    		return darFormato("46531.00");
    	case 7:
    		return darFormato("1425.00");
    	case 8:
    		return darFormato("3600.00");
    	case 9:
    		return darFormato("3980.00");
    	}
    	return "???";
    }
    
    public long initialPrice(int product){
    	switch(product){
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
    
    public void updateSeekBarSlave(int progress){
    	int bid=MAXIMUM_BID_AMOUNT*progress;
    	String sBid = darFormato(Integer.toString(bid));
    	seekBarSlave.setText("+"+sBid);
    	seekBarTextImage.setVisibility(8);
    	
    	int textOffset=(int) (5.35*progress);
    	textOffset=598-textOffset;
    	Resources r = getResources();
    	float topPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, textOffset, r.getDisplayMetrics());
    	float rightPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, r.getDisplayMetrics());
    	LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	lp.setMargins(0, (int)topPx, (int)rightPx, 0);
    	seekBarSlave.setLayoutParams(lp);
    	
    	userBid=actualPrice+(bid/100);
    	yourBid.setText(darFormato(Long.toString(userBid*100))+" USD");
    }
    
    void doBindService() {
        bindService(new Intent(this, UpdateService.class), mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }
    void doUnbindService() {
        if (mIsBound) {
            if (mService != null) {
                try {
                    Message msg = Message.obtain(null, UpdateService.MSG_UNREGISTER_CLIENT);
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
                Message msg = Message.obtain(null, UpdateService.MSG_SET_INT_VALUE);
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
                Message msg = Message.obtain(null, UpdateService.MSG_SET_INT_VALUE);
                msg.setData(b);
                mService.send(msg);
            } catch (RemoteException e) {
            }
        }
    }
    
    public void sendInts(){
    	sendAliveToService(UpdateService.ALIVE);
    	sendProdNumToService(activeProduct);
    }
}
