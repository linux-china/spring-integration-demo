package org.mvnsearch.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageHandler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * integration configuration
 *
 * @author linux_china
 */
@Configuration
public class IntegrationConfiguration {

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(1000).get();
    }

    @Bean
    @InboundChannelAdapter(value = "atomChannel", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<?> integerMessageSource() {
        MethodInvokingMessageSource source = new MethodInvokingMessageSource();
        source.setObject(new AtomicInteger());
        source.setMethodName("getAndIncrement");
        return source;
    }

    @Bean
    MessagingTemplate atomChannelMessageTemplate() {
        return new MessagingTemplate(atomChannel());
    }

    @Bean("atomChannel")
    public PublishSubscribeChannel atomChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    @ServiceActivator(inputChannel = "atomChannel")
    public MessageHandler sendChatMessageHandler() {
        return message -> {
            System.out.println("Handle:" + message.getPayload());
        };
    }

    @Bean
    public IntegrationFlow myFlow() {
        return IntegrationFlows.from(this.integerMessageSource(), c ->
                c.poller(poller -> poller.fixedDelay(500)))
                .channel(atomChannel())
                .filter((Integer p) -> p > 0)
                .handle(message -> System.out.println("flow:" + message.getPayload()))
                .get();
    }


    @Bean("queueChannel")
    public QueueChannel queueChannel() {
        return MessageChannels.queue(100).get();
    }

    @Bean
    @ServiceActivator(inputChannel = "queueChannel")
    public MessageHandler queueMessageHandler() {
        return message -> {
            System.out.println("Handle Queue:" + message.getPayload());
        };
    }

    @Bean
    MessagingTemplate queueChannelMessageTemplate() {
        return new MessagingTemplate(queueChannel());
    }
}
