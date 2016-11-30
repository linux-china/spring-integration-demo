package org.mvnsearch.integration.impl;

import org.mvnsearch.integration.EndpointComponents;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessageEndpoint;

/**
 * endpoint components implementation
 *
 * @author linux_china
 */
@MessageEndpoint
public class EndpointComponentsImpl implements EndpointComponents {

    @Gateway(requestChannel = "atomChannel")
    public void placeOrder(Integer num) {
        System.out.println("gateway:" + num);
    }
}
