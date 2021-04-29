package org.jeecg.sysdict.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.customer.entity.Customer;
import org.jeecg.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @Description: 客户管理表
* @Author: jeecg-boot
* @Date:   2021-04-28
* @Version: V1.0
*/
@Api(tags="数据字典表")
@RestController
@RequestMapping("/dictModel/dictModel")
@Slf4j
public class SysDictController extends JeecgController<Customer, ICustomerService> {
   @Autowired
   private ISysBaseAPI iSysBaseAPI;

   /**
    * 通过code查询
    *
    * @param code
    * @return
    */
   @AutoLog(value = "数据字典表-通过code查询")
   @ApiOperation(value="数据字典表-通过code查询", notes="数据字典表-通过code查询")
   @GetMapping(value = "/queryByCode")
   public Result<?> queryById(@RequestParam(name="code",required=true) String code) {
       List<DictModel> dictModels = iSysBaseAPI.queryDictItemsByCode(code);
       if(CollectionUtils.isNotEmpty(dictModels)) {
           return Result.error("未找到对应数据");
       }
       return Result.ok(dictModels);
   }



}
