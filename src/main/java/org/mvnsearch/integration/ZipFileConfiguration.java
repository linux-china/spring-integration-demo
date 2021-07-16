package org.mvnsearch.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.dsl.FileInboundChannelAdapterSpec;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.zip.splitter.UnZipResultSplitter;
import org.springframework.integration.zip.transformer.UnZipTransformer;

import java.io.File;

/**
 * Spring integration zip : https://spring.io/blog/2021/06/25/spring-integration-zip-2-0-0-available
 *
 * @author linux_china
 */
@Configuration
public class ZipFileConfiguration {

    @Bean
    public UnZipTransformer unZipTransformer() {
        return new UnZipTransformer();
    }

    @Bean
    public UnZipResultSplitter unZipResultSplitter() {
        return unZipResultSplitter();
    }

    @Bean
    public IntegrationFlow extractZipFiles(@Value("file://${user.home}/temp/in") File in,
                                           @Value("file://${user.home}/temp/out") File out,
                                           UnZipTransformer unZipTransformer,
                                           UnZipResultSplitter unZipResultSplitter) {
        final FileInboundChannelAdapterSpec inbound = Files.inboundAdapter(in).autoCreateDirectory(true);
        return IntegrationFlows.from(inbound, pm -> pm.poller(p -> p.fixedRate(1000)))
                .transform(unZipTransformer)
                .split(unZipResultSplitter) // split zip into file list
                .handle(message -> {
                    System.out.println("new messages: " + message.getPayload());
                    message.getHeaders().forEach((k, v) -> {
                        System.out.println(k + "=" + v);
                    });
                })
                .get();
    }
}
