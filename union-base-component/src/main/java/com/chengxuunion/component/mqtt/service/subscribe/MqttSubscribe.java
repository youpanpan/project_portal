package com.chengxuunion.component.mqtt.service.subscribe;

import com.chengxuunion.component.mqtt.exception.MqttSubscribeException;
import com.chengxuunion.component.mqtt.service.subscribe.MqttMessageCallback;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-11 15:55
 * @Modified By:
 */
public interface MqttSubscribe {

    /**
     * 订阅消息
     *
     * @param topic 消息主题
     * @param qos   服务质量
     * @param mqttMessageCallback  回调接口
     * @throws MqttSubscribeException
     */
    void subscribe(String topic, int qos, MqttMessageCallback mqttMessageCallback) throws MqttSubscribeException;

    /**
     * 订阅多个消息
     *
     * @param topic 消息主题数组
     * @param qos   服务质量数组
     * @param mqttMessageCallback   回调接口
     * @throws MqttSubscribeException
     */
    void subscribe(String[] topic, int[] qos, MqttMessageCallback mqttMessageCallback) throws MqttSubscribeException;
}
