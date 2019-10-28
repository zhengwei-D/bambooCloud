package com.hanqian.bambooCloud.entity.menu;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/25 。
 * ============================================================================
 */
public class ButtonLocationSelect extends Button {

	private String key;

	private String type;

	public ButtonLocationSelect(String name, String key) {
		this.setName(name);
		this.type = "location_select";
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
