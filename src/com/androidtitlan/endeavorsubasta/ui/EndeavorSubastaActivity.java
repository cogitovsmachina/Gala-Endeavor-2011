package com.androidtitlan.endeavorsubasta.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.androidtitlan.endeavorsubasta.ProductActivity;
import com.androidtitlan.endeavorsubasta.R;
import com.androidtitlan.endeavorsubasta.R.id;
import com.androidtitlan.endeavorsubasta.R.layout;
import com.androidtitlan.endeavorsubasta.R.menu;
import com.androidtitlan.endeavorsubasta.adapter.ComplexPagerViewAdapter;
import com.androidtitlan.endeavorsubasta.fragments.FirstProductFragment;
import com.androidtitlan.endeavorsubasta.fragments.SecondProductFragment;
import com.androidtitlan.endeavorsubasta.fragments.ThirdProductFragment;

public class EndeavorSubastaActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pager);

		ComplexPagerViewAdapter adapter = new ComplexPagerViewAdapter(this,
				createFragments());
		ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(0);
	}

	private List<Fragment> createFragments() {
		List<Fragment> list = new ArrayList<Fragment>();
		list.add(Fragment.instantiate(this,
				FirstProductFragment.class.getName()));
		list.add(Fragment.instantiate(this,
				SecondProductFragment.class.getName()));
		list.add(Fragment.instantiate(this,
				ThirdProductFragment.class.getName()));
		return list;
	}

	@Override
	protected void onStart() {
		super.onStart();
		ActionBar ab = getActionBar();
		ab.setDisplayUseLogoEnabled(true);
		setTitle("Gala Endeavor 2012");
	}

	/**
	 * This method opens an activity based in what ImageView you are touching
	 * 
	 */
	public void goToProductActivity(View v) {
		// startActivity(new Intent(this, ProductActivity.class));
		if (v.getId() == R.id.productOne) {
			Log.e("***", "It works");
			Intent i = new Intent(this, ProductActivity.class);
			i.putExtra("Product", 1);
			startActivity(i);
		}
		if (v.getId() == R.id.productTwo) {
			Intent i = new Intent(this, ProductActivity.class);
			i.putExtra("Product", 2);
			startActivity(i);
		}
		if (v.getId() == R.id.productThree) {
			Intent i = new Intent(this, ProductActivity.class);
			i.putExtra("Product", 3);
			startActivity(i);
		}
		if (v.getId() == R.id.productFour) {
			Intent i = new Intent(this, ProductActivity.class);
			i.putExtra("Product", 4);
			startActivity(i);
		}
		if (v.getId() == R.id.productFive) {
			Intent i = new Intent(this, ProductActivity.class);
			i.putExtra("Product", 5);
			startActivity(i);
		}
		if (v.getId() == R.id.productSix) {
			Intent i = new Intent(this, ProductActivity.class);
			i.putExtra("Product", 6);
			startActivity(i);
		}
		if (v.getId() == R.id.productSeven) {
			Intent i = new Intent(this, ProductActivity.class);
			i.putExtra("Product", 7);
			startActivity(i);
		}
		if (v.getId() == R.id.productEight) {
			Intent i = new Intent(this, ProductActivity.class);
			i.putExtra("Product", 8);
			startActivity(i);
		}
		if (v.getId() == R.id.productNine) {
			Intent i = new Intent(this, ProductActivity.class);
			i.putExtra("Product", 9);
			startActivity(i);
		}

	}

	/**
	 * Inflating the Action Bar menu with MenuInflater instance and using the
	 * method inflate.
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

}