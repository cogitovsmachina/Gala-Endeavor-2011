package com.androidtitlan.endeavorsubasta.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.androidtitlan.endeavorsubasta.R;

public class FirstProductFragment extends Fragment {
	GridView gridView;

	public static FirstProductFragment newInstance(String title) {
		FirstProductFragment first = new FirstProductFragment();
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

		View myFragmentView = inflater.inflate(R.layout.first_fragment,
				container, false);

		return myFragmentView;
	}

}
