package com.chengxuunion.component.mqtt.model;

import com.chengxuunion.component.mqtt.util.MqttPropertiesUtils;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-11 16:27
 * @Modified By:
 */
public class MqttProperty {

    private String brokerUrl = MqttPropertiesUtils.getInstance().getValue("mqtt.brokerUrl");

    private String userName = MqttPropertiesUtils.getInstance().getValue("mqtt.userName");

    private String password = MqttPropertiesUtils.getInstance().getValue("mqtt.password");

    private boolean cleanSession = false;

    private String clientId;

    public String getBrokerUrl() {
        return brokerUrl;
    }

    public void setBrokerUrl(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCleanSession() {
        return cleanSession;
    }

    public void setCleanSession(boolean cleanSession) {
        this.cleanSession = cleanSession;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
