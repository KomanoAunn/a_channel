package pers.anuu.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pangxiong
 * @title: PublishController
 * @projectName A_Channel
 * @description: TODO
 * @date 2022/6/2318:05
 */
@RestController
@RequestMapping(value = "/publishController")
public class PublishController {

    @Autowired
    private MQTTServer mqttserver;

    @RequestMapping(value = "testPublish")
    public String testPublish(String topic, String msg, int qos) {
        mqttserver.sendMQTTMessage(topic, msg, qos);
        String data = "发送了一条主题是‘"+topic+"’，内容是:"+msg+"，消息级别 "+qos;
        return data;
    }
}
