package com.file.peoplefilesystem;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InformationFragment extends Fragment {
	PeopleInformation information;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		information = new PeopleInformation("Ð¡Ã÷", "ºÜÉÆÁ¼");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_layout, container);
		((TextView)view.findViewById(R.id.name)).setText(information.name);
		((TextView)view.findViewById(R.id.desc)).setText(information.desc);
		return view;
	}
}
