package org.myproject.socket.lesson03;

import java.io.IOException;

public interface VoteMsgCoder {
	
	//编码
	byte[] encode(VoteMsg voteMsg) throws IOException;

	//解码
	VoteMsg decode(byte[] input) throws IOException;
}
