/**
 * All rights Reserved, Designed By 林溪
 * Copyright:    Copyright(C) 2016-2030
 */

package com.modules.boots.mp;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

/**
 * 多租户注入
 * @author：林溪
 * @date：2020年10月3日
 */
@Component
public class Tenant implements TenantLineHandler {

    /**
     * 获取租户的值
     * @author 林溪
     * @return 返回租户的值
     */
    @Override
    public Expression getTenantId() {
        return new LongValue(0000000000L);
    }

    /**
     * 判断表是否需要做多租户判断
     * @author 林溪
     * @param tableName
     * @return 某些表不需要多租户判断,做判断返回false
     */
    @Override
    public boolean ignoreTable(String tableName) {
        return false;
    }

    /**
     * 返回租户字段名称,如果不写默认就是tenant_id
     * @author 林溪
     * @return
     */
    @Override
    public String getTenantIdColumn() {
        return "tenant_id";
    }
}
