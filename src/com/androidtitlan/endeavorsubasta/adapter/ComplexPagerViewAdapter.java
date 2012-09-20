package com.androidtitlan.endeavorsubasta.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ComplexPagerViewAdapter extends FragmentStatePagerAdapter {


	//TODO: Add a gradient background from resources 
	//without sacrifying performance 
	@Override
	public Object instantiateItem(ViewGroup arg0, int arg1) {
		return super.instantiateItem(arg0, arg1);
	}

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
