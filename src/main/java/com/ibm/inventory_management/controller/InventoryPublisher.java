package com.ibm.inventory_management.controller;

import com.ibm.inventory_management.config.KafkaConfig;
import com.ibm.inventory_management.model.InventoryStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryPublisher {

    @Autowired
    private KafkaConfig config;

    @Autowired
    private KafkaTemplate<Object, Object> template;

    @PostMapping(path = "/send/inventory/{count}")
    public void sendInventoryStock(@PathVariable String count) {
        this.template.send(config.getTopic(), new InventoryStock(count));
    }
}
