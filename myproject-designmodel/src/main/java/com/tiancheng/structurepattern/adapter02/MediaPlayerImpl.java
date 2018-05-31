package com.tiancheng.structurepattern.adapter02;

interface MediaPlayer {
	public void play(String audioType, String fileName);
}

// 目前MediaPlayer只能播放mp3，但我们想它能播放其它的音乐
public class MediaPlayerImpl implements MediaPlayer {
	
	MediaAdapter mediaAdapter;

	@Override
	public void play(String audioType, String fileName) {
		// System.out.println("Mp3 play, audioType = " + audioType + " ,fileName = " + fileName);

		//播放 mp3 音乐文件的内置支持
	      if(audioType.equalsIgnoreCase("mp3")){
	         System.out.println("Playing mp3 file. Name: "+ fileName);            
	      } 
	      //mediaAdapter 提供了播放其他文件格式的支持
	      else if(audioType.equalsIgnoreCase("vlc") 
	         || audioType.equalsIgnoreCase("mp4")){
	         mediaAdapter = new MediaAdapter(audioType);
	         mediaAdapter.play(audioType, fileName);
	      }
	      else{
	         System.out.println("Invalid media. "+
	            audioType + " format not supported");
	      }

	}

}
