package org.jeecg.inventory.service.impl;

import org.jeecg.inventory.entity.Inventory;
import org.jeecg.inventory.mapper.InventoryMapper;
import org.jeecg.inventory.service.IInventoryService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 库存管理表
 * @Author: jeecg-boot
 * @Date:   2021-04-28
 * @Version: V1.0
 */
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements IInventoryService {

}
