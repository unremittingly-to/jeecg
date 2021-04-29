package org.jeecg.customer.service.impl;

import org.jeecg.customer.entity.Customer;
import org.jeecg.customer.mapper.CustomerMapper;
import org.jeecg.customer.service.ICustomerService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户管理表
 * @Author: jeecg-boot
 * @Date:   2021-04-28
 * @Version: V1.0
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
