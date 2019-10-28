package com.hanqian.bambooCloud.entity.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * 恢复文本消息
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/24 。
 * ============================================================================
 */
@XStreamAlias("xml")
public class TextMessage extends BaseMessage {

	@XStreamAlias("Content")
	private String content;

	public TextMessage(Map<String, String> requestMap, String content) {
		super(requestMap);
		this.setMsgType("text");
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
