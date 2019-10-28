package com.hanqian.bambooCloud.entity.token;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/24 。
 * ============================================================================
 */
public class AccessToken {

	private String accessToken;
	private long expiresTime;

	public AccessToken(String accessToken, String expiresIn) {
		this.accessToken = accessToken;
		this.expiresTime = System.currentTimeMillis() + Integer.parseInt(expiresIn)*1000;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpiresTime() {
		return expiresTime;
	}

	public void setExpiresTime(long expiresTime) {
		this.expiresTime = expiresTime;
	}

	/**
	 * 判断token是否过期
	 */
	public boolean isExpired(){
		return System.currentTimeMillis() > expiresTime;
	}
}
