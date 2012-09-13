package com.androidtitlan.endeavorsubasta;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ComplexPagerViewAdapter extends FragmentStatePagerAdapter {

	private List<Fragment> fragments;

	public ComplexPagerViewAdapter(FragmentActivity activity,
			List<Fragment> fragments) {
		super(activity.getSupportFragmentManager());
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int i) {
		return fragments.get(i);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

}
