package com.hanqian.bambooCloud.entity.menu;

import java.util.List;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/25 。
 * ============================================================================
 */
public class Menu {

	private List<Button> button;

	public Menu(List<Button> button) {
		this.button = button;
	}

	public List<Button> getButton() {
		return button;
	}

	public void setButton(List<Button> button) {
		this.button = button;
	}
}
