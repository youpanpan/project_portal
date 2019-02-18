package com.chengxuunion.component.mqtt.service.subscribe;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-11 16:13
 * @Modified By:
 */
public interface MqttMessageCallback {

    /**
     * 接收到消息
     *
     * @param topic
     * @param mqttMessage
     * @throws Exception
     */
    void messageArrived(String topic, MqttMessage mqttMessage) throws Exception;
}
