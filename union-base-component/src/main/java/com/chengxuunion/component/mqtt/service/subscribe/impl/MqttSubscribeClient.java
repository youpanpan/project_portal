package com.chengxuunion.component.mqtt.service.subscribe.impl;

import com.chengxuunion.component.mqtt.exception.MqttSubscribeException;
import com.chengxuunion.component.mqtt.service.AbstractMqttClient;
import com.chengxuunion.component.mqtt.service.subscribe.MqttMessageCallback;
import com.chengxuunion.component.mqtt.service.subscribe.MqttSubscribe;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-11 15:56
 * @Modified By:
 */
public class MqttSubscribeClient extends AbstractMqttClient implements MqttSubscribe {
    private static Logger logger = LoggerFactory.getLogger(MqttSubscribeClient.class);
    private MqttConnectOptions mqttConnectOptions;

    @Override
    public void subscribe(String topic, int qos, MqttMessageCallback mqttMessageCallback) throws MqttSubscribeException {
        MqttClient mqttClient = super.getMqttClient();
        this.buildCallback(mqttClient, new String[]{topic}, new int[]{qos}, mqttMessageCallback);

        try {
            mqttClient.connect(this.mqttConnectOptions);
        } catch (MqttException e) {
            logger.error("MQTT连接发生异常", e);
            throw new MqttSubscribeException("MQTT连接发生异常", e);
        }
    }

    @Override
    public void subscribe(String[] topic, int[] qos, MqttMessageCallback mqttMessageCallback) throws MqttSubscribeException {
        MqttClient mqttClient = super.getMqttClient();
        this.buildCallback(mqttClient, topic, qos, mqttMessageCallback);

        try {
            mqttClient.connect(this.mqttConnectOptions);
        } catch (MqttException e) {
            logger.error("MQTT连接发生异常", e);
            throw new MqttSubscribeException("MQTT连接发生异常", e);
        }
    }

    private void buildCallback(final MqttClient mqttClient, final String[] topic, final int[] qos, final MqttMessageCallback mqttMessageCallback) {
        mqttClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                logger.debug("MQTT连接成功");
                try {
                    mqttClient.subscribe(topic, qos);
                } catch (MqttException e) {
                    logger.error("订阅"+ topic +"出现异常", e);
                }
            }

            @Override
            public void connectionLost(Throwable throwable) {
                logger.error("MQTT消费者断开连接", throwable);
            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                logger.debug("收到主题：" + s + "，发送来的消息:" + new String(mqttMessage.getPayload()));
                mqttMessageCallback.messageArrived(s, mqttMessage);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                logger.debug("delivery complete" + iMqttDeliveryToken);
            }
        });
    }

    @Override
    public MqttClient buildMqttClientConnection(MqttClient mqttClient, MqttConnectOptions mqttConnectOptions) throws MqttException {
        this.mqttConnectOptions = mqttConnectOptions;
        return mqttClient;
    }
}
