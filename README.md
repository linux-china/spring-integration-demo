Spring Integration Demo
=======================

### Message endpoints

* transform: Convert a Message’s content or structure and returning the modified Message
* filter: Determine whether a Message should be passed to an output channel at all
* route: Responsible for deciding what channel or channels should receive the Message next
* split: responsibility is to accept a Message from its input channel, split that Message into multiple Messages, and then send each of those to its output channel
* aggregate: Receive multiple Messages and combines them into a single Message
* Service Activator(MessageHandler):  Connect a service instance to the messaging system  and invoke an operation on some service object to process the request Message
* Channel Adapter: Connect a Message Channel to some other system or transport, such as file, ftp, jms
* Messaging Bridge： Relatively trivial endpoint that simply connects two Message Channels or Channel Adapters

### Core classes

#### message

* MessageSource: source of  Messages that can be polled,such as ftp, database, nosql
* MessageHandler: Contract for handling message
* MessagingTemplate: message template
* MessageBuilder: message builder

#### Channel

* MessageChannel: Defines methods for sending messages
* PollableChannel: A  MessageChannel from which messages may be actively received through polling
* SubscribableChannel: A MessageChannel that maintains a registry of subscribers and invokes them to handle messages sent through this channel.
* PublishSubscribeChannel: A channel that sends Messages to each of its subscribers
* DirectChannel: A channel that invokes a single subscriber for each sent Message. The invocation will occur in the sender's thread.
* QueueChannel: point-to-point Channel
* RendezvousChannel: Useful for implementing request-reply operations
* ChannelInterceptor: channel interceptor
* MessageChannels: builder of channel

# Reactive Integration

* spring-integration-rsocket: https://docs.spring.io/spring-integration/reference/html/rsocket.html#rsocket

RSocket support for Spring Integration
Spring Boot now provides auto-configuration for Spring Integration’s RSocket support.

If spring-integration-rsocket is available, developers can configure an RSocket server using "spring.rsocket.server.*" properties and let it use IntegrationRSocketEndpoint or RSocketOutboundGateway components to handle incoming RSocket messages.

# References

* Spring Integration Home: https://spring.io/projects/spring-integration
* Spring Tips: The Spring Integration Kotlin DSL https://www.youtube.com/watch?v=1llAwgOKjTY&feature=emb_title
* Spring Tips: the Spring Integration Zip module and GraalVM native images with Spring Native: https://www.youtube.com/watch?v=O17OjxBLVrs
