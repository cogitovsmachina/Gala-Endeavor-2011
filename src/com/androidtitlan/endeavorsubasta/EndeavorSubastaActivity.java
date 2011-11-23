package com.androidtitlan.endeavorsubasta;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.androidtitlan.endeavorsubasta.ui.Dialog;


public class EndeavorSubastaActivity extends Activity {

	private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;
	private Animation slideLeftIn;
	private Animation slideLeftOut;
	private Animation slideRightIn;
    private Animation slideRightOut;
    private ViewFlipper viewFlipper;
    

    class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;
                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                	viewFlipper.setInAnimation(slideLeftIn);
                    viewFlipper.setOutAnimation(slideLeftOut);
                	viewFlipper.showNext();
                }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                	viewFlipper.setInAnimation(slideRightIn);
                    viewFlipper.setOutAnimation(slideRightOut);
                	viewFlipper.showPrevious();
                }
            } catch (Exception e) {
                // nothing
            }
            return false;
        }
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event))
	        return true;
	    else
	    	return false;
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
      
      viewFlipper = (ViewFlipper)findViewById(R.id.flipper);
      slideLeftIn = AnimationUtils.loadAnimation(this, R.anim.slide_left_in);
      slideLeftOut = AnimationUtils.loadAnimation(this, R.anim.slide_left_out);
      slideRightIn = AnimationUtils.loadAnimation(this, R.anim.slide_right_in);
      slideRightOut = AnimationUtils.loadAnimation(this, R.anim.slide_right_out);
      
      ImageView previous = (ImageView) findViewById(R.id.previous);
      ImageView next = (ImageView) findViewById(R.id.next);
      
      gestureDetector = new GestureDetector(new MyGestureDetector());
      gestureListener = new View.OnTouchListener() {
          public boolean onTouch(View v, MotionEvent event) {
              if (gestureDetector.onTouchEvent(event)) {
                  return true;
              }
              return false;
          }
      };
      
      previous.setOnClickListener(new View.OnClickListener() {
//		@Override
		public void onClick(View v) {
			viewFlipper.setInAnimation(slideRightIn);
            viewFlipper.setOutAnimation(slideRightOut);
        	viewFlipper.showPrevious();
		}
	});
      next.setOnClickListener(new View.OnClickListener() {
//		@Override
		public void onClick(View v) {
			viewFlipper.setInAnimation(slideLeftIn);
            viewFlipper.setOutAnimation(slideLeftOut);
        	viewFlipper.showNext();
		}
	});
    }
	
	@Override
	protected void onStart() {
	    super.onStart();
	    ActionBar actionBar = this.getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	}
	    
    /**
     * This is a method binded with the Button Onclick property
     */
    public void goToProductActivity(View v){
    	startActivity(new Intent(this, ProductActivity.class));
    }
    
    /**
     * Inflating the Action Bar menu with MenuInflater instance and using the method inflate.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu_items, menu);
        return true;
    }
    
    
    /**
     * Listener for the ActionBar Menu.
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_help:
            	Dialog.showHelpMessage(this);
            	return true;
            case R.id.menu_about:
            	Dialog.showAboutMessage(this);
            	return true;
        }
        return false;
    }
    
    /**
     * Methods for each product, binded on main.xml
     */
    public void goToProductOne(View v){
    	Intent i = new Intent(this, ProductActivity.class);
    	i.putExtra("Product", 1);
    	startActivity(i);
    }
    public void goToProductTwo(View v){
    	Intent i = new Intent(this, ProductActivity.class);
    	i.putExtra("Product", 2);
    	startActivity(i);
    }
    public void goToProductThree(View v){
    	Intent i = new Intent(this, ProductActivity.class);
    	i.putExtra("Product", 3);
    	startActivity(i);
    }
    public void goToProductFour(View v){
    	Intent i = new Intent(this, ProductActivity.class);
    	i.putExtra("Product", 4);
    	startActivity(i);
    }
    public void goToproductFive(View v){
    	Intent i = new Intent(this, ProductActivity.class);
    	i.putExtra("Product", 5);
    	startActivity(i);
    }
    public void goToproductSix(View v){
    	Intent i = new Intent(this, ProductActivity.class);
    	i.putExtra("Product", 6);
    	startActivity(i);
    }
    public void goToProductSeven(View v){
    	Intent i = new Intent(this, ProductActivity.class);
    	i.putExtra("Product", 7);
    	startActivity(i);
    }
    public void goToProductEight(View v){
    	Intent i = new Intent(this, ProductActivity.class);
    	i.putExtra("Product", 8);
    	startActivity(i);
    }
    public void goToProductNine(View v){
    	Intent i = new Intent(this, ProductActivity.class);
    	i.putExtra("Product", 9);
    	startActivity(i);
    }
   
}