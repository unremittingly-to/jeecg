package org.jeecg.customer.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 客户管理表
 * @Author: jeecg-boot
 * @Date:   2021-04-28
 * @Version: V1.0
 */
@Data
@TableName("jxc_customer")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="jxc_customer对象", description="客户管理表")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**企业名称*/
	@Excel(name = "企业名称", width = 15)
    @ApiModelProperty(value = "企业名称")
    private String companyName;
	/**客户分组*/
	@Excel(name = "客户分组", width = 15)
    @ApiModelProperty(value = "客户分组")
    private String customerGroup;
	/**单位税号*/
	@Excel(name = "单位税号", width = 15)
    @ApiModelProperty(value = "单位税号")
    private String unitNum;
	/**销售总额*/
	@Excel(name = "销售总额", width = 15)
    @ApiModelProperty(value = "销售总额")
    private BigDecimal salesTotal;
	/**开户银行*/
	@Excel(name = "开户银行", width = 15)
    @ApiModelProperty(value = "开户银行")
    private String bank;
	/**银行账号*/
	@Excel(name = "银行账号", width = 15)
    @ApiModelProperty(value = "银行账号")
    private String bankAccount;
	/**地址电话*/
	@Excel(name = "地址电话", width = 15)
    @ApiModelProperty(value = "地址电话")
    private String addressPhone;
	/**常用发货地址*/
	@Excel(name = "常用发货地址", width = 15)
    @ApiModelProperty(value = "常用发货地址")
    private String deliveryAddress;
	/**常用寄票地址*/
	@Excel(name = "常用寄票地址", width = 15)
    @ApiModelProperty(value = "常用寄票地址")
    private String sendTicketAddress;
	/**备用1*/
	@Excel(name = "备用1", width = 15)
    @ApiModelProperty(value = "备用1")
    private String ext1;
	/**备用2*/
	@Excel(name = "备用2", width = 15)
    @ApiModelProperty(value = "备用2")
    private String ext2;
	/**备用3*/
	@Excel(name = "备用3", width = 15)
    @ApiModelProperty(value = "备用3")
    private String ext3;
}
