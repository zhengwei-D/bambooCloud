package com.hanqian.bambooCloud.entity.menu;

/**
 * 点击跳转连接
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/25 。
 * ============================================================================
 */
public class ButtonView extends Button {

	private String url;

	private String type;

	public ButtonView(String name, String url) {
		this.setName(name);
		this.type = "view";
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
