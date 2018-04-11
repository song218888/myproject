package org.myproject.nio;

import java.nio.CharBuffer;

public class BufferDemo {
	public static void main(String[] args) {
		CharBuffer buffer = CharBuffer.allocate(5);
		buffer.put('H');
		buffer.put('E');
		buffer.put('L');
		buffer.put('L');
		buffer.put('O');
		
		buffer.flip();
		while(buffer.hasRemaining()){
			System.out.println(buffer.get());
		}
	}
}
