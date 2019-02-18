package com.chengxuunion.component.mqtt.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-11 17:29
 * @Modified By:
 */
@Configuration
@ConfigurationProperties(prefix="mqtt")
@PropertySource(value= {"classpath:mqtt.properties"})
public class MqttProperties {

    private String clientId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
