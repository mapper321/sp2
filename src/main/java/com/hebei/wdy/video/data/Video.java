package com.hebei.wdy.video.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.hebei.core.model.BaseModel;
/**
 * 对象功能:文明微电影
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-04-07 15:12:55
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value="文明微电影",description="文明微电影")
public class Video extends BaseModel {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@ApiModelProperty(value="id",name="id")
	protected Long  id;

	/**
	 * 标题
	 */
	@ApiModelProperty(value="标题",name="title")
	protected String  title;

	/**
	 * 内容简介
	 */
	@ApiModelProperty(value="内容简介",name="content")
	protected String  content;

	/**
	 * 内容分类:1学习视频，2科普视频，3看电视，4看党史，5看人物，6看文艺，7看科学，8看法制，9网络视听
	 */
	@ApiModelProperty(value="内容分类:1学习视频，2科普视频，3看电视，4看党史，5看人物，6看文艺，7看科学，8看法制，9网络视听",name="contentType")
	protected Short  contentType;

	/**
	 * 封面
	 */
	@ApiModelProperty(value="封面",name="cover")
	protected String  cover;

	/**
	 * 视频来源
	 */
	@ApiModelProperty(value="视频来源",name="source")
	protected String  source;

	/**
	 * 来源分类：1新闻联播，2微视频，3自媒体
	 */
	@ApiModelProperty(value="来源分类：1新闻联播，2微视频，3自媒体",name="sourceType")
	protected Short  sourceType;

	/**
	 * 视频时长
	 */
	@ApiModelProperty(value="视频时长",name="videoDuration")
	protected Long  videoDuration;

	/**
	 * 视频地址
	 */
	@ApiModelProperty(value="视频地址",name="videoUrl")
	protected String  videoUrl;

	/**
	 * 视频创建时间
	 */
	@ApiModelProperty(value="视频创建时间",name="videoDate")
	protected java.util.Date  videoDate;
	/**
	 * 开始 视频创建时间
	 */
	@ApiModelProperty(value="开始视频创建时间",name="beginvideoDate", hidden=true)
	protected java.util.Date  beginvideoDate;
	/**
	 * 结束  视频创建时间
	 */
	@ApiModelProperty(value="结束视频创建时间",name="endvideoDate", hidden=true)
	protected java.util.Date  endvideoDate;	





}