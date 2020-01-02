package com.lhh.community.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: community
 * @Date: 2020/1/2 11:18
 * @Author: lhh
 * @Description:
 */
public class LogUtil {

    public static Logger logger;

    public static Logger logger(Class<?> class1)
    {
        logger = LoggerFactory.getLogger(class1);
        return logger;
    }
}
