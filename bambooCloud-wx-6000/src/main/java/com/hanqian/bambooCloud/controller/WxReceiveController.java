package com.hanqian.bambooCloud.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hanqian.bambooCloud.entity.menu.*;
import com.hanqian.bambooCloud.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/24 。
 * ============================================================================
 */
@RestController
public class WxReceiveController {

	@Autowired
	private HttpServletRequest request;
//	@Autowired
//	private HttpServletResponse response;
	@Autowired
	private WxService wxService;

	@GetMapping(value = "wx")
	public Object receive(String signature, String timestamp, String nonce, String echostr){
		System.out.println(signature);
		System.out.println(timestamp);
		System.out.println(nonce);
		System.out.println(echostr);
		if(wxService.check(signature, timestamp, nonce)){
			return echostr;
		}else{
			return "接入失败";
		}
	}

	@PostMapping(value = "wx")
	public Object msg() throws Exception {
		Map<String, String> dataMap = wxService.parseXml(request);
		System.out.println(dataMap);
		return wxService.replyMsg(dataMap);
	}

	@GetMapping(value = "getToken")
	public String getToken(){
		return wxService.getAccessToken();
	}

	@GetMapping(value = "setMenu")
	public Object setMenu() throws UnsupportedEncodingException {

		List<Button> button = new ArrayList<>();
		button.add(new ButtonClick("一级点击", "key_1"));

		List<Button> sub_button = new ArrayList<>();
		sub_button.add(new ButtonLocationSelect("二级发位置", "key_location_1"));
		sub_button.add(new ButtonPicSysPhoto("二级拍照", "key_sys_photo_1"));
		sub_button.add(new ButtonClick("点", "123"));
		button.add(new SubButton("存在二级", sub_button));


		String redirectUri = "http://bamboo.free.idcfengye.com/redirectUri";
		String getCodeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=enable#wechat_redirect";
		getCodeUrl = getCodeUrl.replace("APPID", "wx9e2ca8be6d7e98e3").replace("REDIRECT_URI",URLEncoder.encode(redirectUri, "utf-8")).replace("SCOPE", "snsapi_userinfo");


		List<Button> sub_button_scope = new ArrayList<>();
		sub_button_scope.add(new ButtonView("网页授权", getCodeUrl));
//		sub_button_scope.add(new ButtonView("无需同意", ""));
		button.add(new SubButton("授权", sub_button_scope));

		Menu menu = new Menu(button);

		return wxService.setMenu(menu);
	}

	/**
	 * 设置所属行业
	 */
	@GetMapping(value = "setIndustry")
	public Object setIndustry(){
		JSONObject jsonObject = JSONUtil.createObj();
		jsonObject.put("industry_id1", "1");
		jsonObject.put("industry_id2", "39");
		String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
		url = url.replace("ACCESS_TOKEN", wxService.getAccessToken());
		String re = HttpUtil.post(url, jsonObject.toString());
		return re;
	}

	/**
	 * 查看当前设置的所属行业
	 */
	@GetMapping(value = "getIndustry")
	public Object getIndustry(){
		String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
		url = url.replace("ACCESS_TOKEN", wxService.getAccessToken());
		String re = HttpUtil.get(url);
		return re;
	}

	@GetMapping(value = "redirectUri")
	public Object redirectUri(String code, String state){
		System.out.println("code:" + code);
		System.out.println("state:" + state);

		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		url = url.replace("APPID", "wx9e2ca8be6d7e98e3").replace("SECRET", "8625917c65dfccb572934da4aaededc2").replace("CODE", code);
		String re = HttpUtil.get(url);
		JSONObject jsonObject = JSONUtil.parseObj(re);
		String at = jsonObject.get("access_token").toString();
		String openId = jsonObject.get("openid").toString();

		String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		infoUrl = infoUrl.replace("ACCESS_TOKEN", at).replace("OPENID", openId);
		String infoJson = HttpUtil.get(infoUrl);

		return infoJson;
	}

}
