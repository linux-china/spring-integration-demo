package org.mvnsearch.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class IntegrationDemoApplicationTests {
    @Autowired
    @Qualifier("atomChannelMessageTemplate")
    MessagingTemplate atomMessageTemplate;
    @Autowired
    @Qualifier("queueChannelMessageTemplate")
    MessagingTemplate queueMessageTemplate;

    @Autowired
    @Qualifier("atomChannel")
    SubscribableChannel messageChannel;

    @Autowired
    UserService userService;
    @Autowired
    EndpointComponents gatwayService;

    @Test
    public void contextLoads() throws Exception {
        userService.defaultPayload("Jacky", "chan");
        gatwayService.placeOrder(333);
        atomMessageTemplate.send(new GenericMessage<Integer>(111));
        queueMessageTemplate.send(new GenericMessage<String>("2222"));
        Thread.sleep(10000);
    }

}
