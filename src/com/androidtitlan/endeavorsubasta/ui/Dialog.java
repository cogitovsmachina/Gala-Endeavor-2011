package com.androidtitlan.endeavorsubasta.ui;

import com.androidtitlan.endeavorsubasta.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Dialog {
	public Dialog(Context context){
	}

	/**
     * Show Help Dialog app.
     * @param activity Activity started from.
     */
	public static void showHelpMessage(final Activity activity){
		AlertDialog.Builder about = new AlertDialog.Builder(activity)
        .setTitle(R.string.help)
        .setIcon(android.R.drawable.ic_menu_help)
        .setMessage(R.string.help_text)
        .setPositiveButton(R.string.accept,
                    new android.content.DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });	
    	about.show();
	}

	 /*
     * Show About this app.
     * @param activity Activity started from.
     */
	public static void showAboutMessage(Activity activity) {
		AlertDialog.Builder about = new AlertDialog.Builder(activity)
        .setTitle(R.string.about_title)
        .setIcon(android.R.drawable.ic_dialog_info)
        .setMessage(R.string.about_text)
        .setPositiveButton(R.string.accept,
                    new android.content.DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
		about.show();		
	}
	
	public static void showMoreInfo(Activity activity, String title, String contents) {
		AlertDialog.Builder moar = new AlertDialog.Builder(activity)
        .setTitle(title)
        .setIcon(android.R.drawable.ic_dialog_info)
        .setMessage(contents)
        .setPositiveButton("Continuar",
                    new android.content.DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
		moar.show();		
	}
}
