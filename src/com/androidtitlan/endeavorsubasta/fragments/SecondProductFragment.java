package com.androidtitlan.endeavorsubasta.fragments;

import com.androidtitlan.endeavorsubasta.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SecondProductFragment extends Fragment {

	public static SecondProductFragment newInstance(String title) {
		SecondProductFragment first = new SecondProductFragment();
		Bundle bundle = new Bundle();
		bundle.putString("title", title);
		first.setArguments(bundle);
		return first;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View myFragmentView = inflater.inflate(R.layout.second_fragment,
				container, false);

		return myFragmentView;
	}

}
