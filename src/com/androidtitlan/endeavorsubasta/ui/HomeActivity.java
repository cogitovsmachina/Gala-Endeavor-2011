package com.androidtitlan.endeavorsubasta.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.androidtitlan.endeavorsubasta.ProductDetailActivity;
import com.androidtitlan.endeavorsubasta.R;
import com.androidtitlan.endeavorsubasta.adapter.ComplexPagerViewAdapter;
import com.androidtitlan.endeavorsubasta.fragments.FirstProductFragment;
import com.androidtitlan.endeavorsubasta.fragments.SecondProductFragment;
import com.androidtitlan.endeavorsubasta.fragments.ThirdProductFragment;

public class HomeActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pager);
		setActionBarCustomBackground();

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

	/**
	 * Dirty hack to get ActionBar filled with a tile programatically
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
		Intent i = new Intent(this, ProductDetailActivity.class);
		switch (v.getId()) {
		case R.id.productOne:
			i.putExtra("Product", 1);
			startActivity(i);
			break;
		case R.id.productTwo:
			i.putExtra("Product", 2);
			startActivity(i);
			break;

		case R.id.productThree:
			i.putExtra("Product", 3);
			startActivity(i);
			break;

		case R.id.productFour:
			i.putExtra("Product", 4);
			startActivity(i);
			break;

		case R.id.productFive:
			i.putExtra("Product", 5);
			startActivity(i);
			break;

		case R.id.productSix:
			i.putExtra("Product", 6);
			startActivity(i);
			break;

		case R.id.productSeven:
			i.putExtra("Product", 7);
			startActivity(i);
			break;

		case R.id.productEight:
			i.putExtra("Product", 8);
			startActivity(i);
			break;

		case R.id.productNine:
			i.putExtra("Product", 9);
			startActivity(i);
			break;

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