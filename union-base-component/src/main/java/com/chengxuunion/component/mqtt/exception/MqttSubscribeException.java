package com.chengxuunion.component.mqtt.exception;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-11 16:01
 * @Modified By:
 */
public class MqttSubscribeException extends Exception {

    public MqttSubscribeException() {
        super();
    }

    public MqttSubscribeException(String message) {
        super(message);
    }

    public MqttSubscribeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MqttSubscribeException(Throwable cause) {
        super(cause);
    }

    protected MqttSubscribeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
