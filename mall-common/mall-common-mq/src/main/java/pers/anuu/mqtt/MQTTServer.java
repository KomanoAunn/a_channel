package pers.anuu.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pangxiong
 * @title: MQTTServer
 * @projectName A_Channel
 * @description: TODO
 * @date 2022/6/2318:04
 */
@Service
public class MQTTServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MQTTServer.class);

    /* 客户端对象 */
    public MqttClient client;

    /* 主题对象 */
    public MqttTopic topic;

    /* 消息内容对象 */
    public MqttMessage message;

    @Autowired
    private MqttConnect mqttConnect;

    @Autowired
    private MqttConfig config;

    public MQTTServer() {
        LOGGER.info("消息发布者上线了");
    }

    /**
     * 客户端和服务端建立连接
     */
    public MqttClient connect() {
        //防止重复创建MQTTClient实例
        try {
            if (client==null) {
                //先让客户端和服务器建立连接，MemoryPersistence设置clientid的保存形式，默认为以内存保存
                client = new MqttClient(config.getHost(), config.getClientid(), new MemoryPersistence());
                //发布消息不需要回调连接
                //client.setCallback(new PushCallback());
            }

            MqttConnectOptions options = mqttConnect.getOptions();
            //判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
            if (!client.isConnected()) {
                client.connect(options);
                LOGGER.info("---------------------连接成功");
            }else {//这里的逻辑是如果连接成功就重新连接
                client.disconnect();
                client.connect(mqttConnect.getOptions(options));
                LOGGER.info("---------------------连接成功");
            }
        } catch (MqttException e) {
            LOGGER.info(e.toString());
        }
        return client;
    }


    public boolean publish(MqttTopic topic , MqttMessage message) {

        MqttDeliveryToken token = null;
        try {
            //把消息发送给对应的主题
            token = topic.publish(message);
            token.waitForCompletion();
            //检查发送是否成功
            boolean flag = token.isComplete();

            StringBuffer sbf = new StringBuffer(200);
            sbf.append("给主题为'"+topic.getName());
            sbf.append("'发布消息：");
            if (flag) {
                sbf.append("成功！消息内容是："+new String(message.getPayload()));
            } else {
                sbf.append("失败！");
            }
            LOGGER.info(sbf.toString());
        } catch (MqttException e) {
            LOGGER.info(e.toString());
        }
        return token.isComplete();
    }

    /**
     * MQTT发送指令
     * @param topic 主题
     * @param data 消息内容
     * @param qos 消息级别
     */
    public void sendMQTTMessage(String topic, String data, int qos) {

        try {
            MQTTServer server = new MQTTServer();

            server.client = connect();
            server.topic = server.client.getTopic(topic);
            server.message = new MqttMessage();
            //消息等级
            //level 0：消息最多传递一次，不再关心它有没有发送到对方，也不设置任何重发机制
            //level 1：包含了简单的重发机制，发送消息之后等待接收者的回复，如果没收到回复则重新发送消息。这种模式能保证消息至少能到达一次，但无法保证消息重复
            //level 2： 有了重发和重复消息发现机制，保证消息到达对方并且严格只到达一次
            server.message.setQos(qos);
            //如果重复消费，则把值改为true,然后发送一条空的消息，之前的消息就会覆盖，然后在改为false
            server.message.setRetained(false);

            server.message.setPayload(data.getBytes());
            server.publish(server.topic, server.message);
        } catch (Exception e) {
            LOGGER.info(e.toString());
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws MqttException {
        //sendMQTTMessage("测试","我是发布端", 0);
    }

}
