package supertank.player.business;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;


import supertank.player.model.LocalMusic;
import supertank.player.model.RemoteMusic;
import supertank.player.util.Consts;
import supertank.player.util.FileUtil;
import supertank.player.util.HttpUtil;
import supertank.player.util.MusicXmlParser;

public class MusicBusiness {
	
	Context mContext;

	public MusicBusiness(Context pContext) {

		this.mContext = pContext;
	}

	public List<RemoteMusic> getRemoteMusicList(){
		List<RemoteMusic> _MusicList = null;
		String _Url = Consts.net.URL_BASE +"/music.xml";
		HashMap<String, String> map = new HashMap<String, String>();
		String _Result = HttpUtil.get(_Url, map);
		Log.i("supertank", _Result);
		try {	
			FileUtil.write(mContext, "music.xml", _Result);
			Log.i("supertank", "file read from device"+ (new File("/data/data/"+mContext.getPackageName()+"/files/music.xml")).toString());
			MusicXmlParser _Parser = new MusicXmlParser(
					new FileInputStream(
							new File("/data/data/"+mContext.getPackageName()+"/files/music.xml")));
			_Parser.parse();
			_MusicList =  _Parser.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.i("supertank", "MusicBusiness"+ _MusicList);
		return _MusicList;
	}

	public List<LocalMusic> getLocalMusicList() {
		// TODO Auto-generated method stub
		
		ArrayList<LocalMusic> _LocalMusicList = new ArrayList<LocalMusic>();
 
		//查询媒体数据库
		Cursor _Cursor = mContext.getContentResolver().query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, 
				null, 
				null, 
				null,
				MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
		//遍历媒体数据库
		while(_Cursor.moveToNext()){
			 //歌曲文件的路径 ：MediaStore.Audio.Media.DATA
	        String path = _Cursor.getString(_Cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));    
		    
		    
		    if (path.endsWith("mp3")) {
		    	
				LocalMusic _LocalMusic = new LocalMusic();
				_LocalMusic.setMusicPath(path);
				//歌曲编号
		        int id = _Cursor.getInt(_Cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));  
				_LocalMusic.setMusicId(id);
				 //歌曲标题
		        String tilte = _Cursor.getString(_Cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
		        _LocalMusic.setMusicName(tilte);
		        //歌曲的专辑名：MediaStore.Audio.Media.ALBUM
		        //String album = _Cursor.getString(_Cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));  
		        //歌曲的歌手名： MediaStore.Audio.Media.ARTIST
		        //String artist = _Cursor.getString(_Cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));  
		        //歌曲的总播放时长 ：MediaStore.Audio.Media.DURATION
		        //int duration = _Cursor.getInt(_Cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));    
		        //歌曲文件的大小 ：MediaStore.Audio.Media.SIZE
		        Long size = _Cursor.getLong(_Cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
		        _LocalMusic.setMusicSize(size);
		        _LocalMusicList.add(_LocalMusic);
		    }
	        
		         
		       
		}
		        
		return _LocalMusicList;
		
	}
	

	

	
}
