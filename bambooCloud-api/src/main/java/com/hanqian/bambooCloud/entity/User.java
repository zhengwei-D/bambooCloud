package com.hanqian.bambooCloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/16 。
 * ============================================================================
 */
@Data
@TableName(value = "sys_user")
public class User implements Serializable {

	@TableId(type = IdType.UUID)
	private String id;

	private Date createTime;

	private Date updateTime;

	private String name;

}
