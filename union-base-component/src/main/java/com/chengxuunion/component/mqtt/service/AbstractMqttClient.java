package com.chengxuunion.component.mqtt.service;

import com.chengxuunion.component.mqtt.exception.MqttClientException;
import com.chengxuunion.component.mqtt.model.MqttProperty;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-11 15:57
 * @Modified By:
 */
public abstract class AbstractMqttClient {

    private static Logger logger = LoggerFactory.getLogger(AbstractMqttClient.class);
    private MqttProperty mqttProperty;
    private MqttClient mqttClient;

    public void setMqttProperty(MqttProperty mqttProperty) {
        this.mqttProperty = mqttProperty;
    }

    public MqttClient getMqttClient() {
        return this.mqttClient;
    }

    public void init() throws MqttClientException {
        this.mqttClient = this.buildMqttClient();
    }

    public void destroy() throws MqttException {
        if (this.mqttClient != null) {
            this.mqttClient.disconnect();
            this.mqttClient.close();
        }
    }

    protected MqttConnectOptions buildMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(false);
        mqttConnectOptions.setUserName(this.mqttProperty.getUserName());
        mqttConnectOptions.setPassword(this.mqttProperty.getPassword().toCharArray());
        // 设置超时时间
        mqttConnectOptions.setConnectionTimeout(10);
        // 设置会话心跳时间
        mqttConnectOptions.setKeepAliveInterval(20);
        mqttConnectOptions.setAutomaticReconnect(true);
        return mqttConnectOptions;
    }

    protected MqttClient buildMqttClient() throws MqttClientException {
        try {
            MemoryPersistence memoryPersistence = new MemoryPersistence();
            this.mqttClient = new MqttClient(mqttProperty.getBrokerUrl(), mqttProperty.getClientId(), memoryPersistence);
            MqttConnectOptions mqttConnectOptions = buildMqttConnectOptions();
            this.mqttClient = buildMqttClientConnection(mqttClient, mqttConnectOptions);
            return this.mqttClient;
        } catch (MqttException e) {
            logger.error("初始化MqttClient出现异常", e);
            throw new MqttClientException(e);
        }
    }

    public abstract MqttClient buildMqttClientConnection(MqttClient mqttClient, MqttConnectOptions mqttConnectOptions) throws MqttException;


}
