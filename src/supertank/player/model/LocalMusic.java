package supertank.player.model;

import supertank.player.model.base.Model;

public class LocalMusic extends Model {

	public static String FIELD_ID= "musicid"; 
	public static String FIELD_NAME= "musicname"; 
	public static String FIELD_SIZE= "musicsize"; 
	public static String FIELD_PATH= "musicpath"; 
	
	private int musicId;
	private String musicName;
	private long musicSize;
	private String musicPath;
	
	public int getMusicId() {
		return musicId;
	}
	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public long getMusicSize() {
		return musicSize;
	}
	public void setMusicSize(Long size) {
		this.musicSize = size;
	}
	public String getMusicPath() {
		return musicPath;
	}
	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}
	
	@Override
	public String toString() {
		return "LocalMusic [musicId=" + musicId + ", musicName=" + musicName
				+ ", musicSize=" + musicSize + ", musicPath=" + musicPath + "]";
	}
}
