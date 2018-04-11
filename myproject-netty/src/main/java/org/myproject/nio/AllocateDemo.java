package org.myproject.nio;

import java.nio.CharBuffer;

public class AllocateDemo {
	public static void main(String[] args) {
		CharBuffer buffer = CharBuffer.allocate(8);
		buffer.put('L');
		buffer.put('E');
		buffer.put('E');
		buffer.put('S');
		buffer.put('F');
	
		buffer.position(3).limit(6).mark().position(5);
		CharBuffer dupeBuffer = buffer.duplicate( );
        buffer.clear( );
        dupeBuffer.flip();
        System.out.println(dupeBuffer.position());
        System.out.println(dupeBuffer.limit());
        System.out.println(dupeBuffer.get());

        buffer.put('Y');
        buffer.put('D');
        buffer.flip();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.get());

        System.out.println(dupeBuffer.get());
	}
}
