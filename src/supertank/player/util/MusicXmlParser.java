package supertank.player.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;


import supertank.player.model.RemoteMusic;

/**
 * @author czn2013
 * Method: 1. create a new MusicXmlParser with a InputStream or a file path
 * 		   2. call the parse() method 
 *         3. call the getResult() method to get the List<Music> Result
 */
public class MusicXmlParser {

	List<RemoteMusic> mRemoteMusicList;
	String mPath;
	InputStream mInputStream;

	public MusicXmlParser(String pPath) {
		this.mPath = pPath;
	}
	
	public MusicXmlParser(InputStream pInputStream) {
		this.mInputStream = pInputStream;
	}

	public void  parse() throws SAXException,
	//public List<RemoteMusic> parse() throws SAXException,
			ParserConfigurationException, IOException {
		
		SAXParserFactory _Factory = SAXParserFactory.newInstance();

		SAXParser _SaxParser = _Factory.newSAXParser();
		if (mInputStream == null) {
			mInputStream = new FileInputStream(new File(mPath));
		}
		XmlParserHandler _Handler = new XmlParserHandler();
		_SaxParser.parse(mInputStream, _Handler);
		//Log.i("supertank", "return: mRemoteMusicList=" + mRemoteMusicList);
		//return mRemoteMusicList;
	}
	
	public List<RemoteMusic> getResult(){
		
		return mRemoteMusicList;
	}

	class XmlParserHandler extends DefaultHandler {

		RemoteMusic mRemoteMusic;
		String curTag;
		@Override
		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub
			//super.startDocument();
			Log.i("supertank", "startDocument()");
			mRemoteMusicList = new ArrayList<RemoteMusic>();
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			Log.i("supertank", "startElement(): localName=" +localName);
			curTag = qName;
			if (localName =="resource") {
				mRemoteMusic = new RemoteMusic();
			}
			//super.startElement(uri, localName, qName, attributes);
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			// TODO Auto-generated method stub
			Log.i("supertank", "characters: ch=" + new String(ch, start, length));
			String _Data = new String(ch, start, length);
			if ("musicid".equals(curTag)) {
				mRemoteMusic.setMusicId(Integer.parseInt(_Data));
			}else if ("musicname".equals(curTag)) {
				mRemoteMusic.setMusicName(_Data);
			}else if ("musicsize".equals(curTag)) {
				mRemoteMusic.setMusicSize(Integer.parseInt(_Data));
			}else if ("musicpath".equals(curTag)) {
				mRemoteMusic.setMusicPath(_Data);
			}
			//super.characters(ch, start, length);
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			// TODO Auto-generated method stub
			
			Log.i("supertank", "endElement() : localName=" + localName);
			if ("resource".equals(qName)) {
				mRemoteMusicList.add(mRemoteMusic);
			}
			curTag = null;
			//super.endElement(uri, localName, qName);
		}

		@Override
		public void endDocument() throws SAXException {
			// TODO Auto-generated method stub
			Log.i("supertank", "endDocument()");
			//super.endDocument();
		}

	}
}
