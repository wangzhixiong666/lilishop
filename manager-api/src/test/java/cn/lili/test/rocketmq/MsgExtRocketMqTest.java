package cn.lili.test.rocketmq;

import cn.lili.rocketmq.RocketmqSendCallbackBuilder;
import cn.lili.rocketmq.tags.MqOrderTagsEnum;
import cn.lili.common.properties.RocketmqCustomProperties;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author paulG
 * @since 2021/1/15
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class MsgExtRocketMqTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private RocketmqCustomProperties rocketmqCustomProperties;

    @Test
    void searchAll() {
        String destination = rocketmqCustomProperties.getOrderTopic() + ":" + MqOrderTagsEnum.STATUS_CHANGE.name();
        Message<String> message = MessageBuilder.withPayload("Context").build();
        rocketMQTemplate.asyncSend(destination, message, RocketmqSendCallbackBuilder.commonCallback());
        rocketMQTemplate.send(destination, message);
        Assertions.assertTrue(true);
    }

}
