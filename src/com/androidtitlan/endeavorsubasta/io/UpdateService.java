package com.androidtitlan.endeavorsubasta.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.androidtitlan.galaendeavor.pojo.Productos;
import com.androidtitlan.galaendeavor.pojo.PullProductos;

public class UpdateService extends Service {
	public static final int MSG_REGISTER_CLIENT = 1;
    public static final int MSG_UNREGISTER_CLIENT = 2;
    public static final int MSG_SET_INT_VALUE = 3;
    public static final int MSG_SET_STRING_VALUE = 4;
    public static final int MSG_SET_BOOL_VALUE = 5;
    private static boolean isRunning = false;
    public static final int ALIVE=1;
    public static final int DEAD=-1;
    private Timer tmr=new Timer();
    
    private PullProductos catalogo;
    private int isAlive=0;
    private int activeProduct=0;

    ArrayList<Messenger> mClients = new ArrayList<Messenger>();
    final Messenger mMessenger = new Messenger(new IncomingHandler());
	@Override
	public IBinder onBind(Intent intent) {
		return mMessenger.getBinder();
    }
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case MSG_REGISTER_CLIENT:
                mClients.add(msg.replyTo);
                break;
            case MSG_UNREGISTER_CLIENT:
                mClients.remove(msg.replyTo);
                break;
            case MSG_SET_INT_VALUE:
            	isAlive=msg.getData().getInt("Alive");
            	if(isAlive!=0){
            		if(isAlive==DEAD){
            			unsuscribe();
            		}
            		if(isAlive==ALIVE){
            			suscribe();
            		}
            		isAlive=0;
            		break;
            	}
            	activeProduct=msg.getData().getInt("Product");
                break;
            default:
                super.handleMessage(msg);
            }
        }
    }
    
    private void sendString(String tosend) {
    	for (int i=mClients.size()-1; i>=0; i--) {
            try {
            	Bundle b = new Bundle();
                b.putString("Bid", tosend);
                Message msg = Message.obtain(null, MSG_SET_STRING_VALUE);
                msg.setData(b);
                mClients.get(i).send(msg);
            } catch (RemoteException e) {
                mClients.remove(i);
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isRunning = true;
    }
    public static boolean isRunning()
    {
        return isRunning;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
    
    private void update(){
    	try {
			catalogo=WebServices.pullProductos();
			
			if(catalogo.alive==false){
				sendBool(true);
				return;
			}
			
			Log.i("Pull", "Se hizo pull de: "+Integer.toString(activeProduct));
			Productos p=null;
			for(int i=0;i<9;i++){
				Productos temp = catalogo.productos.get(i);
				if(temp.id_producto==activeProduct){
					p=temp;
					break;
				}					
			}
			Log.d("Producto: "+Integer.toString(activeProduct), p.precio+"   "+p.usuario);
			if(p.usuario!=null){
				String toSend=p.precio+"%$%"+p.usuario;
				sendString(toSend);
				Log.i("Update", "Just Updated Product: "+Integer.toString(activeProduct));
				return;
			}
			Log.e("Woot", "Producto = Null");
		} catch (IOException e) {
			Log.e("Shit happened", "Error: "+e.toString());
			e.printStackTrace();
		}
    }
    
    private void sendBool(boolean bool) {
    	for (int i=mClients.size()-1; i>=0; i--) {
            try {
            	Bundle b = new Bundle();
                b.putBoolean("End", bool);
                Message msg = Message.obtain(null, MSG_SET_BOOL_VALUE);
                msg.setData(b);
                mClients.get(i).send(msg);
            } catch (RemoteException e) {
                mClients.remove(i);
            }
        }
	}

	private void suscribe(){
    	tmr.scheduleAtFixedRate(new TimerTask(){ public void run() {update();}}, 0, 1000);
    }
    
    private void unsuscribe(){
    	tmr.cancel();
    	tmr=new Timer();
    }
}
