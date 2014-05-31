package supertank.player.model;

import supertank.player.model.base.Model;

public class RemoteMusic extends Model {

	
	public static String FIELD_ID= "musicid"; 
	public static String FIELD_NAME= "musicname"; 
	public static String FIELD_SIZE= "musicsize"; 
	public static String FIELD_PATH= "musicpath"; 
	
	private long musicId;
	private String musicName;
	private int musicSize;
	private String musicPath;
	
	public long getMusicId() {
		return musicId;
	}
	public void setMusicId(long musicId) {
		this.musicId = musicId;
	}
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public int getMusicSize() {
		return musicSize;
	}
	public void setMusicSize(int musicSize) {
		this.musicSize = musicSize;
	}
	public String getMusicPath() {
		return musicPath;
	}
	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}
	
	@Override
	public String toString() {
		return "RemoteMusic [musicId=" + musicId + ", musicName=" + musicName
				+ ", musicSize=" + musicSize + ", musicPath=" + musicPath + "]";
	}
}
