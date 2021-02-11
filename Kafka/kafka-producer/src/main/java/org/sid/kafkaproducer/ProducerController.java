package org.sid.kafkaproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    @Autowired
    KafkaTemplate<String, Bill> kafkaTemplate;
    int counter = 0;

    @GetMapping(path = "/send")
    public String send(@RequestParam String topic, @RequestParam String client_name, @RequestParam double amount) {
        ++counter;
        kafkaTemplate.send(topic, String.valueOf(counter), new Bill((long) counter, client_name, amount));
        return "Bill sent ...";
    }
}
