/**
 * All rights Reserved, Designed By 林溪
 * Copyright:    Copyright(C) 2016-2020
 * Company       林溪开源
 */

package com.modules.boots.mp;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * 字段自动填充
 * @author：林溪
 * @date：2020年9月9日
 */
@Slf4j
@Component
public class FiledsFillingHandler implements MetaObjectHandler {

    /**
     * 新增数据的时候,自动进行数据填充
     * @author 林溪
     * @param metaObject
     * @return
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始进行新增字段数据填充");
        final Object createdId = getFieldValByName("createdId", metaObject);
        final Object createdName = getFieldValByName("createdName", metaObject);
        final Object createdDate = getFieldValByName("createdDate", metaObject);
        final Object deletedStatus = getFieldValByName("deletedStatus", metaObject);
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
        final Object updatedId = getFieldValByName("updatedId", metaObject);
        final Object updatedName = getFieldValByName("updatedName", metaObject);
        final Object updatedDate = getFieldValByName("updatedDate", metaObject);

    }

}
