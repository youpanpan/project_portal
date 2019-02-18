package com.chengxuunion.component.mqtt.util;

import org.slf4j.LoggerFactory;

import com.chengxuunion.util.properties.PropertiesReader;

import java.io.IOException;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-11 16:29
 * @Modified By:
 */
public class MqttPropertiesUtils {

    /**
     * properties文件读取器
     */
    private PropertiesReader reader;

    private MqttPropertiesUtils() {
        try {
            reader = new PropertiesReader("/mqtt.properties");
        } catch (IOException e) {
            LoggerFactory.getLogger(this.getClass()).error("初始化oauth.properties文件读取器出现异常", e);
        }
    }

    /**
     * 内部静态类，实现单例
     *
     * @author kutome
     * @date 2018年8月29日
     * @version V1.0
     */
    public static class Singleton {
        private static MqttPropertiesUtils instance = new MqttPropertiesUtils();
    }

    /**
     * 获取PropertiesReader对象
     *
     * @return
     */
    public static PropertiesReader getInstance() {
        return MqttPropertiesUtils.Singleton.instance.reader;
    }

}
