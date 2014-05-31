package supertank.player.util;

import java.io.File;

import android.os.Environment;

public class Consts {

	public class net{
		public static final String URL_BASE = "http://192.168.0.135/music";
	}
	
	
	public static class file{
		public static final String FILE_BASE = 
				Environment.getExternalStorageDirectory() + File.separator + "mp3/";
	}
	
}
