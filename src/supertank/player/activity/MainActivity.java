package supertank.player.activity;

import java.util.List;
import java.util.Locale;

import supertank.player.R;
import supertank.player.business.FancyBusiness;
import supertank.player.business.MusicBusiness;
import supertank.player.fragment.FancyFragment;
import supertank.player.fragment.MusicFragment;
import supertank.player.model.LocalMusic;
import supertank.player.model.RemoteMusic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class MainActivity extends FragmentActivity {


	private List<RemoteMusic> mRemoteMusicList ;
	private List<LocalMusic> mLocalMusicList;
	MusicBusiness mMusicBusiness;
	FancyBusiness mFancyBusiness;

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mMusicBusiness = new MusicBusiness(this);
		mFancyBusiness = new FancyBusiness(this);
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				mRemoteMusicList = mMusicBusiness.getRemoteMusicList();
//				//Log.i("supertank", "mRemoteMusicList:"+mRemoteMusicList);
//				mLocalMusicList = mMusicBusiness.getLocalMusicList();
//			}
//		}).start();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment = new FancyFragment();
				break;
			case 1:
				fragment = new MusicFragment();
				break;
			default:
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_fancy).toUpperCase(l);
			case 1:
				return getString(R.string.title_music).toUpperCase(l);
			}
			return null;
		}
	}

	
	public List<LocalMusic> getLocalMusicList() {
		return mLocalMusicList;
	}

	public List<RemoteMusic> getRemoteMusicList() {
		return mRemoteMusicList;
	}

	public MusicBusiness getmMusicBusiness() {
		return mMusicBusiness;
	}

	public FancyBusiness getmFancyBusiness() {
		return mFancyBusiness;
	}


	public void setRemoteMusicList(List<RemoteMusic> mRemoteMusicList2) {
		// TODO Auto-generated method stub
		this.mRemoteMusicList = mRemoteMusicList2;
	}
	
	public void setLocalMusicList(List<LocalMusic> pLocalMusicList) {
		// TODO Auto-generated method stub
		this.mLocalMusicList = pLocalMusicList;
	}

}
