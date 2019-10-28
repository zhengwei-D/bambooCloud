package com.hanqian.bambooCloud.entity.menu;

/**
 * 弹出系统拍照发图
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/25 。
 * ============================================================================
 */
public class ButtonPicSysPhoto extends Button {

	private String key;

	private String type;

	public ButtonPicSysPhoto(String name, String key) {
		this.setName(name);
		this.type = "pic_sysphoto";
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
