package com.niru.wikimedia.producer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangeProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangeProducer.class);

    @Value("${spring.kafka.topic}")
    private String topicName;

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangeProducer(KafkaTemplate<String, String > kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        //To read Realtime stream data from wikimedia, we use event source - WikimediaChanges Handler will trigger when message arrive
        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topicName);

        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }

}
