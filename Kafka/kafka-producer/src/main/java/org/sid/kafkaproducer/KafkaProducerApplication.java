package org.sid.kafkaproducer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableKafka
public class KafkaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProducerController producerController) {
        return args -> {
            Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
                String topic = "FACTURATION";
                int rand = 1+ new Random().nextInt(999);
                double amount = 50+ new Random().nextDouble()*949;
                producerController.send(topic, "Client"+rand, amount);
                System.out.println("Bill sent ...");
            }, 1000, 1000, TimeUnit.MILLISECONDS);
        };
    }

}
