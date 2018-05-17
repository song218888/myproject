package org.myproject.socket.lesson02;

import java.io.IOException;
import java.io.OutputStream;


/**
 * @author DELL
 *
 */
public interface Framer {
	
	//frameMag()方法用来添加成帧信息并将指定消息输出到指定流
	void frameMsg(byte[]message,OutputStream out)throws IOException;
	
	//nextMsg()方法则扫描指定的流，从中抽取出下一条消息
	byte[] nextMsg()throws IOException;
}
