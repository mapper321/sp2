package com.hebei.core.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


public abstract class BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	//创建时间
	@ApiModelProperty(hidden=true)
	protected Date createTime;
	//创建人
	@ApiModelProperty(hidden=true)
	protected Long createBy;
	//更新时间
	@ApiModelProperty(hidden=true)
	protected Date updateTime;
	//更新人
	@ApiModelProperty(hidden=true)
	protected Long updateBy;

	//创建时间范围条件
	@ApiModelProperty(hidden=true)
	protected Date  begincreateTime;
	//创建时间范围条件
	@ApiModelProperty( hidden=true)
	protected Date  endcreateTime;
	//更新时间范围条件
	@ApiModelProperty(hidden=true)
	protected Date  beginupdateTime;
	@ApiModelProperty(hidden=true)
	protected Date  endupdateTime;


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
}
