package com.chengxuunion.component.mqtt.exception;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-11 16:49
 * @Modified By:
 */
public class MqttClientException extends Exception {

    public MqttClientException() {
        super();
    }

    public MqttClientException(String message) {
        super(message);
    }

    public MqttClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public MqttClientException(Throwable cause) {
        super(cause);
    }

    protected MqttClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
