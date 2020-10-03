/**
 * All rights Reserved, Designed By OprCalf
 * Copyright:    Copyright(C) 2016-2020
 * Company       BSL Ltd.
 */

package com.modules.bases.web;

import org.springframework.util.StringUtils;

import cn.hutool.core.util.URLUtil;

/**
 * 表头工具类
 * @author：林溪
 * @date：2020年9月6日
 */
public class HeaderUtils {

    /**
     * 获取header中的值
     * @author OprCalf
     * @param key header中的key
     * @param needDecode 是否需要进行URL解码
     * @return String
     */
    public static String getValue(String key, boolean needDecode) {
        final String str = HttpContextUtils.getHttpServletRequest().getHeader(key);
        if (!StringUtils.isEmpty(str)) {
            if (needDecode) {
                return URLUtil.decode(key);
            } else {
                return str;
            }
        } else {
            return "";
        }
    }

}
