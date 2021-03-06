package org.myproject.socket.lesson02;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 定长成帧
 * 
 * 适用于长度小于65535个字节的消息
 * 
 * 发送者首先给出指定消息的长度，并将长度信息以big-endian顺序（从左边开始，由高位到低位发送）存入2个字节的整数中，
 * 
 * 再将这两个字节存放在完整的消息内容前, 连同消息一起写入输出流；
 * 
 * 在接收端，使用DataInputStream读取整型的长度信息，readFully（）方法将阻塞等待，直到给定的数组完全填满
 * 
 * 使用这种成帧方法，发送者不需要检查要成帧的消息内容，而只需要检查消息的长度是否超出了限制
 * 
 * @author DELL
 *
 */

public class LengthFramer implements Framer {

	public static final int MAXMESSAGELENGTH = 65535;
	public static final int BYTEMASK = 0xff;
	public static final int SHORTMASK = 0xffff;
	public static final int BYTESHIFT = 8;

	private DataInputStream in;

	public LengthFramer(InputStream in) throws IOException {
		this.in = new DataInputStream(in); // 数据来源
	}

	// 对字节流message添加成帧信息，并输出到指定流
	public void frameMsg(byte[] message, OutputStream out) throws IOException {
		// 消息的长度不能超过65535
		if (message.length > MAXMESSAGELENGTH) {
			throw new IOException("message too long");
		}
		out.write((message.length >> BYTESHIFT) & BYTEMASK);
		out.write(message.length & BYTEMASK);
		out.write(message);
		out.flush();
	}

	public byte[] nextMsg() throws IOException {
		int length;
		try {
			// 该方法读取2个字节，将它们作为big-endian整数进行解释，并以int型整数返回它们的值
			length = in.readUnsignedShort();
		} catch (EOFException e) { // no (or 1 byte) message
			return null;
		}
		// 0 <= length <= 65535
		byte[] msg = new byte[length];
		// 该方法处阻塞等待，直到接收到足够的字节来填满指定的数组
		in.readFully(msg);
		return msg;
	}

}
