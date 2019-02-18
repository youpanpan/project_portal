package com.chengxuunion.component.mqtt.service.publish;

import com.chengxuunion.component.mqtt.exception.MqttPublishException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-11 15:54
 * @Modified By:
 */
public interface MqttPublish {

    /**
     * 发布消息
     *
     * @param topic 消息主题
     * @param qos   服务质量
     * @param message   消息内容
     * @throws MqttPublishException
     */
    void publish(String topic, int qos, String message) throws MqttPublishException;

    /**
     * 发布消息
     *
     * @param topic 消息主题
     * @param qos   服务质量
     * @param message   消息内容
     * @throws MqttPublishException
     */
    void publish(String topic, int qos, byte[] message) throws MqttPublishException;
}
