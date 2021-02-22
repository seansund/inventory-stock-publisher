package com.ibm.inventory_management.controller;

import com.ibm.inventory_management.config.KafkaConfig;
import com.ibm.inventory_management.model.InventoryStock;
import io.jaegertracing.internal.JaegerTracer;
import io.opentracing.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryPublisher.class);

    @Autowired
    private KafkaConfig config;

    @Autowired
    private KafkaTemplate<String, InventoryStock> template;

    @Autowired
    private JaegerTracer tracer;

    @PostMapping(path = "/send/inventory/{id}/stock/{count}")
    public void sendInventoryStock(@PathVariable String id, @PathVariable String count) {
        final Span span = tracer.buildSpan("send-inventory-stock").start();

        LOGGER.debug("Sending InventoryStock to topic: " + config.getTopic());
        this.template.send(config.getTopic(), new InventoryStock(id, count));

        span.finish();
    }

}
