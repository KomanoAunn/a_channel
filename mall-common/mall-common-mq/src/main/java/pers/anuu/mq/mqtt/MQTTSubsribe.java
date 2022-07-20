package pers.anuu.mq.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

/**
 * @author pangxiong
 * @title: MQTTSubsribe
 * @projectName A_Channel
 * @description: TODO
 * @date 2022/6/2318:36
 */

@Service
@IntegrationComponentScan
public class MQTTSubsribe {
    private static final Logger LOGGER = LoggerFactory.getLogger(MQTTSubsribe.class);

    @Autowired
    private MqttConfig mqttConfig;

    /* 客户端对象 */
    public MqttClient client;

    @Autowired
    private MqttConnect mqttConnect;

    /**
     * 回调方法
     * @return
     */
    public MqttClient connectCallback() {
        try {
            if (client==null) {
                client = new MqttClient(mqttConfig.getHost(), mqttConfig.getClientid(), new MemoryPersistence());
            }
            MqttConnectOptions options = mqttConnect.getOptions();
            //判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
            if (!client.isConnected()) {
                client.connect(options);
            }else {//这里的逻辑是如果连接成功就重新连接
                client.disconnect();
                client.connect(mqttConnect.getOptions(options));
            }
            LOGGER.info("-----回调-----客户端连接成功");
        } catch (MqttException e) {
            LOGGER.info(e.getMessage(), e);
        }
        return client;
    }

    /**
     * 方法实现说明：
     * 断线重连方法，如果是持久订阅，重连是不需要再次订阅
     * 如果是非持久订阅，重连是需要重新订阅主题 取决于options.setCleanSession(true);
     * true为非持久订阅
     */
    public void connect() {
        try {
            //防止重复创建MQTTClient实例
            if (client==null) {
                //clientId不能和其它的clientId一样，否则会出现频繁断开连接和重连的问题
                client = new MqttClient(mqttConfig.getHost(), mqttConfig.getClientid(), new MemoryPersistence());// MemoryPersistence设置clientid的保存形式，默认为以内存保存
                //如果是订阅者则添加回调类，发布不需要，PushCallback类在后面，继续往下看
                client.setCallback(new PushCallback(MQTTSubsribe.this));
            }
            MqttConnectOptions options = mqttConnect.getOptions();
            //判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
            if (!client.isConnected()) {
                client.connect(options);
            }else {//这里的逻辑是如果连接成功就重新连接
                client.disconnect();
                client.connect(mqttConnect.getOptions(options));
            }
            LOGGER.info("----------客户端连接成功");
        } catch (MqttException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    /**
     * 订阅端订阅消息
     * @param topic 要订阅的主题
     * @param qos 订阅消息的级别
     */
    public void init(String topic, int qos) {
        //建立连接
        connect();
        //以某个消息级别订阅某个主题
        subscribe(topic, qos);
    }

    /**
     * 订阅端取消订阅消息
     * @param topic 要订阅的主题
     */
    public void unionInit(String topic) {
        //建立连接
        connect();
        //取消订阅某个主题
        unsuSubscribe(topic);
    }

    /**
     * 订阅某个主题
     *
     * @param topic .
     * @param qos .
     */
    public void subscribe(String topic, int qos) {

        try {
            client.subscribe(topic,2);
        } catch (MqttException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    /**
     * 取消订阅某个主题
     *
     * @param topic 要取消的主题
     */
    public void unsuSubscribe(String topic) {

        try {
            //MQTT 协议中订阅关系是持久化的，因此如果不需要订阅某些 Topic，需要调用 unsubscribe 方法取消订阅关系。
            client.unsubscribe(topic);
        } catch (MqttException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }


    //通过通道获取数据
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")  //异步处理
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
//				System.out.println("message:"+message);
                System.out.println("----------------------");
                System.out.println("message:"+message.getPayload());
                System.out.println("PacketId:"+message.getHeaders().getId());
                System.out.println("Qos:"+message.getHeaders().get(MqttHeaders.QOS));

                String topic = (String) message.getHeaders().get(MqttHeaders.TOPIC);
                System.out.println("topic:"+topic);
            }
        };
    }


}