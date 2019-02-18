package com.chengxuunion.component.mqtt.service.publish.impl;

import com.chengxuunion.component.mqtt.exception.MqttPublishException;
import com.chengxuunion.component.mqtt.service.AbstractMqttClient;
import com.chengxuunion.component.mqtt.service.publish.MqttPublish;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-11 15:56
 * @Modified By:
 */
public class MqttPublishClient extends AbstractMqttClient implements MqttPublish {
    private static Logger logger = LoggerFactory.getLogger(MqttPublishClient.class);

    @Override
    public void publish(String topic, int qos, String message) throws MqttPublishException {
        try {
            MqttClient mqttClient = super.getMqttClient();
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setPayload(message.getBytes("UTF-8"));
            mqttMessage.setQos(qos);

            mqttClient.publish(topic, mqttMessage);
        } catch (Exception e) {
            String errorMsg = "发布消息【" + topic + "," + message + "】发生异常";
            logger.error(errorMsg, e);
            throw new MqttPublishException(errorMsg);
        }
    }

    @Override
    public void publish(String topic, int qos, byte[] message) throws MqttPublishException {
        try {
            MqttClient mqttClient = super.getMqttClient();
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setPayload(message);
            mqttMessage.setQos(qos);

            mqttClient.publish(topic, mqttMessage);
        } catch (Exception e) {
            String errorMsg = "发布消息【" + topic + "," + message + "】发生异常";
            logger.error(errorMsg, e);
            throw new MqttPublishException(errorMsg);
        }
    }

    @Override
    public MqttClient buildMqttClientConnection(MqttClient mqttClient, MqttConnectOptions mqttConnectOptions) throws MqttException {
        mqttClient.connect(mqttConnectOptions);
        return mqttClient;
    }
}
