package com.androidtitlan.endeavorsubasta.fragments;

import com.androidtitlan.endeavorsubasta.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ThirdProductFragment extends Fragment {

	public static ThirdProductFragment newInstance(String title) {
		ThirdProductFragment first = new ThirdProductFragment();
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

		View myFragmentView = inflater.inflate(R.layout.third_fragment,
				container, false);

		return myFragmentView;
	}

}
