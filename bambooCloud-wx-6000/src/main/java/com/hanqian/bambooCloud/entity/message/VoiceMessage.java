package com.hanqian.bambooCloud.entity.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/24 。
 * ============================================================================
 */
@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage {

	@XStreamAlias("Voice")
	private Voice voice;

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public VoiceMessage(Map<String, String> requestMap, String mediaId) {
		super(requestMap);
		this.setMsgType("voice");
		this.voice = new Voice(mediaId);
	}

	// =============================================================

	public static class Voice{

		@XStreamAlias("MediaId")
		private String mediaId;

		public Voice(String mediaId) {
			this.mediaId = mediaId;
		}

		public String getMediaId() {
			return mediaId;
		}

		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}
	}
}
