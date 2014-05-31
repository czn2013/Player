package supertank.player.fragment;

import supertank.player.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class FancyFragment extends Fragment{

	ExpandableListView mFancyView;
	
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_fancy, container,false);
		mFancyView = (ExpandableListView) rootView.findViewById(R.id.fragment_fancy_elv_fancy);
		//mMusiListView.setAdapter(adapter);
		return rootView;
	}
}
