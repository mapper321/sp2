package com.hebei.zydd.order.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.hebei.core.model.BaseModel;
/**
 * 对象功能:志愿点单
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-04-13 18:09:19
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value="志愿点单",description="志愿点单")
public class VolunteerOrder extends BaseModel {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@ApiModelProperty(value="id",name="id")
	protected Long  id;

	/**
	 * 分类：1助残养老、2生态建设、3时间培训、4社区服务、5大型活动、6应急救援、7环境保护、8医疗健康、9便民服务、10扶贫济困、11其他
	 */
	@ApiModelProperty(value="分类：1助残养老、2生态建设、3时间培训、4社区服务、5大型活动、6应急救援、7环境保护、8医疗健康、9便民服务、10扶贫济困、11其他",name="type")
	protected Short  type;

	/**
	 * 标题
	 */
	@ApiModelProperty(value="标题",name="title")
	protected String  title;

	/**
	 * 内容
	 */
	@ApiModelProperty(value="内容",name="content")
	protected String  content;

	/**
	 * 开始时间
	 */
	@ApiModelProperty(value="开始时间",name="startTime")
	protected java.util.Date  startTime;
	/**
	 * 开始 开始时间
	 */
	@ApiModelProperty(value="开始开始时间",name="beginstartTime", hidden=true)
	protected java.util.Date  beginstartTime;
	/**
	 * 结束  开始时间
	 */
	@ApiModelProperty(value="结束开始时间",name="endstartTime", hidden=true)
	protected java.util.Date  endstartTime;	

	/**
	 * 地址
	 */
	@ApiModelProperty(value="地址",name="address")
	protected String  address;

	/**
	 * 坐标
	 */
	@ApiModelProperty(value="坐标",name="position")
	protected String  position;

	/**
	 * 图片
	 */
	@ApiModelProperty(value="图片",name="files")
	protected String  files;

	/**
	 * 活动状态
	 */
	@ApiModelProperty(value="活动状态",name="status")
	protected Short  status;

	/**
	 * 发布人姓名
	 */
	@ApiModelProperty(value="发布人姓名",name="pubUserName")
	protected String  pubUserName;

	/**
	 * 发布人性别
	 */
	@ApiModelProperty(value="发布人性别",name="pubUserSex")
	protected Short  pubUserSex;

	/**
	 * 发布人年龄
	 */
	@ApiModelProperty(value="发布人年龄",name="pubUserAge")
	protected Short  pubUserAge;

	/**
	 * 发布人联系方式
	 */
	@ApiModelProperty(value="发布人联系方式",name="pubUserPhone")
	protected String  pubUserPhone;





}