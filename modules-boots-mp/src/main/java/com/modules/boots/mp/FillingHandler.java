/**
 * All rights Reserved, Designed By 林溪
 * Copyright:    Copyright(C) 2016-2030
 */

package com.modules.boots.mp;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * MYSQL字段填充
 * @author：林溪
 * @date：2020年10月3日
 */
@Slf4j
@Component(value = "mysqlFillingHandler")
public class FillingHandler implements MetaObjectHandler {

    /**
     * 新增数据的时候,自动进行数据填充
     * @author 林溪
     * @param metaObject
     * @return
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始进行新增字段数据填充");

    }

    /**
     * 更新数据的时候,自动进行数据填充
     * @author 林溪
     * @param metaObject
     * @return
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开始进行更新字段数据填充");
    }
}
