/**
 * All rights Reserved, Designed By 林溪
 * Copyright:    Copyright(C) 2016-2030
 */

package com.modules.bases.security;

import org.jasypt.util.text.AES256TextEncryptor;

/**
 * 敏感信息加密工具类
 * @author：林溪
 * @date：2020年10月2日
 */

public class JasyptUtils {

    /**
     * 根据密码对加密值进行解密
     * @author OprCalf
     * @param decryptPsw 待解密的密码
     * @param decryptValue 待解密的值
     * @return String
     */
    public static String decryptMsg(String decryptPsw, String decryptValue) {
        final AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(decryptPsw);
        return encryptor.decrypt(decryptValue);
    }

    /**
     * 根据密码对加密值进行加密
     * @author OprCalf
     * @param decryptPsw
     * @param decryptValue
     * @return String
     */
    public static String encryptMsg(String decryptPsw, String decryptValue) {
        final AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(decryptPsw);
        return encryptor.encrypt(decryptValue);
    }

    public static void main(String args[]) {
        final String devPsw = "by9xb8yrybr98b2cy498y8xrynq9";
        System.out.println(encryptMsg(devPsw, "lemone1qaz@WSX"));
    }

}
