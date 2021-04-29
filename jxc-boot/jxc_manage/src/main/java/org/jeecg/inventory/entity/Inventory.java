package org.jeecg.inventory.entity;

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
 * @Description: 库存管理表
 * @Author: jeecg-boot
 * @Date:   2021-04-28
 * @Version: V1.0
 */
@Data
@TableName("jxc_inventory")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="jxc_inventory对象", description="库存管理表")
public class Inventory implements Serializable {
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
	/**产品名称*/
	@Excel(name = "产品名称", width = 15)
    @ApiModelProperty(value = "产品名称")
    private String productName;
	/**自有型号*/
	@Excel(name = "自有型号", width = 15)
    @ApiModelProperty(value = "自有型号")
    private String ownModel;
	/**所属大类*/
	@Excel(name = "所属大类", width = 15)
    @ApiModelProperty(value = "所属大类")
    private String category;
	/**入库数量*/
	@Excel(name = "入库数量", width = 15)
    @ApiModelProperty(value = "入库数量")
    private Integer putCount;
	/**入库时间*/
	@Excel(name = "入库时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "入库时间")
    private Date putDate;
	/**库存数量*/
	@Excel(name = "库存数量", width = 15)
    @ApiModelProperty(value = "库存数量")
    private Integer existCount;
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
