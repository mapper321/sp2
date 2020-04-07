package com.hebei.zydd.user.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.hebei.core.model.BaseModel;
/**
 * 对象功能:志愿点单报名人员
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-04-07 18:58:29
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value="志愿点单报名人员",description="志愿点单报名人员")
public class VolunteerOrderUser extends BaseModel {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@ApiModelProperty(value="id",name="id")
	protected Long  id;

	/**
	 * 志愿点单id
	 */
	@ApiModelProperty(value="志愿点单id",name="volunteerOrderId")
	protected Long  volunteerOrderId;

	/**
	 * 状态：1参加，2取消
	 */
	@ApiModelProperty(value="状态：1参加，2取消",name="status")
	protected Short  status;





}