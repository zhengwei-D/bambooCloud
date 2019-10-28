package com.hanqian.bambooCloud.entity.menu;

import java.util.List;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/25 。
 * ============================================================================
 */
public class SubButton extends Button {

	private String name;
	private List<Button> sub_button;

	public SubButton(String name, List<Button> sub_button) {
		this.name = name;
		this.sub_button = sub_button;
	}

	public List<Button> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<Button> sub_button) {
		this.sub_button = sub_button;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
