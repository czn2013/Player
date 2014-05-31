package supertank.player.fragment;


import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import supertank.player.R;
import supertank.player.activity.MainActivity;
import supertank.player.adapter.MusicAdapter;
import supertank.player.model.LocalMusic;
import supertank.player.model.RemoteMusic;
import supertank.player.util.Consts;
import supertank.player.util.FileUtil;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.SyncStateContract.Constants;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MusicFragment extends Fragment {
	ListView mMusicView;
	List<RemoteMusic> mRemoteMusicList;
	Handler mHandler;
	MusicAdapter mMusicViewAdapter;
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mRemoteMusicList = ((MainActivity)getActivity()).getRemoteMusicList();
		if (mRemoteMusicList == null) {
			Log.i("supertank", "null:");
			mHandler = new NetworkHanler(getActivity());
			try {
				new Thread(new Runnable() {
					
					
					
					@Override
					public void run() {
						// TODO Auto-generated method stub

						List<RemoteMusic> _RemoteMusicList = ((MainActivity)getActivity()).getmMusicBusiness().getRemoteMusicList();
						Log.i("supertank", "MusicFragment-->RemoteMusic: "+ _RemoteMusicList);
						((MainActivity)getActivity()).setRemoteMusicList(_RemoteMusicList);
						
						List<LocalMusic> _LocalMusicList = ((MainActivity)getActivity()).getmMusicBusiness().getLocalMusicList();
						Log.i("supertank", "MusicFragment-->LocalMusic: "+ _LocalMusicList);
						for (LocalMusic localMusic : _LocalMusicList) {
							File _File = new File(localMusic.getMusicName());
							Log.i("supertank", _File.getName());
						}
						((MainActivity)getActivity()).setLocalMusicList(_LocalMusicList);
						
						Message _Message = mHandler.obtainMessage(NetworkHanler.MSG_GET_REMOTE);
						_Message.obj = _RemoteMusicList;
						mHandler.sendMessage(_Message);
						
					}
				}).start();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}else {
			Log.i("supertank", "here");
			ListAdapter _Adapter =new MusicAdapter(getActivity(),mRemoteMusicList);
			mMusicView.setAdapter(_Adapter);
		}
		
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_music, container,false);
		mMusicView = (ListView) rootView.findViewById(R.id.fragment_music_lv_remote);
		mMusicView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				RemoteMusic _RemoteMusic =(RemoteMusic) mMusicViewAdapter.getItem((int)arg3);
				Toast.makeText(getActivity(), "" +_RemoteMusic, Toast.LENGTH_LONG).show();
				File appFile = new File(Consts.file.FILE_BASE);
				if (!appFile.exists()) {
					appFile.mkdirs();
				}
				try {
					String _MusicPath = Consts.net.URL_BASE + "/" + _RemoteMusic.getMusicPath()+ "/"+ _RemoteMusic.getMusicName() ;
					FileUtil.downFile(_MusicPath, 
							Consts.file.FILE_BASE,
							_RemoteMusic.getMusicName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		return rootView;
	}
	
	class NetworkHanler extends Handler {
		  public static final int MSG_GET_REMOTE = 1;
		   
		  WeakReference<Activity> mActivity;
		 
		  NetworkHanler(Activity activity) {
			  Log.i("supertank", "created:");
		      mActivity = new WeakReference<Activity>(activity);
		   }
		 
		   @Override
		   public void handleMessage(Message msg) {
			  Log.i("supertank", "handleMessage: ");
		      Activity theActivity = (Activity) mActivity.get();
		      if(theActivity == null)
		         return;
		      switch (msg.what) {
		      case MSG_GET_REMOTE:
		    	 List<RemoteMusic> _RemoteMusics = ((MainActivity)getActivity()).getRemoteMusicList();
		    	 mMusicViewAdapter = new MusicAdapter(getActivity(), _RemoteMusics);
		    	 mMusicView.setAdapter(mMusicViewAdapter);
		         break;
		      default:
		 
		        break;
		      }
		   }
		}

}
