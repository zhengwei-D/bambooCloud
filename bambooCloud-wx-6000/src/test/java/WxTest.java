import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hanqian.bambooCloud.entity.menu.*;
import com.hanqian.bambooCloud.entity.message.ArticlesMessage;
import com.hanqian.bambooCloud.service.WxService;
import com.thoughtworks.xstream.XStream;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/24 。
 * ============================================================================
 */
public class WxTest {

	@Test
	public void test1(){
		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("ToUserName", "to");
		requestMap.put("FromUserName", "from");
		requestMap.put("createTime", "123");
		requestMap.put("msgType", "type");

		XStream xStream = new XStream();
//		xStream.processAnnotations(new Class[]{TextMessage.class, VoiceMessage.class, ImageMessage.class, ArticlesMessage.class, ArticlesMessage.Articles.class});
		xStream.autodetectAnnotations(true);
//		VoiceMessage voiceMessage = new VoiceMessage(requestMap, "6666");
//		String xml = xStream.toXML(voiceMessage);

//		ImageMessage imageMessage = new ImageMessage(requestMap, "999");
//		String xml = xStream.toXML(imageMessage);

//		TextMessage textMessage = new TextMessage(requestMap, "哦哦哦");
//		String xml = xStream.toXML(textMessage);

		List<ArticlesMessage.Articles> articlesList = new ArrayList<>();
		articlesList.add(new ArticlesMessage.Articles("标题1", "描述1", "图片1", "地址1"));
		articlesList.add(new ArticlesMessage.Articles("标题2", "描述2", "图片2", "地址2"));
		articlesList.add(new ArticlesMessage.Articles("标题3", "描述3", "图片3", "地址3"));
		ArticlesMessage articlesMessage = new ArticlesMessage(requestMap, articlesList, String.valueOf(articlesList.size()));
		String xml = xStream.toXML(articlesMessage);

		System.out.println(xml);
	}

	@Test
	public void test2(){
		Map<String, Object> data = new HashMap<>();
		data.put("a", "a");
		data.put("b", "b");
		JSONObject jsonObject = JSONUtil.parseFromMap(data);
		System.out.println(jsonObject.get("a"));
		System.out.println(jsonObject.get("c"));
		System.out.println("---");
	}

	@Test
	public void test3(){
		List<Button> button = new ArrayList<>();
		button.add(new ButtonClick("一级点击", "key_1"));
		button.add(new ButtonView("一级跳转", "http://www.baidu.com"));

		List<Button> sub_button = new ArrayList<>();
		sub_button.add(new ButtonLocationSelect("二级发位置", "key_location_1"));
		sub_button.add(new ButtonPicSysPhoto("二级拍照", "key_sys_photo_1"));
		sub_button.add(new ButtonClick("点", "123"));
		button.add(new SubButton("存在二级", sub_button));

		Menu menu = new Menu(button);
		System.out.println(JSONUtil.toJsonPrettyStr(menu));
	}

}
