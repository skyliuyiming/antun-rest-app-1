package com.zdjy.bigdata.antun.util;

import java.util.UUID;

/**
 * code生成器
 * @author david
 * @create 2017年11月14日 下午3:09:53
 */
public class CodeGenerateUtils {
    public static String getRandomCode(){
        return UUID.randomUUID().toString();
    }
}
