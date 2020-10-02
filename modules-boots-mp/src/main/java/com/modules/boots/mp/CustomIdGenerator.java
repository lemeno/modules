package com.modules.boots.mp;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * 自定义ID生成器
 * @author：溪云阁
 * @date：2020年5月10日
 */
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    /**
     * 获取自定义id
     * @author 溪云阁
     * @param entity
     * @return 返回数据库的主键ID
     */
    @Override
    public Number nextId(Object entity) {
        // 采用雪花算法获取id,时间回拨会存在重复,这里用随机数来减少重复的概率
        final Snowflake snowflake = IdUtil.createSnowflake(1, (int) (Math.random() * 20 + 1));
        return snowflake.nextId();
    }

}
