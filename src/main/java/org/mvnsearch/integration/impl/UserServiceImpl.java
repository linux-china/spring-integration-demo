package org.mvnsearch.integration.impl;

import org.mvnsearch.integration.UserService;
import org.springframework.integration.annotation.Publisher;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

/**
 * user service implementation
 *
 * @author linux_china
 */
@Service
public class UserServiceImpl implements UserService {

    @Publisher(channel = "queueChannel")
    public String defaultPayload(String fname, @Header("last") String lname) {
        return fname + " " + lname;
    }
}
