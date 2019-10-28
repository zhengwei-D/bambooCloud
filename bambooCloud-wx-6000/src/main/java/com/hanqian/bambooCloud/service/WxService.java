package com.hanqian.bambooCloud.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hanqian.bambooCloud.entity.menu.Menu;
import com.hanqian.bambooCloud.entity.message.*;
import com.hanqian.bambooCloud.entity.token.AccessToken;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.*;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/24 。
 * ============================================================================
 */
@Service
public class WxService {

	 @Value("${wx.token}")
	private String token;

	@Value("${wx.appId}")
	private String appId;

	@Value("${wx.appsecret}")
	private String appsecret;

	private static AccessToken accessToken;

	/**
	 * 验证签名
	 */
	public boolean check(String signature, String timestamp, String nonce){

		if(StrUtil.isNotBlank(signature) && StrUtil.isNotBlank(timestamp) && StrUtil.isNotBlank(nonce)){
			//1）将token、timestamp、nonce三个参数进行字典序排序
			String[] strs = new String[]{token, timestamp, nonce};
			Arrays.sort(strs);

			// 2）将三个参数字符串拼接成一个字符串进行sha1加密
			String str = strs[0] + strs[1] + strs[2];
			String sha1Str = SecureUtil.sha1(str);

			// 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
			return StrUtil.equals(signature, sha1Str);
		}

		return false;
	}

	public Map<String, String> parseXml(HttpServletRequest request)throws Exception{
		// 将解析结果存储在HashMap中
		Map<String, String>map =new HashMap<String, String>();
		// 从request中得到输入流
		InputStream inputStream=request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到XML的根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		@SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();
		// 判断又没有子元素列表
		if (elementList.size()==0){
			map.put(root.getName(), root.getText());
		}else {
			for (Element e : elementList)
				map.put(e.getName(), e.getText());
		}
		// 释放资源
		inputStream.close();
		inputStream = null;
		System.out.println("---------xml转换为map-----:"+map);
		return map;
	}

	public String replyMsg(Map<String, String> map){
		BaseMessage baseMessage = null;
		String smgType = map.get("MsgType");
		switch (smgType){
			case "text":
				baseMessage = dealTextMessage(map);
				break;
			case "image":
				baseMessage = dealImageMessage(map);
				break;
			case "voice":
				baseMessage = dealVoiceMessage(map);
				break;
			case "video":

				break;
			case "music":

				break;
			case "news":
				baseMessage = dealNewsMessage(map);
				break;
			case "event":
				baseMessage = dealEventMessage(map);
				break;
			default:
		}

		return beanToXml(baseMessage);
	}

	/**
	 * 回复消息对象转为xml
	 */
	private String beanToXml(BaseMessage message){
		if(message!=null){
			XStream xStream = new XStream();
			xStream.autodetectAnnotations(true);
			return xStream.toXML(message);
		}else{
			return "";
		}
	}

	/**
	 * 回复文本消息
	 */
	private BaseMessage dealTextMessage(Map<String, String> requestMap){
		String content = requestMap.get("Content");
		if(StrUtil.equals("图文", content)){
			return dealNewsMessage(requestMap);
		}
		String replyContent = "你说的是【"+content+"】";
		return new TextMessage(requestMap, replyContent);
	}

	/**
	 * 回复图片消息
	 */
	private BaseMessage dealImageMessage(Map<String, String> requestMap){
		String mediaId = "123456";
//		return new ImageMessage(requestMap, mediaId);
		return new TextMessage(requestMap, "好图+++");
	}

	/**
	 * 回复音频消息
	 */
	private BaseMessage dealVoiceMessage(Map<String, String> requestMap){
		String mediaId = "123456";
		return new VoiceMessage(requestMap, mediaId);
	}

	/**
	 * 回复图文消息
	 */
	private BaseMessage dealNewsMessage(Map<String, String> requestMap){
		List<ArticlesMessage.Articles> articlesList = new ArrayList<>();
		articlesList.add(new ArticlesMessage.Articles("标题1", "描述1", "http://qqpublic.qpic.cn/qq_public/0/0-2465929271-FA00A2C5FB42952F5EE5A99DC01EA5D0/0?fmt=jpg&size=41&h=472&w=755&ppv=1", "http://ai.baidu.com/docs#/EntityAnnotation-API/top"));
//		articlesList.add(new ArticlesMessage.Articles("标题2", "描述2", "http://qqpublic.qpic.cn/qq_public/0/0-3178505981-1AC3D44ED6FE6190307D4F62FE8629F0/0?fmt=jpg&size=99&h=734&w=670&ppv=1", "https://www.bilibili.com/video/av35042298/?p=9"));
//		articlesList.add(new ArticlesMessage.Articles("标题3", "描述3", "图片3", "地址3"));
		return  new ArticlesMessage(requestMap, articlesList, String.valueOf(articlesList.size()));
	}

	/**
	 * 回复事件消息
	 */
	private BaseMessage dealEventMessage(Map<String, String> requestMap){
		String event = requestMap.get("Event");
		String key = requestMap.get("EventKey");
		if(StrUtil.equals("CLICK", event)){
			return new TextMessage(requestMap,"这个按钮的key是：" + key);
		}else if(StrUtil.equals("pic_sysphoto", event)){
			return new TextMessage(requestMap, "好图！");
		}
		return new TextMessage(requestMap, "什么也没处理");
	}

	/**
	 * 获取新token
	 */
	private void getNewToken(){
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		url = url.replace("APPID", appId).replace("APPSECRET", appsecret);
		String re = HttpUtil.get(url);

		JSONObject jsonObject = JSONUtil.parseObj(re);
		String token = jsonObject.get("access_token")!=null ? jsonObject.get("access_token").toString() : "";
		String expiresIn = jsonObject.get("expires_in")!=null ? jsonObject.get("expires_in").toString() : "";
		accessToken = new AccessToken(token, expiresIn); //创建token对象并存储
	}

	/**
	 * 向外暴露获取token
	 */
	public String getAccessToken(){
		if(accessToken==null || accessToken.isExpired()){
			getNewToken();
		}
		return accessToken.getAccessToken();
	}

	/**
	 * 设置菜单
	 */
	public String setMenu(Menu menu){
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		url = url.replace("ACCESS_TOKEN", getAccessToken());
		System.out.println(url);

		String jsonMenu = JSONUtil.toJsonPrettyStr(menu);
		String re = HttpUtil.post(url, jsonMenu);

		return re;
	}
}
