package com.hanqian.bambooCloud.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/24 。
 * ============================================================================
 */
@Component
public class WxUtil {

	public static String getAccessToken(){
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		return "";
	}

}
