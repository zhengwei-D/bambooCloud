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
public class ImageMessage extends BaseMessage {

	@XStreamAlias("Image")
	private Image image;

	public ImageMessage(Map<String, String> requestMap, String mediaId) {
		super(requestMap);
		this.setMsgType("image");
		this.image = new Image(mediaId);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	// ============================================================

	public static class Image{

		@XStreamAlias("MediaId")
		private String mediaId;

		public Image(String mediaId) {
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
