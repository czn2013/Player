package supertank.player.adapter;

import java.util.List;

import supertank.player.R;
import supertank.player.model.RemoteMusic;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MusicAdapter extends BaseAdapter {

	Context mContext;
//	MusicBusiness mMusicBusiness;
//	
//	public MusicAdapter(Context pContext, MusicBusiness pMusicBusiness){
//		mContext = pContext;
//		mMusicBusiness = pMusicBusiness;
//	} 
	
	List<RemoteMusic> mRemoteMusicList;
	
	public MusicAdapter(Context pContext, List<RemoteMusic> pRemoteMusicList){
		mContext = pContext;
		mRemoteMusicList = pRemoteMusicList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mRemoteMusicList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mRemoteMusicList.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.i("supertank", "MusicAdapter.getView()");
		ViewHolder _ViewHolder = new ViewHolder();
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.music_lv_item, null);
			_ViewHolder.name = (TextView) convertView.findViewById(R.id.music_lv_item_name);
			_ViewHolder.length = (TextView) convertView.findViewById(R.id.music_lv_item_length);
			convertView.setTag(_ViewHolder);
		}else {
			_ViewHolder = (ViewHolder)convertView.getTag();
		}
		RemoteMusic _RemoteMusic =mRemoteMusicList.get(position);
		Log.i("supertank", "RemoteMusic: " + _RemoteMusic);
		_ViewHolder.name.setText(_RemoteMusic.getMusicName());
		_ViewHolder.length.setText("" + _RemoteMusic.getMusicSize());
		return convertView;
	}
	
	class ViewHolder{
		TextView name;
		TextView length;
	}

}
