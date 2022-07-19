package pers.anuu.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pangxiong
 * @title: PushCallback
 * @projectName A_Channel
 * @description: TODO
 * @date 2022/6/2318:37
 */
public class PushCallback implements MqttCallback {
    private static final Logger LOGGER = LoggerFactory.getLogger(PushCallback.class);

    private MQTTSubsribe mqttSubsribe;

    public PushCallback(MQTTSubsribe subsribe) {
        this.mqttSubsribe = subsribe;
    }


    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        LOGGER.info("---------------------连接断开，可以做重连");
        mqttSubsribe.connectCallback();

        while (true) {
            try {
                //如果没有发生异常说明连接成功，如果发生异常，则死循环
                Thread.sleep(1000);
                break;
            } catch (Exception e) {
                continue;
            }
        }

    }

    /**
     * 发送消息，消息到达后处理方法
     * 本文不用关注
     *
     * @param token
     */
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }

    /**
     * 接收订阅的主题的消息,并处理
     *
     * @param topic
     * @param message
     */
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        String result = new String(message.getPayload(), "UTF-8");
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息Qos : " + message.getQos());
        System.out.println("接收消息内容 : " + result);
        //这里可以针对收到的消息做处理，比如持久化
    }

}
