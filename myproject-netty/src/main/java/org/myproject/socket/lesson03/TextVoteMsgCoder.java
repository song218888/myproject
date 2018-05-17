package org.myproject.socket.lesson03;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * 文本实现
 * @author DELL
 *
 */
public class TextVoteMsgCoder implements VoteMsgCoder {

	//消息的开头是一个所谓的”魔术字符串“，即一个字符序列，用于快速将投票协议的消息和网络中随机到来的垃圾消息区分开
	public static final String MAGIC = "Voting";
	
	//投票、查询布尔值被编码为字符形似，‘v’代表投票消息，‘i’代表查询消息
	public static final String VOTESTR = "v";
	public static final String INQSTR = "i";
	
	//是否为服务器发送的响应消息，由字符‘R’指示
	public static final String RESPONSESTR = "R";
	
	//协议指定使用ASCII字符集对文本进行编码
	public static final String CHARSETNAME = "US-ASCII";
	
	//文本界定符
	public static final String DELIMSTR = " ";
	
	public static final int MAX_WIRE_LENGTH = 2000;

	
	/**
	 * 
	 * 方法简单地创建一个字符串，该字符串中包含了消息的所有字段，并由空白符隔开。
	 * 
	 * encode()方法首先检查”魔术字符串“，如果在消息最前面没有魔术字符串，则抛出一个异常。
	 * 
	 * 在理说明了在实现协议时非常重要的一点：永远不要对从网络中来的任何输入进行任何假设。
	 * 
	 * 你的程序必须时刻为任何可能的输入做好准备，并能很好的对其进行处理
	 */
	public byte[] encode(VoteMsg msg) throws IOException {
		String msgString = MAGIC + DELIMSTR + (msg.isInquiry() ? INQSTR : VOTESTR) + DELIMSTR
				+ (msg.isResponse() ? RESPONSESTR + DELIMSTR : "") + Integer.toString(msg.getCandidateID()) + DELIMSTR
				+ Long.toString(msg.getVoteCount());
		byte data[] = msgString.getBytes(CHARSETNAME);
		return data;
	}

	public VoteMsg decode(byte[] message) throws IOException {
		
		ByteArrayInputStream msgStream = new ByteArrayInputStream(message);
		
		@SuppressWarnings("resource")
		Scanner s = new Scanner(new InputStreamReader(msgStream, CHARSETNAME));
		boolean isInquiry;
		boolean isResponse;
		int candidateID;
		long voteCount;
		String token;

		try {
			token = s.next();
			if (!token.equals(MAGIC)) {
				throw new IOException("Bad magic string: " + token);
			}
			token = s.next();
			if (token.equals(VOTESTR)) {
				isInquiry = false;
			} else if (!token.equals(INQSTR)) {
				throw new IOException("Bad vote/inq indicator: " + token);
			} else {
				isInquiry = true;
			}

			token = s.next();
			if (token.equals(RESPONSESTR)) {
				isResponse = true;
				token = s.next();
			} else {
				isResponse = false;
			}
			// Current token is candidateID
			// Note: isResponse now valid
			candidateID = Integer.parseInt(token);
			if (isResponse) {
				token = s.next();
				voteCount = Long.parseLong(token);
			} else {
				voteCount = 0;
			}
		} catch (IOException ioe) {
			throw new IOException("Parse error...");
		}
		return new VoteMsg(isResponse, isInquiry, candidateID, voteCount);
	}

}
