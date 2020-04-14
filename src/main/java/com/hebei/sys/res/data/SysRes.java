package com.hebei.sys.res.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.hebei.core.model.BaseModel;
/**
 * 对象功能:菜单资源
 * 开发公司:长城新媒体
 * 开发人员:mapper
 * 创建时间:2020-04-14 09:47:55
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value="菜单资源",description="菜单资源")
public class SysRes extends BaseModel {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@ApiModelProperty(value="id",name="id")
	protected Long  id;

	/**
	 * 资源名称
	 */
	@ApiModelProperty(value="资源名称",name="name")
	protected String  name;

	/**
	 * 父id
	 */
	@ApiModelProperty(value="父id",name="pid")
	protected Long  pid;

	/**
	 * 菜单别名
	 */
	@ApiModelProperty(value="菜单别名",name="alias")
	protected String  alias;

	/**
	 * 路径
	 */
	@ApiModelProperty(value="路径",name="path")
	protected String  path;

	/**
	 * 菜单地址
	 */
	@ApiModelProperty(value="菜单地址",name="src")
	protected String  src;

	/**
	 * 菜单类型
	 */
	@ApiModelProperty(value="菜单类型",name="type")
	protected Short  type;

	/**
	 * 菜单状态:1展示、2隐藏
	 */
	@ApiModelProperty(value="菜单状态:1展示、2隐藏",name="status")
	protected Short  status;

	/**
	 * 菜单描述
	 */
	@ApiModelProperty(value="菜单描述",name="description")
	protected String  description;

	/**
	 * 额外参数
	 */
	@ApiModelProperty(value="额外参数",name="params")
	protected String  params;





}