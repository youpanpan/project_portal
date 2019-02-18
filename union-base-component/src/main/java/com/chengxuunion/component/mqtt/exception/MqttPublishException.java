package com.chengxuunion.component.mqtt.exception;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-11 16:00
 * @Modified By:
 */
public class MqttPublishException extends Exception {

    public MqttPublishException() {
        super();
    }

    public MqttPublishException(String message) {
        super(message);
    }

    public MqttPublishException(String message, Throwable cause) {
        super(message, cause);
    }

    public MqttPublishException(Throwable cause) {
        super(cause);
    }

    protected MqttPublishException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
