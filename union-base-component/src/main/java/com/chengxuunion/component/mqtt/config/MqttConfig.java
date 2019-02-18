package com.chengxuunion.component.mqtt.config;

import com.chengxuunion.component.mqtt.model.MqttProperties;
import com.chengxuunion.component.mqtt.model.MqttProperty;
import com.chengxuunion.component.mqtt.service.publish.impl.MqttPublishClient;
import com.chengxuunion.component.mqtt.service.subscribe.impl.MqttSubscribeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-11 17:27
 * @Modified By:
 */
//@Configuration
public class MqttConfig {

    @Autowired
    private MqttProperties mqttProperties;

    @Bean
    public MqttProperty mqttSimpleProperty() {
        MqttProperty mqttProperty = new MqttProperty();
        mqttProperty.setClientId(mqttProperties.getClientId());
        return mqttProperty;
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public MqttSubscribeClient mqttSubscribeClient(MqttProperty mqttSimpleProperty) {
        MqttSubscribeClient mqttSubscribeClient = new MqttSubscribeClient();
        mqttSubscribeClient.setMqttProperty(mqttSimpleProperty);
        return mqttSubscribeClient;
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public MqttPublishClient mqttPublishClient(MqttProperty mqttSimpleProperty) {
        MqttPublishClient mqttPublishClient = new MqttPublishClient();
        mqttPublishClient.setMqttProperty(mqttSimpleProperty);
        return mqttPublishClient;
    }

}
