package com.hanqian.bambooCloud.entity.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;
import java.util.Map;

/**
 * 图文消息
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/24 。
 * ============================================================================
 */
@XStreamAlias("xml")
public class ArticlesMessage extends BaseMessage {

	@XStreamAlias("ArticleCount")
	private String articleCount;
	@XStreamAlias("Articles")
	private List<Articles> articles;

	public ArticlesMessage(Map<String, String> requestMap, List<Articles> articles, String articleCount) {
		super(requestMap);
		this.setMsgType("news");
		this.articleCount = articleCount;
		this.articles = articles;
	}

	public String getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}

	public List<Articles> getArticles() {
		return articles;
	}

	public void setArticles(List<Articles> articles) {
		this.articles = articles;
	}

	// ==========================================================

	@XStreamAlias("item")
	public static class Articles{
		@XStreamAlias("Title")
		private String title;
		@XStreamAlias("Description")
		private String description;
		@XStreamAlias("PicUrl")
		private String picUrl;
		@XStreamAlias("Url")
		private String url;

		public Articles(String title, String description, String picUrl, String url) {
			this.title = title;
			this.description = description;
			this.picUrl = picUrl;
			this.url = url;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}

}
